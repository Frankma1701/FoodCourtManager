package org.pragma.foodcourtmanager.infrastructure.exception;

public class RestaurantNotFoundException extends RuntimeException{

    public RestaurantNotFoundException () {
        super(ExceptionConstant.RESTAURANT_NOT_FOUND);
    }
}
