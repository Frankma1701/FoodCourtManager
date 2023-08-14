package org.pragma.foodcourtmanager.infrastructure.output.jpa.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "order_dish")
@Getter
@Setter
public class OrderDishEntity{

    @EmbeddedId
    private OrderDishId id;

    @ManyToOne
    @MapsId("orderId")
    @JoinColumn(name = "order_id",foreignKey = @ForeignKey(name = "fk_orderdish_order"))
    private OrderEntity orderEntity;

    @ManyToOne
    @MapsId("dishId")
    @JoinColumn(name = "dish_id",foreignKey = @ForeignKey(name = "fk_orderdish_dish"))
    private DishEntity dishEntity;

    private Long quantity;

    @Override
    public String toString (){
        return "OrderDishEntity{" +
                "id=" + id +
                ", orderEntity=" + orderEntity.getId() +
                ", dishEntity=" + dishEntity.getId() +
                ", quantity=" + quantity +
                '}';
    }
}
