package org.pragma.foodcourtmanager.infrastructure.output.jpa.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDishId implements Serializable {
    @Column(name = "order_id")
    private Long orderId;
    @Column(name = "dish_id")
    private Long dishId;

}