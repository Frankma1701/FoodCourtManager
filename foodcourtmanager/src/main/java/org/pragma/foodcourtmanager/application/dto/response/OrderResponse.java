package org.pragma.foodcourtmanager.application.dto.response;

import lombok.Getter;
import lombok.Setter;
import org.pragma.foodcourtmanager.domain.model.OrderStatus;

import java.time.LocalDate;

@Getter
@Setter
public class OrderResponse{

    private Long id;
    private Long customerId;
    private LocalDate date;
    private OrderStatus orderStatus;
    private Long employeeId;
    private Long restaurantId;

}
