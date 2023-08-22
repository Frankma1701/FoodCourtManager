package org.pragma.foodcourtmanager.util;

import org.pragma.foodcourtmanager.domain.model.Order;
import org.pragma.foodcourtmanager.domain.model.OrderStatus;
import org.pragma.foodcourtmanager.domain.model.Restaurant;

import java.time.LocalDateTime;

public class FactoryRestaurant{

    public static final Restaurant mockObject = new Restaurant(1L,"Restaurante 1","Plazoleta de comidas",
            1L,"12341","example.png","12345");

}
