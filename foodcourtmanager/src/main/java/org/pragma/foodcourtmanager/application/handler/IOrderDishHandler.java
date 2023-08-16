package org.pragma.foodcourtmanager.application.handler;

import org.pragma.foodcourtmanager.application.dto.request.OrderDishRequest;
import org.pragma.foodcourtmanager.application.dto.response.OrderDishResponse;

import java.util.List;

public interface IOrderDishHandler{

    void saveOrderDish(OrderDishRequest orderDishRequest);
   List<OrderDishResponse> getAllOrderDish(Long orderId);

}
