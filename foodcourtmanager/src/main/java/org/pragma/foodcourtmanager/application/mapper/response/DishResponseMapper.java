package org.pragma.foodcourtmanager.application.mapper.response;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import org.pragma.foodcourtmanager.application.dto.response.DishResponse;
import org.pragma.foodcourtmanager.application.dto.response.RestaurantListResponse;
import org.pragma.foodcourtmanager.application.dto.response.RestaurantResponse;
import org.pragma.foodcourtmanager.domain.model.Dish;
import org.pragma.foodcourtmanager.domain.model.Restaurant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface DishResponseMapper{

    DishResponse toResponse(Dish dish);

    default Page<DishResponse> toResponseList(Page<Dish> dishPage) {


        List<DishResponse> dishResponseList = dishPage.getContent().stream()
                .map(dish -> {
                    DishResponse dishResponse = new DishResponse();
                    dishResponse.setId(dish.getId());
                    dishResponse.setName(dish.getName());
                    dishResponse.setPrice(dish.getPrice());
                    dishResponse.setCategoryId(dish.getCategoryId());
                    dishResponse.setDescription(dish.getDescription());
                    dishResponse.setRestaurantId(dish.getRestaurantId());
                    dishResponse.setImageUrl(dish.getImageUrl());
                    dishResponse.setActive(dish.getActive());
                    return dishResponse;
                })
                .toList();

        return new PageImpl<>(dishResponseList, dishPage.getPageable(), dishPage.getTotalElements());
    }


}
