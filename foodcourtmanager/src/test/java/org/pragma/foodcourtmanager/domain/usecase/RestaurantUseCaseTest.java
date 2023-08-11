package org.pragma.foodcourtmanager.domain.usecase;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.pragma.foodcourtmanager.domain.model.Restaurant;
import org.pragma.foodcourtmanager.domain.spi.IRestaurantPersistencePort;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class RestaurantUseCaseTest{
    @Mock
    private IRestaurantPersistencePort iRestaurantPersistencePort;

    @InjectMocks
    private RestaurantUseCase restaurantUseCase;

    private Restaurant mockObject;
    private Restaurant mockObject1;

    private Restaurant expectedObject;

    private Restaurant expectedUpdateObject;
    @BeforeEach
    void setUp (){
        mockObject = new Restaurant(1L, "Restaurante 1", "Dirección 1", 101L, "123456789", "url_logo_1", "001");
        mockObject1 = new Restaurant(2L, "Restaurante 2", "Dirección 2", 102L, "987654321", "url_logo_2", "002");
        expectedObject = new Restaurant(1L, "Restaurante 1", "Dirección 1", 101L, "123456789", "url_logo_1", "001");
        expectedUpdateObject = new Restaurant(4L, "Restaurante 4", "Dirección 4", 104L, "777777777", "url_logo_4", "004");
    }

    @Test
    void saveRestaurant (){
        Mockito.when(iRestaurantPersistencePort.saveRestaurant(mockObject)).thenReturn(mockObject);
        Restaurant resultObject = restaurantUseCase.saveRestaurant(mockObject);
        Assertions.assertEquals(expectedObject.getId(),resultObject.getId(), "Los ids de los restaurantes son iguales");
        Assertions.assertEquals(expectedObject.getName(),resultObject.getName(), "Los nombres de los restaurantes son iguales");
        Mockito.verify(iRestaurantPersistencePort).saveRestaurant(mockObject);
    }

    @Test
    void getAllRestaurants (){
        Mockito.when(iRestaurantPersistencePort.getAllRestaurants()).thenReturn(Arrays.asList(mockObject,mockObject1));
        Assertions.assertNotNull(restaurantUseCase.getAllRestaurants());
        restaurantUseCase.saveRestaurant(mockObject);
        restaurantUseCase.saveRestaurant(mockObject1);
        List<Restaurant> userList = restaurantUseCase.getAllRestaurants();
        Assertions.assertNotNull(userList);
        Assertions.assertEquals(2,userList.size());
    }

    @Test
    void getRestaurant (){
        Mockito.when(iRestaurantPersistencePort.getRestaurant("001")).thenReturn(mockObject);
        Restaurant resultObject = restaurantUseCase.getRestaurant("001");
        Assertions.assertEquals(expectedObject.getId(),resultObject.getId(), "Los ids de los restaurantes son iguales");
        Assertions.assertEquals(expectedObject.getName(),resultObject.getName(), "Los nombres de los restaurantes son iguales");
        Mockito.verify(iRestaurantPersistencePort).getRestaurant("001");
    }

    @Test
    void updateRestaurant (){
        restaurantUseCase.updateRestaurant(expectedUpdateObject);
        Mockito.verify(iRestaurantPersistencePort).updateRestaurant(expectedUpdateObject);
    }

    @Test
    void deleteRestaurant (){
        String nit = "123";
        restaurantUseCase.deleteRestaurant(nit);
        Mockito.verify(iRestaurantPersistencePort).deleteRestaurant(nit);
    }
}