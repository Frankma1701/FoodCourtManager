package org.pragma.foodcourtmanager.application.exception;

public class NoEmployeeRestaurantFoundException extends RuntimeException{

    public NoEmployeeRestaurantFoundException () {
        super(ExceptionConstant.EMPLOYEE_RESTAURANT_NO_DATA);
    }
}
