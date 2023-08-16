package org.pragma.foodcourtmanager.application.mapper.response;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import org.pragma.foodcourtmanager.application.dto.request.EmployeeRestaurantRequest;
import org.pragma.foodcourtmanager.application.dto.response.EmployeeRestaurantResponse;
import org.pragma.foodcourtmanager.domain.model.EmployeeRestaurant;

import java.util.List;


@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface EmployeeRestaurantResponseMapper{
    EmployeeRestaurant toEmployeeRestaurant(EmployeeRestaurantResponse employeeRestaurantResponse);

    @Mapping(target = "employeeId" , source = "employeeRestaurant.employeeId")
    @Mapping(target = "restaurantId" , source = "employeeRestaurant.restaurantId")
    EmployeeRestaurantResponse toResponse(EmployeeRestaurant employeeRestaurant);

    List<EmployeeRestaurant> toEmployeeRestaurantList(List<EmployeeRestaurantRequest> employeeRestaurantRequestList);

    // List<OrderDishResponse> toResponseList(List<EmployeeRestaurant> orderDishList);

}
