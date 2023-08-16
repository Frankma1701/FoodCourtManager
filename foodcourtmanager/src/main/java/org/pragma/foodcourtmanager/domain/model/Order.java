package org.pragma.foodcourtmanager.domain.model;

import java.time.LocalDate;

public class Order {

    private Long id;
    private Long customerId;
    private LocalDate date;
    private OrderStatus orderStatus;
    private Long employeeId;
    private Long restaurantId;

    private String verificationCode;

    public Order (Long id, Long customerId, LocalDate date, OrderStatus orderStatus, Long employeeId, Long restaurantId, String verificationCode){
        this.id = id;
        this.customerId = customerId;
        this.date = date;
        this.orderStatus = orderStatus;
        this.employeeId = employeeId;
        this.restaurantId = restaurantId;
        this.verificationCode = verificationCode;
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

    public Long getEmployeeId (){
        return employeeId;
    }

    public void setEmployeeId (Long employeeId){
        this.employeeId = employeeId;
    }

    public Long getRestaurantId (){
        return restaurantId;
    }

    public void setRestaurantId (Long restaurantId){
        this.restaurantId = restaurantId;
    }

    public String getVerificationCode (){
        return verificationCode;
    }

    public void setVerificationCode (String verificationCode){
        this.verificationCode = verificationCode;
    }

    @Override
    public String toString (){
        return "Order{" +
                "id=" + id +
                ", customerId=" + customerId +
                ", date=" + date +
                ", orderStatus=" + orderStatus +
                ", employeeId=" + employeeId +
                ", restaurantId=" + restaurantId +
                ", verificationCode='" + verificationCode + '\'' +
                '}';
    }
}
