package org.pragma.foodcourtmanager.domain.api;
import org.pragma.foodcourtmanager.domain.model.Dish;
import org.pragma.foodcourtmanager.domain.model.OrderDish;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IOrderDishServicePort{

    OrderDish saveOrderDish (OrderDish orderDish);


}
