package org.pragma.foodcourtmanager.application.exception;

public class NotOwnerUserException extends RuntimeException{

    public NotOwnerUserException () {
        super(ExceptionConstant.NOT_OWNER_USER);
    }
}
