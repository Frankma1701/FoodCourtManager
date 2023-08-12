package org.pragma.foodcourtmanager.application.handler;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.pragma.foodcourtmanager.application.dto.request.DishRequest;
import org.pragma.foodcourtmanager.application.dto.request.DishUpdateRequest;
import org.pragma.foodcourtmanager.application.dto.response.DishResponse;
import org.pragma.foodcourtmanager.application.dto.response.RestaurantResponse;
import org.pragma.foodcourtmanager.application.mapper.request.DishRequestMapper;
import org.pragma.foodcourtmanager.application.mapper.response.DishResponseMapper;
import org.pragma.foodcourtmanager.domain.api.IDishServicePort;
import org.pragma.foodcourtmanager.domain.model.Dish;
import org.pragma.foodcourtmanager.infrastructure.exception.NotOwnerRestaurantUserException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class DishHandler implements IDishHandler{

    private final IDishServicePort iDishServicePort;
    private final DishRequestMapper dishRequestMapper;
    private final DishResponseMapper dishResponseMapper;
    private final UserHandler userHandler;
    private final RestaurantHandler restaurantHandler;

    @Value("${jwt.secret-key}")
    private String SECRET_KEY;


    @Override
    public void saveDish(DishRequest dishRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String token = (String) authentication.getCredentials();
        Claims claims = Jwts.parserBuilder()
                        .setSigningKey(SECRET_KEY)
                        .build()
                        .parseClaimsJws(token)
                        .getBody();
        Long userId = claims.get("id", Long.class);
        RestaurantResponse restaurantResponse = restaurantHandler.getRestaurant(dishRequest.getRestaurantNit());
        if(restaurantResponse.getOwnerId() == userId){
            dishRequest.setRestaurantNit(String.valueOf(restaurantResponse.getId()));
            Dish dish = dishRequestMapper.toDish(dishRequest);
            iDishServicePort.saveDish(dish);
        }else{
            throw new NotOwnerRestaurantUserException();

        }

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
        Dish dish = iDishServicePort.getDish(dishUpdateRequest.getId());
        dish.setDescription(dishUpdateRequest.getDescription());
        dish.setPrice(dishUpdateRequest.getPrice());
        iDishServicePort.updateDish(dish);
    }

}
