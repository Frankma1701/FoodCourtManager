package org.pragma.foodcourtmanager.application.mapper.request;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import org.pragma.foodcourtmanager.application.dto.request.EmployeeRestaurantRequest;
import org.pragma.foodcourtmanager.application.dto.request.OrderDishRequest;
import org.pragma.foodcourtmanager.application.dto.response.OrderDishResponse;
import org.pragma.foodcourtmanager.domain.model.EmployeeRestaurant;
import org.pragma.foodcourtmanager.domain.model.OrderDish;
import org.pragma.foodcourtmanager.domain.model.Restaurant;
import org.pragma.foodcourtmanager.infrastructure.output.jpa.entity.EmployeeRestaurantEntity;
import org.pragma.foodcourtmanager.infrastructure.output.jpa.entity.RestaurantEntity;

import java.util.List;


@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface EmployeeRestaurantRequestMapper{
    EmployeeRestaurant toEmployeeRestaurant(EmployeeRestaurantRequest employeeRestaurantRequest);

    EmployeeRestaurantRequest toResponse(EmployeeRestaurant employeeRestaurant);

    List<EmployeeRestaurant> toEmployeeRestaurantList(List<EmployeeRestaurantRequest> employeeRestaurantRequestList);

    // List<OrderDishResponse> toResponseList(List<EmployeeRestaurant> orderDishList);

}
