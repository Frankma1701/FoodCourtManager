package org.pragma.foodcourtmanager.infrastructure.output.jpa.repository;
import org.pragma.foodcourtmanager.infrastructure.output.jpa.entity.OrderDishEntity;
import org.pragma.foodcourtmanager.infrastructure.output.jpa.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IOrderDishRepository extends JpaRepository<OrderDishEntity,Long> {


}
