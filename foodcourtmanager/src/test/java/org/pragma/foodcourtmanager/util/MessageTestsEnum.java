package org.pragma.foodcourtmanager.util;

public enum MessageTestsEnum{

    ID_ORDER_EQUAL("Los ids de los pedidos son iguales"),
    CUSTOMER_ID_ORDER_EQUAL("Los id de los clientes son iguales"),
    DATE_ORDER_EQUAL("Las fechas del pedido son iguales"),
    ORDER_STATUS_EQUAL ("Los estados de la orden son igua);es son iguales"),
    CHEF_ID_ORDER_EQUAL("Los ids de los chefs son iguales"),
    RESTAURANT_ID_ORDER_EQUAL("Los ids de los restaurantes son iguales"),
    ORDER_ID_DEBIL_ENTITY_EQUAL("Los ids de los pedidos son iguales"),
    DISH_ID_DEBIL_ENTITY_EQUAL("Los ids de los platos son iguales"),
    QUANTITY_DEBIL_ENTITY_EQUAL("Las cantidades son iguales");

    private final String message;
    MessageTestsEnum (String message ){
        this.message = message;
    }
    public String getMessage(){
        return this.message;
    }

}
