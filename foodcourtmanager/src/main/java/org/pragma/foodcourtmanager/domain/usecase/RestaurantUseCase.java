package org.pragma.foodcourtmanager.domain.usecase;

import org.pragma.foodcourtmanager.domain.api.IRestaurantServicePort;
import org.pragma.foodcourtmanager.domain.model.Restaurant;
import org.pragma.foodcourtmanager.domain.spi.IRestaurantPersistencePort;

import java.util.List;
// Conection with the infrastructure

public class RestaurantUseCase implements IRestaurantServicePort{

    private IRestaurantPersistencePort iRestaurantPersistencePort;

    public RestaurantUseCase (IRestaurantPersistencePort iRestaurantPersistencePort) {
        this.iRestaurantPersistencePort = iRestaurantPersistencePort;
    }
    @Override
    public Restaurant saveRestaurant(Restaurant restaurant) {
        System.out.println("Los datos del restaurante son : ");
        System.out.println(restaurant.toString());
        return iRestaurantPersistencePort.saveRestaurant(restaurant);
    }
    @Override
    public List<Restaurant> getAllRestaurants() {
        return iRestaurantPersistencePort.getAllRestaurants();
    }
    @Override
    public Restaurant getRestaurant(String nit) {
        return iRestaurantPersistencePort.getRestaurant(nit);
    }

    @Override
    public Restaurant getRestaurant (Long restaurantId){
        return iRestaurantPersistencePort.getRestaurant(restaurantId);
    }

    @Override
    public void updateRestaurant(Restaurant restaurant) {
        iRestaurantPersistencePort.updateRestaurant(restaurant);
    }
    @Override
    public void deleteRestaurant(String nit) {
        iRestaurantPersistencePort.deleteRestaurant(nit);
    }
}
