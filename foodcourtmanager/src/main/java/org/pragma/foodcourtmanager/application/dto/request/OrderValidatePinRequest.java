package org.pragma.foodcourtmanager.application.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderValidatePinRequest{

    private Long orderId;
    private String pin;

}
