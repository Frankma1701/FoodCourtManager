package org.pragma.foodcourtmanager.infrastructure.output.jpa.repository;

import org.pragma.foodcourtmanager.infrastructure.output.jpa.entity.RestaurantEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IRestaurantRepository extends JpaRepository<RestaurantEntity,Long> {

    Optional<RestaurantEntity> findByNit(String nit);

    void deleteByNit(String nit);
}
