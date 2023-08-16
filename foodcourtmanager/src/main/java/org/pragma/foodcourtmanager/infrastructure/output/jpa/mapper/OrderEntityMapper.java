package org.pragma.foodcourtmanager.infrastructure.output.jpa.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import org.pragma.foodcourtmanager.domain.model.Dish;
import org.pragma.foodcourtmanager.domain.model.Order;
import org.pragma.foodcourtmanager.domain.model.Restaurant;
import org.pragma.foodcourtmanager.infrastructure.output.jpa.entity.DishEntity;
import org.pragma.foodcourtmanager.infrastructure.output.jpa.entity.OrderEntity;
import org.pragma.foodcourtmanager.infrastructure.output.jpa.entity.RestaurantEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface OrderEntityMapper{

    @Mapping(target = "restaurantEntity.id",source = "order.restaurantId")
    OrderEntity toEntity(Order order);

    @Mapping(target = "restaurantId" , source = "orderEntity.restaurantEntity.id")
    Order toOrder(OrderEntity orderEntity);

    default Page<Order> toOrderPage (Page<OrderEntity> orderEntityPage) {
        List<Order> orders = orderEntityPage.stream()
                .map(this::toOrder)
                .toList();
        return new PageImpl<>(orders);
    }
}
