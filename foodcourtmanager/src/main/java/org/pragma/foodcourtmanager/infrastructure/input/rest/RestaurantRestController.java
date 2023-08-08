package org.pragma.foodcourtmanager.infrastructure.input.rest;


import lombok.RequiredArgsConstructor;
import org.pragma.foodcourtmanager.application.dto.request.RestaurantRequest;
import org.pragma.foodcourtmanager.application.dto.response.RestaurantResponse;
import org.pragma.foodcourtmanager.application.handler.RestaurantHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/restaurant")
@RequiredArgsConstructor
public class RestaurantRestController{

    private final RestaurantHandler restaurantHandler;



    @PostMapping("/")
    public ResponseEntity<Void> saveRestaurant(@RequestBody RestaurantRequest restaurantRequest){
        restaurantHandler.saveRestaurant(restaurantRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/")
    public ResponseEntity<List<RestaurantResponse>> getAllRestaurants(){
        return ResponseEntity.ok(restaurantHandler.getAllRestaurants());

    }

    @GetMapping("/{nit}")
    public ResponseEntity<RestaurantResponse> getRestaurant(@PathVariable(name="nit") String nit){
        return ResponseEntity.ok(restaurantHandler.getRestaurant(nit));
    }

    @PutMapping("/")
    public ResponseEntity<Void> updateRestaurant(@RequestBody RestaurantRequest restaurantRequest){
        restaurantHandler.updateRestaurant(restaurantRequest);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{nit}")
    public ResponseEntity<Void> deleteRestaurant( String nit){
        restaurantHandler.deleteRestaurant(nit);
        return ResponseEntity.noContent().build();
    }




}
