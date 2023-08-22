package org.pragma.foodcourtmanager.application.handler;

import org.pragma.foodcourtmanager.application.dto.request.DishRequest;
import org.pragma.foodcourtmanager.application.dto.request.DishStateRequest;
import org.pragma.foodcourtmanager.application.dto.request.DishUpdateRequest;
import org.pragma.foodcourtmanager.application.dto.request.RestaurantRequest;
import org.pragma.foodcourtmanager.application.dto.response.DishResponse;
import org.pragma.foodcourtmanager.application.dto.response.RestaurantResponse;
import org.pragma.foodcourtmanager.domain.model.Dish;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IDishHandler{

    Dish saveDish(DishRequest dishRequest);
    Page<DishResponse> getAllDishes(Long restaurantId,Long categoryId, Pageable pageable);

    DishResponse getDish(Long id);

    //void deleteRestaurant(String nit);
    void updateDish(DishUpdateRequest dishUpdateRequest);

    void updateStateDish(DishStateRequest dishStateRequest);


    // void deleteRestaurant(String documentId);
}
