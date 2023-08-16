package org.pragma.foodcourtmanager.domain.spi;

import org.pragma.foodcourtmanager.domain.model.EmployeeRestaurant;
import org.pragma.foodcourtmanager.domain.model.OrderDish;

import java.util.List;

public interface IEmployeeRestaurntPersistencePort{
    EmployeeRestaurant saveEmployeeRestaurant (EmployeeRestaurant employeeRestaurant);

    EmployeeRestaurant getEmployeeRestaurant (Long employeeId);

}
