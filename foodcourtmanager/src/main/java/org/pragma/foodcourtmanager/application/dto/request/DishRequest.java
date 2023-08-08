package org.pragma.foodcourtmanager.application.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DishRequest{

    private String name;
    private Long categoryId;
    private String description;
    private Double price;
    private String imageUrl;
    private Boolean isActive;
    private Long restaurantId;
}
