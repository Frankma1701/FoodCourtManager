package org.pragma.foodcourtmanager.application.handler;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.pragma.foodcourtmanager.application.dto.request.CompleteOrderRequest;
import org.pragma.foodcourtmanager.application.dto.request.OrderDishRequest;
import org.pragma.foodcourtmanager.application.dto.request.OrderRequest;
import org.pragma.foodcourtmanager.application.dto.request.RestaurantRequest;
import org.pragma.foodcourtmanager.application.dto.response.RestaurantListResponse;
import org.pragma.foodcourtmanager.application.dto.response.RestaurantResponse;
import org.pragma.foodcourtmanager.application.dto.response.UserResponse;
import org.pragma.foodcourtmanager.application.mapper.request.OrderDishRequestMapper;
import org.pragma.foodcourtmanager.application.mapper.request.OrderRequestMapper;
import org.pragma.foodcourtmanager.application.mapper.request.RestaurantRequestMapper;
import org.pragma.foodcourtmanager.application.mapper.response.RestaurantResponseMapper;
import org.pragma.foodcourtmanager.domain.api.IOrderDishServicePort;
import org.pragma.foodcourtmanager.domain.api.IOrderServicePort;
import org.pragma.foodcourtmanager.domain.api.IRestaurantServicePort;
import org.pragma.foodcourtmanager.domain.model.Order;
import org.pragma.foodcourtmanager.domain.model.OrderDish;
import org.pragma.foodcourtmanager.domain.model.OrderStatus;
import org.pragma.foodcourtmanager.domain.model.Restaurant;
import org.pragma.foodcourtmanager.infrastructure.exception.ActiveOrderException;
import org.pragma.foodcourtmanager.infrastructure.exception.NotOwnerUserException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderHandler implements IOrderHandler{
    private final IOrderServicePort iOrderServicePort;
    private final OrderRequestMapper orderRequestMapper;
    private final IOrderDishServicePort iOrderDishServicePort;
    private final OrderDishRequestMapper orderDishRequestMapper;
    @Value("${jwt.secret-key}")
    private String SECRET_KEY;


    @Override
    public Order saveOrder(OrderRequest orderRequest){
        return iOrderServicePort.saveOrder(orderRequestMapper.toOrder(orderRequest));
    }

    public boolean hasPendingOrders(Long userId) {
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

    @Override
    public void saveCompleteOrder(CompleteOrderRequest completeOrderRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
       String token = (String) authentication.getCredentials();
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();
        Long userId = claims.get("id", Long.class);
        if(!this.hasPendingOrders(userId)) {
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
        }else{
            throw new ActiveOrderException();
        }
    }
}
