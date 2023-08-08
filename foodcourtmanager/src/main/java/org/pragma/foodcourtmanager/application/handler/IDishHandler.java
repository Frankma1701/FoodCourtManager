package org.pragma.foodcourtmanager.application.handler;

import org.pragma.foodcourtmanager.application.dto.request.DishRequest;
import org.pragma.foodcourtmanager.application.dto.request.DishUpdateRequest;
import org.pragma.foodcourtmanager.application.dto.request.RestaurantRequest;
import org.pragma.foodcourtmanager.application.dto.response.DishResponse;
import org.pragma.foodcourtmanager.application.dto.response.RestaurantResponse;

import java.util.List;

public interface IDishHandler{

    void saveDish(DishRequest dishRequest);
    List<DishResponse> getAllDishes();

    DishResponse getDish(Long id);

    //void deleteRestaurant(String nit);
    void updateDish(DishUpdateRequest dishUpdateRequest);

    // void deleteRestaurant(String documentId);
}
