package org.pragma.foodcourtmanager.domain.model;

import java.time.LocalDate;

public class Order {

    private Long id;
    private Long customerId;
    private LocalDate date;
    private OrderStatus orderStatus;
    private Long chefId;
    private Long restaurantId;

    public Order (Long id, Long customerId, LocalDate date, OrderStatus orderStatus, Long chefId, Long restaurantId){
        this.id = id;
        this.customerId = customerId;
        this.date = date;
        this.orderStatus = orderStatus;
        this.chefId = chefId;
        this.restaurantId = restaurantId;
    }

    public Long getId (){
        return id;
    }

    public void setId (Long id){
        this.id = id;
    }

    public Long getCustomerId (){
        return customerId;
    }

    public void setCustomerId (Long customerId){
        this.customerId = customerId;
    }

    public LocalDate getDate (){
        return date;
    }

    public void setDate (LocalDate date){
        this.date = date;
    }

    public OrderStatus getOrderStatus (){
        return orderStatus;
    }

    public void setOrderStatus (OrderStatus orderStatus){
        this.orderStatus = orderStatus;
    }

    public Long getChefId (){
        return chefId;
    }

    public void setChefId (Long chefId){
        this.chefId = chefId;
    }

    public Long getRestaurantId (){
        return restaurantId;
    }

    public void setRestaurantId (Long restaurantId){
        this.restaurantId = restaurantId;
    }

    @Override
    public String toString (){
        return "Order{" +
                "id=" + id +
                ", customerId=" + customerId +
                ", date=" + date +
                ", orderStatus=" + orderStatus +
                ", chefId=" + chefId +
                ", restaurantId=" + restaurantId +
                '}';
    }
}
