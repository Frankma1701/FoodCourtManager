package org.pragma.foodcourtmanager.infrastructure.output.jpa.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.pragma.foodcourtmanager.infrastructure.output.jpa.constants.ConstantsEntity;

import java.util.List;

@Entity
@Table(name = "category")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CategoryEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = ConstantsEntity.NOT_NULL_NAME_RESTAURANT)
    @NotBlank(message = ConstantsEntity.NOT_BLANK_NAME_RESTAURANT)
    @Pattern(regexp = ConstantsEntity.REGEX_NAME_RESTAURANT, message = ConstantsEntity.NO_VALID_NAME_RESTAURANT)
    private String name;
    @NotNull(message = ConstantsEntity.NOT_NULL_NAME_RESTAURANT)
    @NotBlank(message = ConstantsEntity.NOT_BLANK_NAME_RESTAURANT)
    private String description;
    @OneToMany(fetch = FetchType.LAZY , mappedBy = "categoryEntity" , cascade = CascadeType.ALL)
    private List<DishEntity> dishes;

}
