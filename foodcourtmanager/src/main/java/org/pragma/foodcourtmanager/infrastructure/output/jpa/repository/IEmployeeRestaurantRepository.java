package org.pragma.foodcourtmanager.infrastructure.output.jpa.repository;
import org.pragma.foodcourtmanager.infrastructure.output.jpa.entity.EmployeeRestaurantEntity;
import org.pragma.foodcourtmanager.infrastructure.output.jpa.entity.OrderDishEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IEmployeeRestaurantRepository extends JpaRepository<EmployeeRestaurantEntity,Long> {
    EmployeeRestaurantEntity findByIdEmployeeId(Long employeeId);

}
