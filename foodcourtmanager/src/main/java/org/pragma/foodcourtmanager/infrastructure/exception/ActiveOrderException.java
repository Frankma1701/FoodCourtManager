package org.pragma.foodcourtmanager.infrastructure.exception;

public class ActiveOrderException extends RuntimeException{

    public ActiveOrderException () {
        super(ExceptionConstant.ACTIVE_ORDER_USER);
    }
}
