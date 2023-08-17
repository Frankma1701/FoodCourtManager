package org.pragma.foodcourtmanager.domain.spi;

import org.pragma.foodcourtmanager.application.dto.request.OrderValidatePinRequest;
import org.pragma.foodcourtmanager.application.dto.response.CompleteOrderResponse;
import org.pragma.foodcourtmanager.domain.model.Dish;
import org.pragma.foodcourtmanager.domain.model.Order;
import org.pragma.foodcourtmanager.domain.model.OrderDish;
import org.pragma.foodcourtmanager.domain.model.OrderStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IOrderPersistencePort{
    Order saveOrder (Order order);

    void saveCompleteOrder (Order order, List<OrderDish> orderDishList);

    List<Order> getOrdersByCustomerId (Long userId);

    void assignOrder (Order order);

    void orderReady (Order order);

    void deliverOrder (Order order);


    Order getOrder (Long orderId);


    Page<Order> getAllOrders (Long employeeId, OrderStatus orderStatus, Pageable pageable);


}
