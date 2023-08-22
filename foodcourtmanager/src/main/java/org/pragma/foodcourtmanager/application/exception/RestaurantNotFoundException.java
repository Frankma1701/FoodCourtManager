package org.pragma.foodcourtmanager.application.exception;

public class RestaurantNotFoundException extends RuntimeException{

    public RestaurantNotFoundException () {
        super(ExceptionConstant.RESTAURANT_NOT_FOUND);
    }
}
