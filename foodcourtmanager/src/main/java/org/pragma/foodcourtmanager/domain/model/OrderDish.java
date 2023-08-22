package org.pragma.foodcourtmanager.domain.model;

public class OrderDish {

    private Long orderId;
    private Long dishId;

    private Long quantity;

    public OrderDish (Long orderId, Long dishId, Long quantity){
        this.orderId = orderId;
        this.dishId = dishId;
        this.quantity = quantity;
    }

    public Long getOrderId (){
        return orderId;
    }

    public void setOrderId (Long orderId){
        this.orderId = orderId;
    }

    public Long getDishId (){
        return dishId;
    }

    public void setDishId (Long dishId){
        this.dishId = dishId;
    }

    public Long getQuantity (){
        return quantity;
    }

    public void setQuantity (Long quantity){
        this.quantity = quantity;
    }


}
