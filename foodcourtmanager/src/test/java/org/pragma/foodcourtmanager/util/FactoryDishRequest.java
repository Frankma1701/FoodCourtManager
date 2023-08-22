package org.pragma.foodcourtmanager.util;

import org.pragma.foodcourtmanager.application.dto.request.DishRequest;
import org.pragma.foodcourtmanager.domain.model.EmployeeRestaurant;

public class FactoryDishRequest{

    public static final DishRequest mockObject = new DishRequest("Plato 1",1L,"Descripción del plato 1",25000.0,
            "example.png",true,"12345");


}
