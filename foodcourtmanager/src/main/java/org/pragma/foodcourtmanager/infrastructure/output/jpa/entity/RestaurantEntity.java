package org.pragma.foodcourtmanager.infrastructure.output.jpa.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.pragma.foodcourtmanager.domain.model.Dish;
import org.pragma.foodcourtmanager.infrastructure.output.jpa.constants.ConstantsEntity;

import java.util.List;

@Entity
@Table(name = "restaurant")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RestaurantEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = ConstantsEntity.NOT_NULL_NAME_RESTAURANT)
    @NotBlank(message = ConstantsEntity.NOT_BLANK_NAME_RESTAURANT)
    @Pattern(regexp = ConstantsEntity.REGEX_NAME_RESTAURANT, message = ConstantsEntity.NO_VALID_NAME_RESTAURANT)
    private String name;
    @NotNull(message = ConstantsEntity.NOT_NULL_NAME_RESTAURANT)
    @NotBlank(message = ConstantsEntity.NOT_BLANK_NAME_RESTAURANT)
    private String address;
    @NotNull(message = ConstantsEntity.NOT_NULL_NAME_RESTAURANT)
    @NotBlank(message = ConstantsEntity.NOT_BLANK_NAME_RESTAURANT)
    private Long ownerId;
    @NotNull(message = ConstantsEntity.NOT_NULL_NAME_RESTAURANT)
    @NotBlank(message = ConstantsEntity.NOT_BLANK_NAME_RESTAURANT)
    @Pattern(regexp = ConstantsEntity.REGEX_PHONE_NUMBER_RESTAURANT, message = ConstantsEntity.NO_VALID_PHONE_NUMBER_RESTAURANT)
    private String phoneNumber;
    @NotNull(message = ConstantsEntity.NOT_NULL_NAME_RESTAURANT)
    @NotBlank(message = ConstantsEntity.NOT_BLANK_NAME_RESTAURANT)
    private String logoUrl;
    @NotNull(message = ConstantsEntity.NOT_NULL_NAME_RESTAURANT)
    @NotBlank(message = ConstantsEntity.NOT_BLANK_NAME_RESTAURANT)
    @Pattern(regexp = ConstantsEntity.REGEX_NIT_RESTAURANT , message = ConstantsEntity.NO_VALID_NIT_RESTAURANT)
    private String nit;
    @OneToMany(fetch = FetchType.LAZY , mappedBy = "restaurantEntity" , cascade = CascadeType.ALL)
    private List<DishEntity> dishes;

}
