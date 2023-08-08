package org.pragma.foodcourtmanager.application.handler;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.pragma.foodcourtmanager.application.dto.request.RestaurantRequest;
import org.pragma.foodcourtmanager.application.dto.response.RestaurantResponse;
import org.pragma.foodcourtmanager.application.mapper.request.RestaurantRequestMapper;
import org.pragma.foodcourtmanager.application.mapper.response.RestaurantResponseMapper;
import org.pragma.foodcourtmanager.domain.api.IRestaurantServicePort;
import org.pragma.foodcourtmanager.domain.model.Restaurant;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class RestaurantHandler implements IRestaurantHandler{

    private final IRestaurantServicePort iRestaurantServicePort;
    private final RestaurantRequestMapper restaurantRequestMapper;
    private final RestaurantResponseMapper restaurantResponseMapper;


    @Override
    public void saveRestaurant(RestaurantRequest restaurantRequest) {
        Restaurant restaurant = restaurantRequestMapper.toRestaurant(restaurantRequest);
        iRestaurantServicePort.saveRestaurant(restaurant);
    }

    @Override
    public List<RestaurantResponse> getAllRestaurants() {
        return restaurantResponseMapper.toResponseList(iRestaurantServicePort.getAllRestaurants());
    }

    @Override
    public RestaurantResponse getRestaurant(String nit) {
        Restaurant restaurant = iRestaurantServicePort.getRestaurant(nit);
        return restaurantResponseMapper.toResponse(restaurant);
    }

    @Override
    public void updateRestaurant(RestaurantRequest restaurantRequest) {
        Restaurant oldRestaurant = iRestaurantServicePort.getRestaurant(restaurantRequest.getNit());
        Restaurant newRestaurant = restaurantRequestMapper.toRestaurant(restaurantRequest);
        newRestaurant.setId(oldRestaurant.getId());
        iRestaurantServicePort.updateRestaurant(newRestaurant);
    }

    @Override
    public void deleteRestaurant(String nit) {
        Restaurant restaurant = iRestaurantServicePort.getRestaurant(nit);
        iRestaurantServicePort.deleteRestaurant(restaurant.getNit());
    }


}
