package org.pragma.foodcourtmanager.infrastructure.output.jpa.entity;

import jakarta.persistence.*;
import lombok.*;
import org.pragma.foodcourtmanager.domain.model.OrderStatus;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "order_food")
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class OrderEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long customerId;
    private LocalDateTime date;
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;
    @Column(columnDefinition = "bigint default 0")
    private Long employeeId;
    @Column(columnDefinition = "varchar(255) default ''")
    private String verificationCode;
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
                ", employeeId=" + employeeId +
                ", restaurantEntity=" + restaurantEntity +
                ", orderDishEntities=" + orderDishEntities +
                '}';
    }
}
