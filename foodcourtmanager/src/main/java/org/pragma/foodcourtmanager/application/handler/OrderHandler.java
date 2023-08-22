package org.pragma.foodcourtmanager.application.handler;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.pragma.foodcourtmanager.application.dto.request.*;
import org.pragma.foodcourtmanager.application.dto.response.*;
import org.pragma.foodcourtmanager.application.mapper.request.OrderDishRequestMapper;
import org.pragma.foodcourtmanager.application.mapper.request.OrderRequestMapper;
import org.pragma.foodcourtmanager.application.mapper.response.OrderResponseMapper;
import org.pragma.foodcourtmanager.application.util.GeneratorPin;
import org.pragma.foodcourtmanager.domain.api.IOrderDishServicePort;
import org.pragma.foodcourtmanager.domain.api.IOrderServicePort;
import org.pragma.foodcourtmanager.domain.model.*;
import org.pragma.foodcourtmanager.application.exception.ActiveOrderException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Service
@RequiredArgsConstructor
public class OrderHandler implements IOrderHandler{
    private final IOrderServicePort iOrderServicePort;
    private final OrderRequestMapper orderRequestMapper;
    private final OrderResponseMapper orderResponseMapper;

    private final IOrderDishServicePort iOrderDishServicePort;
    private final OrderDishRequestMapper orderDishRequestMapper;

    private final RestaurantHandler restaurantHandler;
    private final UserHandler userHandler;
    private final MessageHandler messageHandler;

    private final OrderDishHandler orderDishHandler;
    private final DishHandler dishHandler;
    private final EmployeeRestaurantHandler employeeRestaurantHandler;

    private final TraceabilityHandler traceabilityHandler;

    @Value("${jwt.secret-key}")
    private String SECRET_KEY;


    @Override
    public Order saveOrder (OrderRequest orderRequest){
        return iOrderServicePort.saveOrder(orderRequestMapper.toOrder(orderRequest));
    }

    @Override
    public void assignOrder (OrderUpdateRequest orderUpdateRequest){
        Long userId = this.getUserIdAuthenticate();
        Order order = iOrderServicePort.getOrder(orderUpdateRequest.getOrderId());
        order.setEmployeeId(userId);
        TraceabilityRequest traceabilityRequest = this.buildTraceability(order,OrderStatus.IN_PREPARATION);
        order.setOrderStatus(OrderStatus.IN_PREPARATION);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String token = (String) authentication.getCredentials();
        traceabilityHandler.saveTraceability(token,traceabilityRequest);
        iOrderServicePort.assignOrder(order);
    }

    private TraceabilityRequest buildTraceability (Order order , OrderStatus newStatus){
        UserResponse userCustomer = userHandler.getUser(order.getCustomerId());
        UserResponse userEmployee = userHandler.getUser(order.getEmployeeId());
        TraceabilityRequest traceabilityRequest = new TraceabilityRequest();
        traceabilityRequest.setOrderId(order.getId());
        traceabilityRequest.setCustomerId(order.getCustomerId());
        traceabilityRequest.setCustomerEmail(userCustomer.getEmail());
        traceabilityRequest.setDate(LocalDateTime.now());
        traceabilityRequest.setPreviousStatus(order.getOrderStatus().name());
        traceabilityRequest.setNewStatus(newStatus.name());
        traceabilityRequest.setEmployeeId(order.getEmployeeId());
        traceabilityRequest.setEmployeeEmail(userEmployee.getEmail());
        traceabilityRequest.setRestaurantId(order.getRestaurantId());

        return  traceabilityRequest;
    }


    @Override
    public void orderReady (OrderUpdateRequest orderUpdateRequest){
        Long userId = this.getUserIdAuthenticate();
        Order order = iOrderServicePort.getOrder(orderUpdateRequest.getOrderId());
        EmployeeRestaurantResponse employeeRestaurantResponse = employeeRestaurantHandler.getEmployeeRestaurant(userId);
        if (order.getRestaurantId() == employeeRestaurantResponse.getRestaurantId()) {
            TraceabilityRequest traceabilityRequest = this.buildTraceability(order,OrderStatus.READY);
            order.setOrderStatus(OrderStatus.READY);
            UserResponse userResponse = userHandler.getUser(order.getCustomerId());
            String pin = GeneratorPin.generateSecurityPin(4);
            MessageRequest messageRequest = new MessageRequest(userResponse.getCellPhoneNumber(), "Tu pedido esta listo , puedes reclamarlo con el siguiente código " + pin);
            order.setVerificationCode(pin);
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String token = (String) authentication.getCredentials();
            traceabilityHandler.saveTraceability(token,traceabilityRequest);
            messageHandler.sendMessage(token,messageRequest);
            iOrderServicePort.assignOrder(order);
        } else {
            throw new RuntimeException("El empleado no pertenece al resturante y por tanto no puede cambiar el estado del pedido");
        }
    }


    @Override
    public void deliverOrder (OrderValidatePinRequest orderValidatePinRequest){
        Order order = iOrderServicePort.getOrder(orderValidatePinRequest.getOrderId());
        if (order.getVerificationCode().equals(orderValidatePinRequest.getPin()) && order.getOrderStatus() == OrderStatus.READY) {
            TraceabilityRequest traceabilityRequest = this.buildTraceability(order,OrderStatus.DELIVERED);
            order.setOrderStatus(OrderStatus.DELIVERED);
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String token = (String) authentication.getCredentials();
            traceabilityHandler.saveTraceability(token,traceabilityRequest);
            iOrderServicePort.assignOrder(order);
        } else {
            throw new RuntimeException("El pin del pedido es incorrecto y no se puede entregar el mismo o aun no esta listo ");
        }
    }

    @Override
    public void cancelOrder (OrderUpdateRequest orderUpdateRequest){
        Order order = iOrderServicePort.getOrder(orderUpdateRequest.getOrderId());
        if(order.getOrderStatus() == OrderStatus.PENDING){
            order.setOrderStatus(OrderStatus.CANCELED);
            iOrderServicePort.assignOrder(order);
        }else{
            throw new RuntimeException("Lo sentimos, tu pedido ya está en preparación y no puede cancelarse");
        }
    }

    @Override
    public List<TraceabilityResponse> getTraceability (){
        Long userId = this.getUserIdAuthenticate();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String token = (String) authentication.getCredentials();
        return traceabilityHandler.getTraceability(token, userId);
    }

    @Override
    public  List<TraceabilityRankingResponse> getRankingEmployeesOrders (){
        Long userId = this.getUserIdAuthenticate();
        List<TraceabilityRankingResponse> result = new ArrayList<>();
        List<TraceabilityTimeResponse> listTraceability = this.getTimeOrders();
        List<TraceabilityTimeResponse> listTimeOrders = new ArrayList<>();
        listTraceability.forEach(traceabilityTimeResponse -> {
            RestaurantResponse restaurantResponse = restaurantHandler.getRestaurant(traceabilityTimeResponse.getRestaurantId());
            if(restaurantResponse.getOwnerId() == userId){
                listTimeOrders.add(traceabilityTimeResponse);
            }
        });
        Map<Long, Duration> employeeTotalTimes = new HashMap<>();
        Map<Long, Integer> employeeOrderCounts = new HashMap<>();
        for (TraceabilityTimeResponse entry : listTimeOrders) {
            Long employeeId = entry.getEmployeeId();
            Duration totalOrderTime = parseTotalOrderTime(entry.getTotalOrderTime());

            employeeTotalTimes.put(employeeId,
                    employeeTotalTimes.getOrDefault(employeeId, Duration.ZERO).plus(totalOrderTime));

            employeeOrderCounts.put(employeeId,
                    employeeOrderCounts.getOrDefault(employeeId, 0) + 1);
        }
        for (Map.Entry<Long, Duration> entry : employeeTotalTimes.entrySet()) {
            Long employeeId = entry.getKey();
            Duration totalDuration = entry.getValue();
            int orderCount = employeeOrderCounts.get(employeeId);
            Duration averageTime = totalDuration.dividedBy(orderCount);

            String averageTimeFormatted = formatDuration(averageTime);
            TraceabilityRankingResponse traceabilityRankingResponse = new TraceabilityRankingResponse();
            traceabilityRankingResponse.setEmployeeId(employeeId);
            traceabilityRankingResponse.setAverageTime(averageTimeFormatted);
            result.add(traceabilityRankingResponse);
        }
        result.sort(Comparator.comparing(TraceabilityRankingResponse::getAverageTime));

        for (int i = 0; i < result.size(); i++) {
                result.get(i).setRanking("Posición en el ranking : " + (i + 1));
                UserResponse userResponse = userHandler.getUser(result.get(i).getEmployeeId());
                result.get(i).setEmployeeFullName(userResponse.getName() + " " + userResponse.getLastName());
                result.get(i).setDocumentId(userResponse.getDocumentId());
        }
        return result;
    }



    @Override
    public List<TraceabilityTimeResponse> getTimeOrders (){
        Long userId = this.getUserIdAuthenticate();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String token = (String) authentication.getCredentials();
        List<TraceabilityResponse> traceabilityResponseList = traceabilityHandler.getAllTraceability(token);
        traceabilityResponseList.sort(Comparator.comparing(TraceabilityResponse::getDate));
        List<TraceabilityTimeResponse> result = new ArrayList<>();
        TraceabilityResponse previous = null;
        for (TraceabilityResponse current : traceabilityResponseList) {
            RestaurantResponse restaurantResponse = restaurantHandler.getRestaurant(current.getRestaurantId());
            if (restaurantResponse.getOwnerId() ==userId &&  previous != null
                    && previous.getOrderId().equals(current.getOrderId())) {
                long timeElapsedMillis = ChronoUnit.MILLIS.between(previous.getDate(), current.getDate());
                Duration duration = Duration.ofMillis(timeElapsedMillis);
                long hours = duration.toHours();
                long minutes = duration.toMinutesPart();
                long seconds = duration.toSecondsPart();
                DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
                String formattedDuration = String.format("%02d:%02d:%02d", hours, minutes, seconds);
                result.add(new TraceabilityTimeResponse(
                        previous.getOrderId(),
                        previous.getCustomerId(),
                        previous.getCustomerEmail(),
                        outputFormatter.format(previous.getDate()),
                        outputFormatter.format(current.getDate()),
                        previous.getEmployeeId(),
                        previous.getEmployeeEmail(),
                        formattedDuration,
                        previous.getRestaurantId()));
            }
            previous = current;
        }
        return result;
    }


    @Override
    public OrderResponse getOrder (Long orderId){
        return orderResponseMapper.toResponse(iOrderServicePort.getOrder(orderId));
    }




    public List<OrderDishResponse> getDishesForOrder (Long orderId){
        List<OrderDishResponse> dishOrderList = new ArrayList<>();
        List<OrderDishResponse> orderDishResponsesList = orderDishHandler.getAllOrderDish(orderId);
        if (orderDishResponsesList != null) {
            for (OrderDishResponse orderDishResponse : orderDishResponsesList) {
                DishResponse dishResponse = dishHandler.getDish(orderDishResponse.getDishId());
                orderDishResponse.setDishId(dishResponse.getId());
                orderDishResponse.setDescription(dishResponse.getDescription());
                orderDishResponse.setName(dishResponse.getName());
                orderDishResponse.setCategoryId(dishResponse.getCategoryId());
                orderDishResponse.setPrice(dishResponse.getPrice());
                orderDishResponse.setImageUrl(dishResponse.getImageUrl());
                orderDishResponse.setIsActive(dishResponse.isActive());
                orderDishResponse.setQuantity(orderDishResponse.getQuantity());
                dishOrderList.add(orderDishResponse);
            }
        }

        return dishOrderList;
    }


    @Override
    public Page<CompleteOrderResponse> getAllOrders (OrderStatus orderStatus, Pageable pageable){
        Long userId = this.getUserIdAuthenticate();
        Long restaurantId = employeeRestaurantHandler.getEmployeeRestaurant(userId).getRestaurantId();
        Page<Order> orders = iOrderServicePort.getAllOrders(restaurantId, orderStatus, pageable);
        List<CompleteOrderResponse> modifiedOrderResponses = new ArrayList<>();
        for (Order order : orders) {
            CompleteOrderResponse response = orderRequestMapper.toResponse(order);
            RestaurantResponse restaurant = restaurantHandler.getRestaurant(response.getRestaurantId());
            UserResponse userResponse = userHandler.getUser(response.getCustomerId());
            response.setRestaurantName(restaurant.getName());
            response.setDocumentIdCustomer(userResponse.getDocumentId());
            response.setDishes(this.getDishesForOrder(order.getId()));
            response.setFullNameCustomer(userResponse.getName() + " " + userResponse.getLastName());
            modifiedOrderResponses.add(response);
        }
        return new PageImpl<>(modifiedOrderResponses, pageable, orders.getTotalElements());
    }


    @Override
    public void saveCompleteOrder (CompleteOrderRequest completeOrderRequest){
        Long userId = this.getUserIdAuthenticate();
        if (!this.hasPendingOrders(userId)) {
            Order order = orderRequestMapper.toOrder(completeOrderRequest);
            OrderRequest orderRequest = orderRequestMapper.toOrderRequest(completeOrderRequest);
            orderRequest.setOrderStatus(OrderStatus.PENDING);
            orderRequest.setDate(LocalDateTime.now());
            orderRequest.setCustomerId(userId);
            Order orderSave = this.saveOrder(orderRequest);
            List<OrderDish> orderDishList = orderDishRequestMapper.toOrderDishList(completeOrderRequest.getDishes());
            orderDishList.forEach(orderDish -> {
                orderDish.setOrderId(orderSave.getId());
                iOrderDishServicePort.saveOrderDish(orderDish);
            });
        } else {
            throw new ActiveOrderException();
        }
    }

    public boolean hasPendingOrders (Long userId){
        List<Order> userOrders = iOrderServicePort.getOrdersByCustomerId(userId);
        for (Order order : userOrders) {
            OrderStatus orderStatus = order.getOrderStatus();
            if (orderStatus == OrderStatus.IN_PREPARATION ||
                    orderStatus == OrderStatus.PENDING ||
                    orderStatus == OrderStatus.READY) {
                return true;
            }
        }
        return false;
    }
    private Long getUserIdAuthenticate (){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String token = (String) authentication.getCredentials();
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();
        Long userId = claims.get("id", Long.class);
        return userId;
    }

    private Long convertTimeToSeconds(String time) {
        String[] timeComponents = time.split(":");
        long hours = Long.parseLong(timeComponents[0]);
        long minutes = Long.parseLong(timeComponents[1]);
        long seconds = Long.parseLong(timeComponents[2]);
        return hours * 3600 + minutes * 60 + seconds;
    }

    private  Duration parseTotalOrderTime(String totalOrderTime) {
        String[] timeComponents = totalOrderTime.split(":");
        int hours = Integer.parseInt(timeComponents[0]);
        int minutes = Integer.parseInt(timeComponents[1]);
        int seconds = Integer.parseInt(timeComponents[2]);

        return Duration.ofHours(hours).plusMinutes(minutes).plusSeconds(seconds);
    }

    private  String formatDuration(Duration duration) {
        long hours = duration.toHours();
        long minutes = duration.toMinutesPart();
        long seconds = duration.toSecondsPart();

        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }


}
