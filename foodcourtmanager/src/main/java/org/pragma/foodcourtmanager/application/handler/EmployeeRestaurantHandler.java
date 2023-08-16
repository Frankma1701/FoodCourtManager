package org.pragma.foodcourtmanager.application.handler;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.pragma.foodcourtmanager.application.dto.request.EmployeeRestaurantRequest;
import org.pragma.foodcourtmanager.application.dto.request.OrderDishRequest;
import org.pragma.foodcourtmanager.application.dto.response.EmployeeRestaurantResponse;
import org.pragma.foodcourtmanager.application.dto.response.OrderDishResponse;
import org.pragma.foodcourtmanager.application.mapper.request.EmployeeRestaurantRequestMapper;
import org.pragma.foodcourtmanager.application.mapper.request.OrderDishRequestMapper;
import org.pragma.foodcourtmanager.application.mapper.response.EmployeeRestaurantResponseMapper;
import org.pragma.foodcourtmanager.domain.api.IEmployeeRestaurantServicePort;
import org.pragma.foodcourtmanager.domain.api.IOrderDishServicePort;
import org.pragma.foodcourtmanager.domain.model.OrderDish;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class EmployeeRestaurantHandler implements IEmployeeRestaurantHandler{

    private final IEmployeeRestaurantServicePort iEmployeeRestaurantServicePort;
    private final EmployeeRestaurantRequestMapper employeeRestaurantRequestMapper;
    private final EmployeeRestaurantResponseMapper employeeRestaurantResponse;


    @Override
    public void saveEmployeeRestaurant (EmployeeRestaurantRequest employeeRestaurantRequest){
        iEmployeeRestaurantServicePort.saveEmployeeRestaurant(employeeRestaurantRequestMapper.toEmployeeRestaurant(employeeRestaurantRequest));
    }

    @Override
    public EmployeeRestaurantResponse getEmployeeRestaurant (Long id){
        return employeeRestaurantResponse.toResponse(iEmployeeRestaurantServicePort.getEmployeeRestaurant(id));


    }
}
