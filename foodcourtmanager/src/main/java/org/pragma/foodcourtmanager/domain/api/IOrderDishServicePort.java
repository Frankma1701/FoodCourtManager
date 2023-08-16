package org.pragma.foodcourtmanager.domain.api;
import org.pragma.foodcourtmanager.domain.model.OrderDish;

import java.util.List;

public interface IOrderDishServicePort{

    OrderDish saveOrderDish (OrderDish orderDish);

    List<OrderDish> getAllOrderDish(Long orderId);


}
