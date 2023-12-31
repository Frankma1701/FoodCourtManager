package org.pragma.foodcourtmanager.infrastructure.output.jpa.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.pragma.foodcourtmanager.infrastructure.output.jpa.constants.ConstantsEntity;

import java.util.List;

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
    @NotNull(message = ConstantsEntity.NOT_NULL_NAME_DISH)
    @NotBlank(message = ConstantsEntity.NOT_BLANK_NAME_DISH)
    private String name;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn( name = "category_id",foreignKey = @ForeignKey(name = "fk_plate_category"))
    private CategoryEntity categoryEntity;
    @NotNull(message = ConstantsEntity.NOT_NULL_DESCRIPTION_DISH)
    @NotBlank(message = ConstantsEntity.NOT_BLANK_DESCRIPTION_DISH)
    private String description;
    @Digits(integer = 10, fraction = 0, message = ConstantsEntity.INTEGER_PRICE_DISH)
    @Positive(message = ConstantsEntity.NOT_ZERO_PRICE_DISH)
    private Double price;
    @NotNull(message = ConstantsEntity.NOT_NULL_URL_IMAGE_DISH)
    @NotBlank(message = ConstantsEntity.NOT_BLANK_URL_IMAGE_DISH)
    private String imageUrl;
    @Column(columnDefinition = "boolean default true")
    private boolean isActive;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn( name = "restaurant_id",foreignKey = @ForeignKey(name = "fk_plate_restaurant"))
    private RestaurantEntity restaurantEntity;
    @OneToMany(fetch = FetchType.LAZY , mappedBy = "dishEntity" , cascade = CascadeType.ALL)
    private List<OrderDishEntity> orderDishEntities;
    @Override
    public String toString (){
        return "DishEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", imageUrl='" + imageUrl + '\'' +
                ", isActive=" + isActive +
                ", restaurantEntity=" + restaurantEntity.getId() +
                '}';
    }
}
