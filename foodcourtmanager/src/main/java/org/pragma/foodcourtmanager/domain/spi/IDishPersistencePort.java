package org.pragma.foodcourtmanager.domain.spi;

import org.pragma.foodcourtmanager.application.dto.request.DishUpdateRequest;
import org.pragma.foodcourtmanager.domain.model.Dish;
import org.pragma.foodcourtmanager.domain.model.Restaurant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IDishPersistencePort{
    Dish saveDish (Dish dish);

    Page<Dish> getAllDishes (Long restaurantId,Long categoryId, Pageable pageable);

    Dish getDish (Long id);

    //void deleteRestaurant(String nit);
    void updateDish (Dish dish);
}
