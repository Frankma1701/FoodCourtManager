package org.pragma.foodcourtmanager.infrastructure.output.jpa.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import org.pragma.foodcourtmanager.domain.model.OrderDish;
import org.pragma.foodcourtmanager.infrastructure.output.jpa.entity.OrderDishEntity;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface OrderDishEntityMapper{
    @Mapping(target = "orderEntity.id" , source = "order.orderId")
    @Mapping(target = "dishEntity.id" , source = "order.dishId")
    @Mapping(target = "id.orderId" , source = "order.orderId")
    @Mapping(target = "id.dishId" , source = "order.dishId")
    OrderDishEntity toEntity(OrderDish order);
    OrderDish toOrderDish(OrderDishEntity orderDishEntity);

}
