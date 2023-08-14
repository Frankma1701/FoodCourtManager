package org.pragma.foodcourtmanager.application.dto.request;

import lombok.Getter;
import lombok.Setter;
import org.pragma.foodcourtmanager.domain.model.OrderStatus;

import java.time.LocalDate;

@Getter
@Setter
public class OrderDishRequest{

    private Long dishId;
    private Long quantity;
}
