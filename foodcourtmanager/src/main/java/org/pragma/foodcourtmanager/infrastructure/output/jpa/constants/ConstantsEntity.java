package org.pragma.foodcourtmanager.infrastructure.output.jpa.constants;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Positive;

public class ConstantsEntity{

    public static final String NOT_NULL_NAME_RESTAURANT= "El restaurante no puede tener el nombre nulo";
    public static final String NOT_BLANK_NAME_RESTAURANT = "El restaurante no puede tener el nombre vacio";
    public static final String NOT_NULL_ADDRESS_RESTAURANT= "El restaurante no puede tener la dirección nula";
    public static final String NOT_BLANK_ADDRESS_RESTAURANT = "El restaurante no puede tener la dirección vacia";
    public static final String NOT_NULL_OWNER_RESTAURANT= "El restaurante no puede tener el propietario nulo";
    public static final String NOT_BLANK_OWNER_RESTAURANT = "El restaurante no puede tener el propietario vacio";
    public static final String NOT_NULL_PHONE_NUMBER_RESTAURANT= "El restaurante no puede tener el numero de telefono nulo";
    public static final String NOT_BLANK_PHONE_NUMBER_RESTAURANT = "El restaurante no puede tener el numero de telefono vacio";
    public static final String NOT_NULL_URL_LOGO_RESTAURANT= "El restaurante no puede tener la url del logo nula";
    public static final String NOT_BLANK_URL_LOGO_RESTAURANT = "El restaurante no puede tener la url del logo vacia";
    public static final String NOT_NULL_NIT_RESTAURANT= "El restaurante no puede tener el nit nulo";
    public static final String NOT_BLANK_NIT_RESTAURANT = "El restaurante no puede tener el nit vacio";
    public static final String REGEX_PHONE_NUMBER_RESTAURANT= "\\+?[0-9]+";
    public static final String NO_VALID_PHONE_NUMBER_RESTAURANT = "El numero de telefono del restaurante no es válido";
    public static final String NO_VALID_NIT_RESTAURANT = "El nit del restaurante no es válido";
    public static final String NO_VALID_NAME_RESTAURANT = "El nombre del restaurante no es válido";

    public static final String REGEX_NIT_RESTAURANT = "\\d+";

    public static final String REGEX_NAME_RESTAURANT = "^(?=.*[a-zA-Z]).+$";








    public static final String NOT_NULL_NAME_DISH= "El plato no puede tener el nombre nulo";
    public static final String NOT_BLANK_NAME_DISH = "El plato no puede tener el nombre vacio";

    public static final String NOT_NULL_CATEGORY_DISH= "El plato no puede tener id de la categoria nulo";
    public static final String NOT_BLANK_CATEGORY_DISH = "El plato no puede tener el id de la categoria vacio";
    public static final String NOT_NULL_DESCRIPTION_DISH= "El plato no puede tener la descripción nula";
    public static final String NOT_BLANK_DESCRIPTION_DISH = "El plato no puede tener la descripción vacia";

    public static final String NOT_NULL_PRICE_DISH= "El plato no puede tener el precio nulo";
    public static final String NOT_BLANK_PRICE_DISH = "El plato no puede tener el precio vacio";

    public static final String NOT_NULL_URL_IMAGE_DISH= "El plato no puede tener la url de la imagen nula";
    public static final String NOT_BLANK_URL_IMAGE_DISH = "El plato no puede tener la url de la imagen vacia";

    public static final String INTEGER_PRICE_DISH = "El precio del plato debe ser un numero entero";
    public static final String NOT_ZERO_PRICE_DISH = "El precio del plato debe ser mayor a cero";



}
