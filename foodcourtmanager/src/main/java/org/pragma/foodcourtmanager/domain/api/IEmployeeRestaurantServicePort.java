package org.pragma.foodcourtmanager.domain.api;
import org.pragma.foodcourtmanager.domain.model.EmployeeRestaurant;
import org.pragma.foodcourtmanager.domain.model.OrderDish;

import java.util.List;

public interface IEmployeeRestaurantServicePort{

    EmployeeRestaurant saveEmployeeRestaurant (EmployeeRestaurant employeeRestaurant);

    EmployeeRestaurant getEmployeeRestaurant (Long employeeId);


}
