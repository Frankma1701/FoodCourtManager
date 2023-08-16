package org.pragma.foodcourtmanager.application.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeRestaurantRequest{
    private Long employeeId;
    private Long restaurantId;
}
