package org.pragma.foodcourtmanager.infrastructure.exception;

public class RestaurantAlreadyExistException extends RuntimeException{

    public RestaurantAlreadyExistException () {
        super(ExceptionConstant.RESTAURANT_ALREADY_EXIST);
    }
}
