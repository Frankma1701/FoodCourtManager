package org.pragma.foodcourtmanager.application.handler;

import org.pragma.foodcourtmanager.application.dto.request.*;
import org.pragma.foodcourtmanager.application.dto.response.DishResponse;
import org.pragma.foodcourtmanager.domain.model.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IOrderHandler{

     Order saveOrder(OrderRequest orderRequest);

     boolean hasPendingOrders(Long userId);


          void saveCompleteOrder(CompleteOrderRequest completeOrderRequest);
}
