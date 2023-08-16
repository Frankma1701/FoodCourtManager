package org.pragma.foodcourtmanager.application.handler;

import org.pragma.foodcourtmanager.application.dto.request.EmployeeRestaurantRequest;
import org.pragma.foodcourtmanager.application.dto.request.OrderDishRequest;
import org.pragma.foodcourtmanager.application.dto.response.EmployeeRestaurantResponse;
import org.pragma.foodcourtmanager.application.dto.response.OrderDishResponse;

import java.util.List;

public interface IEmployeeRestaurantHandler{

    void saveEmployeeRestaurant(EmployeeRestaurantRequest employeeRestaurantRequest);

    EmployeeRestaurantResponse getEmployeeRestaurant(Long id);

}
