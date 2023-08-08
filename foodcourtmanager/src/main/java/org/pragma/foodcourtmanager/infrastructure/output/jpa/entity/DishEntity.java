package org.pragma.foodcourtmanager.infrastructure.output.jpa.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "dish")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DishEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Long categoryId;
    private String description;
    private Double price;
    private String imageUrl;
    private boolean isActive;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn( name = "restaurant_id",foreignKey = @ForeignKey(name = "fk_plate_restaurant"))
    private RestaurantEntity restaurantEntity;

    @Override
    public String toString (){
        return "DishEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", categoryId=" + categoryId +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", imageUrl='" + imageUrl + '\'' +
                ", isActive=" + isActive +
                ", restaurantEntity=" + restaurantEntity.toString() +
                '}';
    }
}
