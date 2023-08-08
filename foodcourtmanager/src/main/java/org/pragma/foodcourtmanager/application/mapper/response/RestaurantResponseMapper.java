package org.pragma.foodcourtmanager.application.mapper.response;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import org.pragma.foodcourtmanager.application.dto.response.RestaurantResponse;
import org.pragma.foodcourtmanager.domain.model.Restaurant;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface RestaurantResponseMapper{

    RestaurantResponse toResponse(Restaurant restaurant);

    default List<RestaurantResponse> toResponseList(List<Restaurant> restaurantList) {
        restaurantList.get(0).getName();
        return restaurantList.stream()
                .map(restaurant -> {
                    RestaurantResponse restaurantResponse = new RestaurantResponse();
                    restaurantResponse.setName(restaurant.getName());
                    restaurantResponse.setAddress(restaurant.getAddress());
                    restaurantResponse.setOwnerId(restaurant.getOwnerId());
                    restaurantResponse.setPhoneNumber(restaurant.getPhoneNumber());
                    restaurantResponse.setLogoUrl(restaurant.getLogoUrl());
                    restaurantResponse.setNit(restaurant.getNit());
                    return restaurantResponse;
                }).toList();
    }


}
