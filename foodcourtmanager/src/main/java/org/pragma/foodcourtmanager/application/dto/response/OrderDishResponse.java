package org.pragma.foodcourtmanager.application.dto.response;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OrderDishResponse{
    private Long dishId;
    private String name;
    private Long categoryId;
    private String description;
    private Double price;
    private String imageUrl;
    private Boolean isActive;
    private Long quantity;

    @Override
    public String toString (){
        return "OrderDishResponse{" +
                "dishId=" + dishId +
                ", name='" + name + '\'' +
                ", categoryId=" + categoryId +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", imageUrl='" + imageUrl + '\'' +
                ", isActive=" + isActive +
                ", quantity=" + quantity +
                '}';
    }
}
