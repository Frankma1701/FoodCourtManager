package org.pragma.foodcourtmanager.application.handler;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.pragma.foodcourtmanager.application.dto.request.DishRequest;
import org.pragma.foodcourtmanager.application.dto.request.DishUpdateRequest;
import org.pragma.foodcourtmanager.application.dto.request.RestaurantRequest;
import org.pragma.foodcourtmanager.application.dto.response.DishResponse;
import org.pragma.foodcourtmanager.application.dto.response.RestaurantResponse;
import org.pragma.foodcourtmanager.application.mapper.request.DishRequestMapper;
import org.pragma.foodcourtmanager.application.mapper.request.RestaurantRequestMapper;
import org.pragma.foodcourtmanager.application.mapper.response.DishResponseMapper;
import org.pragma.foodcourtmanager.application.mapper.response.RestaurantResponseMapper;
import org.pragma.foodcourtmanager.domain.api.IDishServicePort;
import org.pragma.foodcourtmanager.domain.api.IRestaurantServicePort;
import org.pragma.foodcourtmanager.domain.model.Dish;
import org.pragma.foodcourtmanager.domain.model.Restaurant;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class DishHandler implements IDishHandler{

    private final IDishServicePort iDishServicePort;
    private final DishRequestMapper dishRequestMapper;
    private final DishResponseMapper dishResponseMapper;
    private final IRestaurantServicePort iRestaurantServicePort;


    @Override
    public void saveDish(DishRequest dishRequest) {
        Dish dish = dishRequestMapper.toDish(dishRequest);
        iDishServicePort.saveDish(dish);
    }

    @Override
    public List<DishResponse> getAllDishes() {
        return dishResponseMapper.toResponseList(iDishServicePort.getAllDishes());
    }


    @Override
    public DishResponse getDish(Long id) {
        return dishResponseMapper.toResponse(iDishServicePort.getDish(id));
    }


    @Override
    public void updateDish(DishUpdateRequest dishUpdateRequest) {
        iDishServicePort.getDish(dishUpdateRequest.getId());
        iDishServicePort.updateDish(dishRequestMapper.toDish(dishUpdateRequest));
    }




}
