package org.pragma.foodcourtmanager.application.dto.request;

import lombok.Getter;
import lombok.Setter;
import org.pragma.foodcourtmanager.domain.model.OrderStatus;

@Getter
@Setter
public class OrderUpdateRequest{

    private Long orderId;
}
