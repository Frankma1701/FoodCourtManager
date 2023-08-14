package org.pragma.foodcourtmanager.application.handler;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.pragma.foodcourtmanager.application.dto.request.RestaurantRequest;
import org.pragma.foodcourtmanager.application.dto.response.RestaurantListResponse;
import org.pragma.foodcourtmanager.application.dto.response.RestaurantResponse;
import org.pragma.foodcourtmanager.application.dto.response.UserResponse;
import org.pragma.foodcourtmanager.application.mapper.request.RestaurantRequestMapper;
import org.pragma.foodcourtmanager.application.mapper.response.RestaurantResponseMapper;
import org.pragma.foodcourtmanager.domain.api.IRestaurantServicePort;
import org.pragma.foodcourtmanager.domain.model.Restaurant;
import org.pragma.foodcourtmanager.infrastructure.exception.NotOwnerUserException;
import org.pragma.foodcourtmanager.infrastructure.exception.RestaurantAlreadyExistException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class RestaurantHandler implements IRestaurantHandler{

    private final IRestaurantServicePort iRestaurantServicePort;
    private final RestaurantRequestMapper restaurantRequestMapper;
    private final RestaurantResponseMapper restaurantResponseMapper;
    private final UserHandler userHandler;


    @Override
    public void saveRestaurant(RestaurantRequest restaurantRequest) {
        UserResponse userResponse = userHandler.getUserFromApi(restaurantRequest.getDocumentId());
        if(userResponse.getRoleId() == 2){
            restaurantRequest.setDocumentId( String.valueOf(userResponse.getId()));
            Restaurant restaurant = restaurantRequestMapper.toRestaurant(restaurantRequest);
            System.out.println("Los datos del restaurante son : ");
            System.out.println(restaurant.toString());
            iRestaurantServicePort.saveRestaurant(restaurant);
        }else{
            throw new NotOwnerUserException();

        }

    }

    @Override
    public Page<RestaurantListResponse> getAllRestaurants(Pageable pageable) {
        return restaurantResponseMapper.toResponseList(iRestaurantServicePort.getAllRestaurants(pageable));
    }

    @Override
    public RestaurantResponse getRestaurant(String nit) {
        Restaurant restaurant = iRestaurantServicePort.getRestaurant(nit);
        return restaurantResponseMapper.toResponse(restaurant);
    }

    @Override
    public RestaurantResponse getRestaurant (Long restaurantId){
        Restaurant restaurant = iRestaurantServicePort.getRestaurant(restaurantId);
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
