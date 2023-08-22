package org.pragma.foodcourtmanager.application.exception;

public class ActiveOrderException extends RuntimeException{

    public ActiveOrderException () {
        super(ExceptionConstant.ACTIVE_ORDER_USER);
    }
}
