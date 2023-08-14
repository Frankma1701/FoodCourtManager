package org.pragma.foodcourtmanager.infrastructure.output.jpa.repository;

import org.pragma.foodcourtmanager.infrastructure.output.jpa.entity.DishEntity;
import org.pragma.foodcourtmanager.infrastructure.output.jpa.entity.RestaurantEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface IDishRepository extends JpaRepository<DishEntity,Long> {
    //@Query("SELECT d FROM DishEntity d WHERE d.restaurantEntity.id = :restaurantId")

    @Query("SELECT d FROM DishEntity d WHERE d.restaurantEntity.id = :restaurantId AND d.categoryEntity.id = :categoryId")
    Page<DishEntity> findByRestaurantIdAndCategoryId(Long restaurantId, Long categoryId, Pageable pageable);

}
