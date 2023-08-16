package org.pragma.foodcourtmanager.application.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeRestaurantResponse{
    private Long employeeId;
    private Long restaurantId;

    @Override
    public String toString (){
        return "EmployeeRestaurantResponse{" +
                "employeeId=" + employeeId +
                ", restaurantId=" + restaurantId +
                '}';
    }
}
