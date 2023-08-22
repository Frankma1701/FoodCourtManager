package org.pragma.foodcourtmanager.domain.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.pragma.foodcourtmanager.util.FactoryRestaurant;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)

class RestaurantTest{

    @Test
    void testGetters() {
        Restaurant restaurant = FactoryRestaurant.mockObject;
        Long id = 1L;
        String name = "Restaurante 1";
        String address = "Plazoleta de comidas";
        Long ownerId = 1L;
        String phoneNumber = "12341";
        String logoUrl = "example.png";
        String nit = "12345";
        assertEquals(id, restaurant.getId());
        assertEquals(name, restaurant.getName());
        assertEquals(address, restaurant.getAddress());
        assertEquals(ownerId, restaurant.getOwnerId());
        assertEquals(phoneNumber, restaurant.getPhoneNumber());
        assertEquals(logoUrl, restaurant.getLogoUrl());
        assertEquals(nit, restaurant.getNit());
    }

    @Test
    void testSetters() {
        Restaurant restaurant = FactoryRestaurant.mockObject;
        Long id = 1L;
        String name = "Restaurante 2";
        String address = "Plazoleta de comidas Viva";
        Long ownerId = 2L;
        String phoneNumber = "5551234";
        String logoUrl = "https://example.com/logo.png";
        String nit = "123456789";
        restaurant.setId(id);
        restaurant.setName(name);
        restaurant.setAddress(address);
        restaurant.setOwnerId(ownerId);
        restaurant.setPhoneNumber(phoneNumber);
        restaurant.setLogoUrl(logoUrl);
        restaurant.setNit(nit);
        assertEquals(id, restaurant.getId());
        assertEquals(name, restaurant.getName());
        assertEquals(address, restaurant.getAddress());
        assertEquals(ownerId, restaurant.getOwnerId());
        assertEquals(phoneNumber, restaurant.getPhoneNumber());
        assertEquals(logoUrl, restaurant.getLogoUrl());
        assertEquals(nit, restaurant.getNit());
    }
}