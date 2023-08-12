package org.pragma.foodcourtmanager.infrastructure.output.jpa.adapter;

import lombok.RequiredArgsConstructor;
import org.pragma.foodcourtmanager.domain.model.Restaurant;
import org.pragma.foodcourtmanager.domain.spi.IRestaurantPersistencePort;
import org.pragma.foodcourtmanager.infrastructure.exception.NoDataFoundException;
import org.pragma.foodcourtmanager.infrastructure.exception.RestaurantAlreadyExistException;
import org.pragma.foodcourtmanager.infrastructure.exception.RestaurantNotFoundException;
import org.pragma.foodcourtmanager.infrastructure.output.jpa.entity.RestaurantEntity;
import org.pragma.foodcourtmanager.infrastructure.output.jpa.mapper.RestaurantEntityMapper;
import org.pragma.foodcourtmanager.infrastructure.output.jpa.repository.IRestaurantRepository;

import java.util.List;

@RequiredArgsConstructor
public class RestaurantJpaAdapter implements IRestaurantPersistencePort{

    private final IRestaurantRepository iRestaurantRepository;
    private final RestaurantEntityMapper restaurantEntityMapper;


    @Override
    public Restaurant saveRestaurant(Restaurant restaurant) {
        System.out.println("Los datos del restaurante en el Jpa son : ");
        System.out.println(restaurant.toString());
        if(iRestaurantRepository.findByNit(restaurant.getNit()).isPresent()){
            throw new RestaurantAlreadyExistException();
        }
        RestaurantEntity restaurantEntity = iRestaurantRepository.save(restaurantEntityMapper.toEntity(restaurant));
        return restaurantEntityMapper.toRestaurant(restaurantEntity);
    }

    @Override
    public List<Restaurant> getAllRestaurants() {
        List<RestaurantEntity> restaurantEntityList = iRestaurantRepository.findAll();
        if(restaurantEntityList.isEmpty()){
            throw new NoDataFoundException();
        }
        return restaurantEntityMapper.toRestaurantList(restaurantEntityList);
    }

    @Override
    public Restaurant getRestaurant(String nit) {
        return restaurantEntityMapper.toRestaurant(iRestaurantRepository.findByNit(nit).orElseThrow(RestaurantNotFoundException::new));
    }

    @Override
    public Restaurant getRestaurant (Long restaurantId){
        return restaurantEntityMapper.toRestaurant(iRestaurantRepository.getReferenceById(restaurantId));
    }

    @Override
    public void updateRestaurant(Restaurant restaurant) {
        iRestaurantRepository.save(restaurantEntityMapper.toEntity(restaurant));
    }

    @Override
    public void deleteRestaurant(String nit) {
        iRestaurantRepository.deleteByNit(nit);
    }
}
