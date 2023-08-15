package org.pragma.foodcourtmanager.domain.api;
import org.pragma.foodcourtmanager.domain.model.Dish;
import org.pragma.foodcourtmanager.domain.model.Order;
import org.pragma.foodcourtmanager.domain.model.OrderDish;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IOrderServicePort{

    Order saveOrder (Order order);

    void saveCompleteOrder(Order order , List<OrderDish> orderDishList);

    List<Order> getOrdersByCustomerId (Long userId);
}
