package org.pragma.foodcourtmanager.application.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DishResponse{

    private Long id;
    private String name;
    private Long categoryId;
    private String description;
    private Double price;
    private Long restaurantId;
    private String imageUrl;
    private boolean isActive;

}
