package org.pragma.foodcourtmanager.application.exception;

public class NotOwnerRestaurantUserException extends RuntimeException{

    public NotOwnerRestaurantUserException () {
        super(ExceptionConstant.NOT_OWNER_OF_RESTAURANT);
    }
}
