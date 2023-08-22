package org.pragma.foodcourtmanager.util;

import org.pragma.foodcourtmanager.application.dto.request.DishRequest;
import org.pragma.foodcourtmanager.domain.model.Dish;

public class FactoryDish{

    public static final Dish mockObject = new Dish(1L,"Plato 1",1L,"Descripción del plato 1",25000.0,
            1L,"example.png",true);


}
