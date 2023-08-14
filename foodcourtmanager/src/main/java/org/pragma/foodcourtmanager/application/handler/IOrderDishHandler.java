package org.pragma.foodcourtmanager.application.handler;

import org.pragma.foodcourtmanager.application.dto.request.OrderDishRequest;
public interface IOrderDishHandler{

    void saveOrderDish(OrderDishRequest orderDishRequest);

}
