package org.pragma.foodcourtmanager.domain.model;

public class EmployeeRestaurant{

    private Long employeeId;
    private Long restaurantId;

    public EmployeeRestaurant (Long employeeId, Long restaurantId){
        this.employeeId = employeeId;
        this.restaurantId = restaurantId;
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

    @Override
    public String toString (){
        return "EmployeeRestaurant{" +
                "employeeId=" + employeeId +
                ", restaurantId=" + restaurantId +
                '}';
    }

}
