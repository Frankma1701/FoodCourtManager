package org.pragma.foodcourtmanager.infrastructure.exception;

public class DishNotFoundException extends RuntimeException{

    public DishNotFoundException () {
        super(ExceptionConstant.DISH_NOT_FOUND);
    }
}
