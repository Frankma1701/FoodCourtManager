package org.pragma.foodcourtmanager.domain.usecase;

import org.pragma.foodcourtmanager.domain.api.IEmployeeRestaurantServicePort;
import org.pragma.foodcourtmanager.domain.api.IOrderDishServicePort;
import org.pragma.foodcourtmanager.domain.model.EmployeeRestaurant;
import org.pragma.foodcourtmanager.domain.model.OrderDish;
import org.pragma.foodcourtmanager.domain.spi.IEmployeeRestaurntPersistencePort;
import org.pragma.foodcourtmanager.domain.spi.IOrderDishPersistencePort;

import java.util.List;

public class EmployeeRestaurantUseCase implements IEmployeeRestaurantServicePort{

    private final IEmployeeRestaurntPersistencePort iEmployeeRestaurntPersistencePort;

    public EmployeeRestaurantUseCase (IEmployeeRestaurntPersistencePort iEmployeeRestaurntPersistencePort){
        this.iEmployeeRestaurntPersistencePort = iEmployeeRestaurntPersistencePort;
    }


    @Override
    public EmployeeRestaurant saveEmployeeRestaurant (EmployeeRestaurant employeeRestaurant){
        return iEmployeeRestaurntPersistencePort.saveEmployeeRestaurant(employeeRestaurant);

    }

    @Override
    public EmployeeRestaurant getEmployeeRestaurant (Long employeeId){
        return iEmployeeRestaurntPersistencePort.getEmployeeRestaurant(employeeId);
    }
}
