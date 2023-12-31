package org.pragma.foodcourtmanager.application.mapper.request;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import org.pragma.foodcourtmanager.application.dto.request.RestaurantRequest;
import org.pragma.foodcourtmanager.domain.model.Restaurant;


@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface RestaurantRequestMapper{


    @Mapping(target = "ownerId" , source = "restaurantRequest.documentId")
    Restaurant toRestaurant(RestaurantRequest restaurantRequest);
}
