package org.pragma.foodcourtmanager.domain.spi;

import org.pragma.foodcourtmanager.domain.model.Dish;
import org.pragma.foodcourtmanager.domain.model.OrderDish;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IOrderDishPersistencePort{
    OrderDish saveOrderDish (OrderDish orderDish);

    List<OrderDish> getAllOrderDish(Long orderId);
}
