package org.pragma.foodcourtmanager.application.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DishUpdateRequest{

    private Long id;
    private Double price;
    private String description;


}
