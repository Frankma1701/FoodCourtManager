package org.pragma.foodcourtmanager.infrastructure.output.jpa.repository;
import org.pragma.foodcourtmanager.domain.model.Order;
import org.pragma.foodcourtmanager.domain.model.OrderStatus;
import org.pragma.foodcourtmanager.infrastructure.output.jpa.entity.DishEntity;
import org.pragma.foodcourtmanager.infrastructure.output.jpa.entity.OrderEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IOrderRepository extends JpaRepository<OrderEntity,Long> {
    List<OrderEntity> getOrdersByCustomerId(Long customerId);

    //@Query("SELECT o FROM OrderEntity o WHERE o.chefId = :employeeId")
    @Query("SELECT o FROM OrderEntity o WHERE o.restaurantEntity.id = :restaurantId AND o.orderStatus = :orderStatus")
    Page<OrderEntity> getOrdersByRestaurantIdId(Long restaurantId, OrderStatus orderStatus, Pageable pageable);

    //@Query("SELECT o FROM OrderEntity o WHERE o.chefId = :employeeId")
    //List<OrderEntity> getOrdersByEmployeeId(Long employeeId);

}
