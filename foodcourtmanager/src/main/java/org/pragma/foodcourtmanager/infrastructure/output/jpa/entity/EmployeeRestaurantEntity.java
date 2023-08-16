package org.pragma.foodcourtmanager.infrastructure.output.jpa.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "employee_restaurant")
@Getter
@Setter
public class EmployeeRestaurantEntity{

    @EmbeddedId
    private EmployeeRestaurantId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("restaurantId")
    @JoinColumn( name = "restaurant_id",foreignKey = @ForeignKey(name = "fk_employee_restaurant"))
    private RestaurantEntity restaurantEntity;

    @Override
    public String toString (){
        return "EmployeeRestaurantEntity{" +
                 "idEmployee=" + id.getEmployeeId() +
                ", restaurantEntity=" + restaurantEntity.getId() +
                '}';
    }
}
