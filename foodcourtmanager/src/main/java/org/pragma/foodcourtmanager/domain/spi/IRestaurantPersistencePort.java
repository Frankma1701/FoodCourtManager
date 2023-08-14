package org.pragma.foodcourtmanager.domain.spi;

import org.pragma.foodcourtmanager.domain.model.Restaurant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IRestaurantPersistencePort{
    Restaurant saveRestaurant(Restaurant restaurant);
    Page<Restaurant> getAllRestaurants(Pageable pageable);

    Restaurant getRestaurant(String nit);

    Restaurant getRestaurant(Long restaurantId);

    void updateRestaurant(Restaurant restaurant);

    void deleteRestaurant(String nit);
}
