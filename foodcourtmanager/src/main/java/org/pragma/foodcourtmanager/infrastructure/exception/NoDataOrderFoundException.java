package org.pragma.foodcourtmanager.infrastructure.exception;

public class NoDataOrderFoundException extends RuntimeException{

    public NoDataOrderFoundException () {
        super(ExceptionConstant.ORDER_NO_DATA);
    }
}
