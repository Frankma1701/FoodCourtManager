package org.pragma.foodcourtmanager.infrastructure.exception;

public class ExceptionConstant{

    public static final String RESTAURANT_ALREADY_EXIST = "El restaurante con el nit validado ya existe";
    public static final String RESTAURANT_NOT_FOUND = "El restaurante con el nit validado no se encuentra registrado";

    public static final String NOT_OWNER_USER = "El usuario validado no tiene el rol de propietario";
    public static final String NOT_OWNER_OF_RESTAURANT = "El usuario validado no es dueño del restaurante al que se desea agregar el plato";

    public static final String RESTAURANT_NO_DATA = "No existen datos para mostrar en el restaurante";
    public static final String ACTIVE_ORDER_USER = "El usuario tiene pedidos en proceso actualmente por lo tanto no puede crear nuevos pedidos";

    public static final String DISH_ALREADY_EXIST = "El plato con el id validado ya existe";
    public static final String DISH_NOT_FOUND = "El plato con el id validado no se encuentra registrado";
    public static final String DISH_NO_DATA = "No existen datos para mostrar en los platos";


}
