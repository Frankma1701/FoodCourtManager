package org.pragma.foodcourtmanager.domain.api;
import org.pragma.foodcourtmanager.domain.model.Dish;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IDishServicePort{

    Dish saveDish (Dish dish);

    Page<Dish> getAllDishes (Long restaurantId,Long categoryId, Pageable pageable);

    Dish getDish (Long id);

    void updateDish (Dish dish);

    //void deleteRestaurant(String nit);

}
