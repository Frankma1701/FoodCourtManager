package org.pragma.foodcourtmanager.infrastructure.output.jpa.mapper;

import jakarta.persistence.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import org.pragma.foodcourtmanager.domain.model.OrderDish;
import org.pragma.foodcourtmanager.domain.model.Restaurant;
import org.pragma.foodcourtmanager.infrastructure.output.jpa.entity.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface OrderDishEntityMapper{
    @Mapping(target = "orderEntity.id" , source = "order.orderId")
    @Mapping(target = "dishEntity.id" , source = "order.dishId")
    @Mapping(target = "id.orderId" , source = "order.orderId")
    @Mapping(target = "id.dishId" , source = "order.dishId")
    OrderDishEntity toEntity(OrderDish order);

    @Mapping(target = "orderId" , source = "orderEntity.id")
    @Mapping(target = "dishId" , source = "dishEntity.id")
    @Mapping(target = "quantity" , source = "orderDishEntity.quantity")
    OrderDish toOrderDish(OrderDishEntity orderDishEntity);


    default List<OrderDish> toOrderDishList(List<OrderDishEntity> orderDishEntityList) {
        List<OrderDish> orderDishList = orderDishEntityList.stream()
                .map(this::toOrderDish)
                .toList();

        return orderDishList;
    }

}
