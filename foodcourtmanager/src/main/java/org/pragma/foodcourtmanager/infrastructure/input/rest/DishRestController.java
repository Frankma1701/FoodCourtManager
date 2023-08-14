package org.pragma.foodcourtmanager.infrastructure.input.rest;


import lombok.RequiredArgsConstructor;
import org.pragma.foodcourtmanager.application.dto.request.DishRequest;
import org.pragma.foodcourtmanager.application.dto.request.DishStateRequest;
import org.pragma.foodcourtmanager.application.dto.request.DishUpdateRequest;
import org.pragma.foodcourtmanager.application.dto.request.RestaurantRequest;
import org.pragma.foodcourtmanager.application.dto.response.DishResponse;
import org.pragma.foodcourtmanager.application.dto.response.RestaurantListResponse;
import org.pragma.foodcourtmanager.application.dto.response.RestaurantResponse;
import org.pragma.foodcourtmanager.application.handler.DishHandler;
import org.pragma.foodcourtmanager.application.handler.RestaurantHandler;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/dish")
@RequiredArgsConstructor
public class DishRestController{

    private final DishHandler dishHandler;


    @PostMapping("/")
    public ResponseEntity<Void> saveDish (@RequestBody DishRequest dishRequest){
        dishHandler.saveDish(dishRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/")
    public ResponseEntity<List<DishResponse>> getAllDishes (
            @RequestParam Long restaurantId,
            @RequestParam Long categoryId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size){

        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "name"));
        Page<DishResponse> restaurantPage = dishHandler.getAllDishes(restaurantId,categoryId, pageable);
        List<DishResponse> result = restaurantPage.getContent();
        return ResponseEntity.ok(result);

    }

    @GetMapping("/{id}")
    public ResponseEntity<DishResponse> getDish (@PathVariable(name = "id") Long id){
        return ResponseEntity.ok(dishHandler.getDish(id));
    }

    @PutMapping("/")
    public ResponseEntity<Void> updateDish (@RequestBody DishUpdateRequest dishUpdateRequest){
        dishHandler.updateDish(dishUpdateRequest);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/change-state")
    public ResponseEntity<Void> updateStateDish (@RequestBody DishStateRequest dishStateRequest){
        dishHandler.updateStateDish(dishStateRequest);
        return ResponseEntity.noContent().build();
    }


}
