package org.pragma.foodcourtmanager.application.handler;

import org.pragma.foodcourtmanager.application.dto.request.*;
import org.pragma.foodcourtmanager.application.dto.response.CompleteOrderResponse;
import org.pragma.foodcourtmanager.application.dto.response.DishResponse;
import org.pragma.foodcourtmanager.application.dto.response.OrderResponse;
import org.pragma.foodcourtmanager.domain.model.Order;
import org.pragma.foodcourtmanager.domain.model.OrderStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IOrderHandler{

     Order saveOrder (OrderRequest orderRequest);

     boolean hasPendingOrders (Long userId);

      void assignOrder (OrderUpdateRequest orderUpdateRequest);

    void orderReady (OrderUpdateRequest orderUpdateRequest);
   void deliverOrder (OrderValidatePinRequest orderValidatePinRequest);

    void cancelOrder (OrderUpdateRequest orderUpdateRequest);


    OrderResponse getOrder (Long orderId);


    void saveCompleteOrder (CompleteOrderRequest completeOrderRequest);

     Page<CompleteOrderResponse> getAllOrders (OrderStatus orderStatus, Pageable pageable);
}
