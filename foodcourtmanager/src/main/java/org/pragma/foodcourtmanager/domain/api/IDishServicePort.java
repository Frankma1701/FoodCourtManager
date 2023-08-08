package org.pragma.foodcourtmanager.domain.api;
import org.pragma.foodcourtmanager.domain.model.Dish;

import java.util.List;

public interface IDishServicePort{

    Dish saveDish (Dish dish);

    List<Dish> getAllDishes ();

    Dish getDish (Long id);

    void updateDish (Dish dish);

    //void deleteRestaurant(String nit);

}
