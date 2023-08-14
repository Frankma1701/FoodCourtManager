package org.pragma.foodcourtmanager.infrastructure.output.jpa.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import org.pragma.foodcourtmanager.domain.model.Restaurant;
import org.pragma.foodcourtmanager.infrastructure.output.jpa.entity.RestaurantEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface RestaurantEntityMapper{

    RestaurantEntity toEntity(Restaurant restaurant);
    Restaurant toRestaurant(RestaurantEntity restaurantEntity);

     default Page<Restaurant> toRestaurantPage (Page<RestaurantEntity> restaurantEntityList) {
        List<Restaurant> restaurants = restaurantEntityList.stream()
                .map(this::toRestaurant)
                .toList();

        return new PageImpl<>(restaurants);
    }
}
