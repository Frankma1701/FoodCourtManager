package org.pragma.foodcourtmanager.infrastructure.input.rest;


import lombok.RequiredArgsConstructor;
import org.pragma.foodcourtmanager.application.dto.request.DishRequest;
import org.pragma.foodcourtmanager.application.dto.request.DishUpdateRequest;
import org.pragma.foodcourtmanager.application.dto.request.RestaurantRequest;
import org.pragma.foodcourtmanager.application.dto.response.DishResponse;
import org.pragma.foodcourtmanager.application.dto.response.RestaurantResponse;
import org.pragma.foodcourtmanager.application.handler.DishHandler;
import org.pragma.foodcourtmanager.application.handler.RestaurantHandler;
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
    public ResponseEntity<Void> saveDish(@RequestBody DishRequest dishRequest){
        dishHandler.saveDish(dishRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/")
    public ResponseEntity<List<DishResponse>> getAllDishes(){
        return ResponseEntity.ok(dishHandler.getAllDishes());

    }

    @GetMapping("/{id}")
    public ResponseEntity<DishResponse> getDish(@PathVariable(name="id") Long id){
        return ResponseEntity.ok(dishHandler.getDish(id));
    }

    @PutMapping("/")
    public ResponseEntity<Void> updateRestaurant(@RequestBody DishUpdateRequest dishUpdateRequest){
        dishHandler.updateDish(dishUpdateRequest);
        return ResponseEntity.noContent().build();
    }






}
