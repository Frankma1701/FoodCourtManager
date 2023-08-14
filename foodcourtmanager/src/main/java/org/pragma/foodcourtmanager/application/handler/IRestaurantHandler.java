package org.pragma.foodcourtmanager.application.handler;

import org.pragma.foodcourtmanager.application.dto.request.RestaurantRequest;
import org.pragma.foodcourtmanager.application.dto.response.RestaurantListResponse;
import org.pragma.foodcourtmanager.application.dto.response.RestaurantResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IRestaurantHandler{

    void saveRestaurant(RestaurantRequest userRequest);
    Page<RestaurantListResponse> getAllRestaurants(Pageable pageable);

    RestaurantResponse getRestaurant(String documentId);

    RestaurantResponse getRestaurant(Long restaurantId);

    void updateRestaurant(RestaurantRequest userRequest);

    void deleteRestaurant(String documentId);
}
