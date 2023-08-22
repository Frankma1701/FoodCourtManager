package org.pragma.foodcourtmanager.application.exception;

public class NoDataOrderFoundException extends RuntimeException{

    public NoDataOrderFoundException () {
        super(ExceptionConstant.ORDER_NO_DATA);
    }
}
