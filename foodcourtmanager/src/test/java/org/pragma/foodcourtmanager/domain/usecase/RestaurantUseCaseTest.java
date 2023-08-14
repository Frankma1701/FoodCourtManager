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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
        when(iRestaurantPersistencePort.saveRestaurant(mockObject)).thenReturn(mockObject);
        Restaurant resultObject = restaurantUseCase.saveRestaurant(mockObject);
        Assertions.assertEquals(expectedObject.getId(),resultObject.getId(), "Los ids de los restaurantes son iguales");
        Assertions.assertEquals(expectedObject.getName(),resultObject.getName(), "Los nombres de los restaurantes son iguales");
        verify(iRestaurantPersistencePort).saveRestaurant(mockObject);
    }

    @Test
    void testGetAllRestaurants() {
        Pageable pageable = Pageable.ofSize(10).withPage(0); // Example pageable
        Page<Restaurant> mockRestaurantPage = Mockito.mock(Page.class);
        when(iRestaurantPersistencePort.getAllRestaurants(pageable)).thenReturn(mockRestaurantPage);
        Page<Restaurant> result = restaurantUseCase.getAllRestaurants(pageable);
        Assertions.assertEquals(result.getTotalElements(), mockRestaurantPage.getTotalElements());
        verify(iRestaurantPersistencePort).getAllRestaurants(pageable);
    }

    @Test
    void getRestaurant (){
        when(iRestaurantPersistencePort.getRestaurant("001")).thenReturn(mockObject);
        Restaurant resultObject = restaurantUseCase.getRestaurant("001");
        Assertions.assertEquals(expectedObject.getId(),resultObject.getId(), "Los ids de los restaurantes son iguales");
        Assertions.assertEquals(expectedObject.getName(),resultObject.getName(), "Los nombres de los restaurantes son iguales");
        verify(iRestaurantPersistencePort).getRestaurant("001");
    }

    @Test
    void updateRestaurant (){
        restaurantUseCase.updateRestaurant(expectedUpdateObject);
        verify(iRestaurantPersistencePort).updateRestaurant(expectedUpdateObject);
    }

    @Test
    void deleteRestaurant (){
        String nit = "123";
        restaurantUseCase.deleteRestaurant(nit);
        verify(iRestaurantPersistencePort).deleteRestaurant(nit);
    }
}