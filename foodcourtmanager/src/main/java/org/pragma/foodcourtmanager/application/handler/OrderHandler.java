package org.pragma.foodcourtmanager.application.handler;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.hibernate.event.spi.SaveOrUpdateEvent;
import org.pragma.foodcourtmanager.application.dto.request.CompleteOrderRequest;
import org.pragma.foodcourtmanager.application.dto.request.OrderDishRequest;
import org.pragma.foodcourtmanager.application.dto.request.OrderRequest;
import org.pragma.foodcourtmanager.application.dto.request.RestaurantRequest;
import org.pragma.foodcourtmanager.application.dto.response.*;
import org.pragma.foodcourtmanager.application.mapper.request.OrderDishRequestMapper;
import org.pragma.foodcourtmanager.application.mapper.request.OrderRequestMapper;
import org.pragma.foodcourtmanager.application.mapper.request.RestaurantRequestMapper;
import org.pragma.foodcourtmanager.application.mapper.response.RestaurantResponseMapper;
import org.pragma.foodcourtmanager.domain.api.IOrderDishServicePort;
import org.pragma.foodcourtmanager.domain.api.IOrderServicePort;
import org.pragma.foodcourtmanager.domain.api.IRestaurantServicePort;
import org.pragma.foodcourtmanager.domain.model.*;
import org.pragma.foodcourtmanager.infrastructure.exception.ActiveOrderException;
import org.pragma.foodcourtmanager.infrastructure.exception.NotOwnerUserException;
import org.pragma.foodcourtmanager.infrastructure.output.jpa.entity.EmployeeRestaurantEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderHandler implements IOrderHandler{
    private final IOrderServicePort iOrderServicePort;
    private final OrderRequestMapper orderRequestMapper;
    private final IOrderDishServicePort iOrderDishServicePort;
    private final OrderDishRequestMapper orderDishRequestMapper;

    private final RestaurantHandler restaurantHandler;
    private final UserHandler userHandler;
    private final OrderDishHandler orderDishHandler;
    private final DishHandler dishHandler;
    private final EmployeeRestaurantHandler employeeRestaurantHandler;



    @Value("${jwt.secret-key}")
    private String SECRET_KEY;


    @Override
    public Order saveOrder (OrderRequest orderRequest){
        return iOrderServicePort.saveOrder(orderRequestMapper.toOrder(orderRequest));
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

    public List<OrderDishResponse> getPlatosForOrder(Long orderId) {
        System.out.println("El orderId que llega " + orderId);
        List<OrderDishResponse> dishOrderList = new ArrayList<>();
        List<OrderDishResponse> orderDishResponsesList = orderDishHandler.getAllOrderDish(orderId);
        System.out.println("Las lista que llega al OrderHandler de Response");
        orderDishResponsesList.forEach(orderDishResponse -> System.out.println(orderDishResponse.toString()));

        if (orderDishResponsesList != null) {
            for (OrderDishResponse orderDishResponse : orderDishResponsesList) {
                DishResponse dishResponse  = dishHandler.getDish(orderDishResponse.getDishId());
                orderDishResponse.setDishId(dishResponse.getId());
                orderDishResponse.setDescription(dishResponse.getDescription());
                orderDishResponse.setName(dishResponse.getName());
                orderDishResponse.setCategoryId(dishResponse.getCategoryId());
                orderDishResponse.setPrice(dishResponse.getPrice());
                orderDishResponse.setImageUrl(dishResponse.getImageUrl());
                System.out.println("El isActive es " + dishResponse.isActive());
                orderDishResponse.setIsActive(dishResponse.isActive());
                orderDishResponse.setQuantity(orderDishResponse.getQuantity());
                dishOrderList.add(orderDishResponse);
            }
        }

        return dishOrderList;
    }


    @Override
    public Page<CompleteOrderResponse> getAllOrders (OrderStatus orderStatus , Pageable pageable){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String token = (String) authentication.getCredentials();
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();
        Long userId = claims.get("id", Long.class);
        System.out.println("El userId es " + userId);
        EmployeeRestaurantResponse employeeRestaurant = employeeRestaurantHandler.getEmployeeRestaurant(userId);
        System.out.println("El employeeRestaurant response es " + employeeRestaurant.toString());
        Long restaurantId = employeeRestaurantHandler.getEmployeeRestaurant(userId).getRestaurantId();
        System.out.println("El restaurantId es " + restaurantId);
        Page<Order> orders = iOrderServicePort.getAllOrders(restaurantId,orderStatus,pageable);

        System.out.println("Las orders en Handler");
        orders.forEach(order -> System.out.println(order.toString()));

        List<CompleteOrderResponse> modifiedOrderResponses = new ArrayList<>();
        for (Order order : orders) {
            CompleteOrderResponse response = orderRequestMapper.toResponse(order);
            RestaurantResponse restaurant  = restaurantHandler.getRestaurant(response.getRestaurantId());
            UserResponse userResponse = userHandler.getUser(response.getCustomerId());
            response.setRestaurantName(restaurant.getName());
            response.setDocumentIdCustomer(userResponse.getDocumentId());
            response.setDishes(this.getPlatosForOrder(order.getId()));
            response.setFullNameCustomer(userResponse.getName() +" "+ userResponse.getLastName());
            modifiedOrderResponses.add(response);
        }
        return new PageImpl<>(modifiedOrderResponses, pageable, orders.getTotalElements());
    }


    @Override
    public void saveCompleteOrder (CompleteOrderRequest completeOrderRequest){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String token = (String) authentication.getCredentials();
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();
        Long userId = claims.get("id", Long.class);
        if (!this.hasPendingOrders(userId)) {
            Order order = orderRequestMapper.toOrder(completeOrderRequest);
            OrderRequest orderRequest = orderRequestMapper.toOrderRequest(completeOrderRequest);
            orderRequest.setOrderStatus(OrderStatus.PENDING);
            orderRequest.setDate(LocalDate.now());
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
}
