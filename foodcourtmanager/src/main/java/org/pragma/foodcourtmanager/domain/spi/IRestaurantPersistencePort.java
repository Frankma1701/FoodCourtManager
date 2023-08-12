package org.pragma.foodcourtmanager.domain.spi;

import org.pragma.foodcourtmanager.domain.model.Restaurant;

import java.util.List;

public interface IRestaurantPersistencePort{
    Restaurant saveRestaurant(Restaurant restaurant);
    List<Restaurant> getAllRestaurants();

    Restaurant getRestaurant(String nit);

    Restaurant getRestaurant(Long restaurantId);

    void updateRestaurant(Restaurant restaurant);

    void deleteRestaurant(String nit);
}
