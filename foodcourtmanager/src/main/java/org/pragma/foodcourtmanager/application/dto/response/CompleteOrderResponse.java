package org.pragma.foodcourtmanager.application.dto.response;

import lombok.Getter;
import lombok.Setter;
import org.pragma.foodcourtmanager.application.dto.request.OrderDishRequest;
import org.pragma.foodcourtmanager.domain.model.OrderStatus;

import java.util.List;

@Getter
@Setter
public class CompleteOrderResponse{
    private Long restaurantId;
    private String restaurantName;
    private List<OrderDishResponse> dishes;
    private Long chefId;
    private Long customerId;
    private OrderStatus orderStatus;
    private String documentIdCustomer;
    private String fullNameCustomer;
}
