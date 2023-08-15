package org.pragma.foodcourtmanager.infrastructure.output.jpa.repository;
import org.pragma.foodcourtmanager.domain.model.Order;
import org.pragma.foodcourtmanager.infrastructure.output.jpa.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IOrderRepository extends JpaRepository<OrderEntity,Long> {
    List<OrderEntity> getOrdersByCustomerId(Long customerId);


}
