package org.pragma.foodcourtmanager.infrastructure.exception;

public class DishAlreadyExistException extends RuntimeException{

    public DishAlreadyExistException () {
        super(ExceptionConstant.DISH_ALREADY_EXIST);
    }
}
