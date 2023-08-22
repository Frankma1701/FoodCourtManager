package org.pragma.foodcourtmanager.infrastructure.output.jpa.adapter;

import lombok.RequiredArgsConstructor;
import org.pragma.foodcourtmanager.domain.model.EmployeeRestaurant;
import org.pragma.foodcourtmanager.domain.spi.IEmployeeRestaurntPersistencePort;
import org.pragma.foodcourtmanager.application.exception.NoEmployeeRestaurantFoundException;
import org.pragma.foodcourtmanager.infrastructure.output.jpa.entity.EmployeeRestaurantEntity;
import org.pragma.foodcourtmanager.infrastructure.output.jpa.mapper.EmployeeRestaurantEntityMapper;
import org.pragma.foodcourtmanager.infrastructure.output.jpa.repository.IEmployeeRestaurantRepository;

@RequiredArgsConstructor
public class EmployeeRestaurantJpaAdapter implements IEmployeeRestaurntPersistencePort{

    private final IEmployeeRestaurantRepository iEmployeeRestaurantRepository;
    private final EmployeeRestaurantEntityMapper employeeRestaurantRequestMapper;


    @Override
    public EmployeeRestaurant saveEmployeeRestaurant (EmployeeRestaurant employeeRestaurant){
        return employeeRestaurantRequestMapper.toEmployeeRestaurant(iEmployeeRestaurantRepository.save(employeeRestaurantRequestMapper.toEntity(employeeRestaurant)));
    }

    @Override
    public EmployeeRestaurant getEmployeeRestaurant (Long employeeId){
        EmployeeRestaurantEntity employeeRestaurant = iEmployeeRestaurantRepository.findByIdEmployeeId(employeeId);
        if(employeeRestaurant!=null){
            return employeeRestaurantRequestMapper.toEmployeeRestaurant(employeeRestaurant);

        }else{
            throw new NoEmployeeRestaurantFoundException();
        }
    }
}
