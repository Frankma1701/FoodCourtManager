package org.pragma.foodcourtmanager.application.handler;

import org.pragma.foodcourtmanager.application.dto.request.RestaurantRequest;
import org.pragma.foodcourtmanager.application.dto.response.RestaurantResponse;

import java.util.List;

public interface IRestaurantHandler{

    void saveRestaurant(RestaurantRequest userRequest);
    List<RestaurantResponse> getAllRestaurants();

    RestaurantResponse getRestaurant(String documentId);

    RestaurantResponse getRestaurant(Long restaurantId);

    void updateRestaurant(RestaurantRequest userRequest);

    void deleteRestaurant(String documentId);
}
