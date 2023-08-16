package org.pragma.foodcourtmanager.application.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CompleteOrderRequest{
    private Long restaurantId;
    private List<OrderDishRequest> dishes;
    //private Long employeeId;

    @Override
    public String toString (){
        return "CompleteOrderRequest{" +
                "restaurantId=" + restaurantId +
                ", dishes=" + dishes +
               // ", employeeId=" + employeeId +
                '}';
    }
}
