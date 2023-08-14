package org.pragma.foodcourtmanager.infrastructure.output.jpa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.pragma.foodcourtmanager.domain.model.OrderStatus;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "order_food")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrderEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long customerId;
    private LocalDate date;
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;
    private Long chefId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn( name = "restaurant_id",foreignKey = @ForeignKey(name = "fk_order_restaurant"))
    private RestaurantEntity restaurantEntity;
    @OneToMany(fetch = FetchType.LAZY , mappedBy = "orderEntity" , cascade = CascadeType.ALL)
    private List<OrderDishEntity> orderDishEntities;

    @Override
    public String toString (){
        return "OrderEntity{" +
                "id=" + id +
                ", customerId=" + customerId +
                ", date=" + date +
                ", orderStatus=" + orderStatus +
                ", chefId=" + chefId +
                ", restaurantEntity=" + restaurantEntity +
                ", orderDishEntities=" + orderDishEntities +
                '}';
    }
}
