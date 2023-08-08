package org.pragma.foodcourtmanager.domain.spi;

import org.pragma.foodcourtmanager.application.dto.request.DishUpdateRequest;
import org.pragma.foodcourtmanager.domain.model.Dish;
import org.pragma.foodcourtmanager.domain.model.Restaurant;

import java.util.List;

public interface IDishPersistencePort{
    Dish saveDish (Dish dish);

    List<Dish> getAllDishes ();

    Dish getDish (Long id);

    //void deleteRestaurant(String nit);
    void updateDish (Dish dish);
}
