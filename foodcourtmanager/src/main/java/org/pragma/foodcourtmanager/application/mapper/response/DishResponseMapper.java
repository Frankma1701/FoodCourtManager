package org.pragma.foodcourtmanager.application.mapper.response;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import org.pragma.foodcourtmanager.application.dto.response.DishResponse;
import org.pragma.foodcourtmanager.application.dto.response.RestaurantResponse;
import org.pragma.foodcourtmanager.domain.model.Dish;
import org.pragma.foodcourtmanager.domain.model.Restaurant;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface DishResponseMapper{

    DishResponse toResponse(Dish dish);

    default List<DishResponse> toResponseList(List<Dish> dishList) {
        return dishList.stream()
                .map(dish -> {
                    DishResponse dishResponse = new DishResponse();
                    dishResponse.setName(dish.getName());
                    dishResponse.setCategoryId(dish.getCategoryId());
                    dishResponse.setDescription(dish.getDescription());
                    dishResponse.setPrice(dish.getPrice());
                    dishResponse.setRestaurantId(dish.getRestaurantId());
                    dishResponse.setImageUrl(dish.getImageUrl());
                    dishResponse.setIsActive(dish.getActive());
                    return dishResponse;
                }).toList();
    }


}
