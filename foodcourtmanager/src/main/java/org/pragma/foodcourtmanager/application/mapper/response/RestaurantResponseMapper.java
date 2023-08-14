package org.pragma.foodcourtmanager.application.mapper.response;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import org.pragma.foodcourtmanager.application.dto.response.RestaurantListResponse;
import org.pragma.foodcourtmanager.application.dto.response.RestaurantResponse;
import org.pragma.foodcourtmanager.domain.model.Restaurant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface RestaurantResponseMapper{

    RestaurantResponse toResponse(Restaurant restaurant);

    default Page<RestaurantListResponse> toResponseList(Page<Restaurant> restaurantPage) {
        List<RestaurantListResponse> restaurantResponseList = restaurantPage.getContent().stream()
                .map(restaurant -> {
                    RestaurantListResponse restaurantResponse = new RestaurantListResponse();
                    restaurantResponse.setName(restaurant.getName());
                    restaurantResponse.setLogoUrl(restaurant.getLogoUrl());
                    return restaurantResponse;
                })
                .toList();

        return new PageImpl<>(restaurantResponseList, restaurantPage.getPageable(), restaurantPage.getTotalElements());
    }


}
