package org.pragma.foodcourtmanager.infrastructure.output.jpa.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import org.pragma.foodcourtmanager.domain.model.EmployeeRestaurant;
import org.pragma.foodcourtmanager.domain.model.OrderDish;
import org.pragma.foodcourtmanager.infrastructure.output.jpa.entity.EmployeeRestaurantEntity;
import org.pragma.foodcourtmanager.infrastructure.output.jpa.entity.OrderDishEntity;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface EmployeeRestaurantEntityMapper{

    @Mapping(target = "id.employeeId" , source = "employeeRestaurant.employeeId")
    @Mapping(target = "id.restaurantId" , source = "employeeRestaurant.restaurantId")
    @Mapping(target = "restaurantEntity.id" , source = "employeeRestaurant.restaurantId")
    EmployeeRestaurantEntity toEntity(EmployeeRestaurant employeeRestaurant);


    @Mapping(target = "employeeId" , source = "employeeRestaurantEntity.id.employeeId")
    @Mapping(target = "restaurantId" , source = "employeeRestaurantEntity.id.restaurantId")
    EmployeeRestaurant toEmployeeRestaurant(EmployeeRestaurantEntity employeeRestaurantEntity);


    /**default List<OrderDish> toOrderDishList(List<OrderDishEntity> orderDishEntityList) {
        List<OrderDish> orderDishList = orderDishEntityList.stream()
                .map(this::toOrderDish)
                .toList();

        return orderDishList;
    }**/

}
