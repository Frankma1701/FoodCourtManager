package org.pragma.foodcourtmanager.application.dto.request;

import lombok.Getter;
import lombok.Setter;
import org.pragma.foodcourtmanager.domain.model.OrderStatus;
import java.time.LocalDate;

@Getter
@Setter
public class OrderRequest{

    private Long id;
    private Long customerId;
    private LocalDate date;
    private OrderStatus orderStatus;
    private Long chefId;
    private Long restaurantId;
}
