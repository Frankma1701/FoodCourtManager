package org.pragma.foodcourtmanager.infrastructure.input.rest;
import lombok.RequiredArgsConstructor;
import org.pragma.foodcourtmanager.application.dto.request.CompleteOrderRequest;
import org.pragma.foodcourtmanager.application.dto.request.EmployeeRestaurantRequest;
import org.pragma.foodcourtmanager.application.dto.response.CompleteOrderResponse;
import org.pragma.foodcourtmanager.application.dto.response.EmployeeRestaurantResponse;
import org.pragma.foodcourtmanager.application.handler.EmployeeRestaurantHandler;
import org.pragma.foodcourtmanager.application.handler.OrderHandler;
import org.pragma.foodcourtmanager.domain.model.OrderStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee-restaurant")
@RequiredArgsConstructor
public class EmployeeRestaurantRestController{

    private final EmployeeRestaurantHandler employeeRestaurantHandler;

    @PostMapping("/")
    public ResponseEntity<Void> saveEmployeeRestaurant (@RequestBody EmployeeRestaurantRequest employeeRestaurantRequest){
        employeeRestaurantHandler.saveEmployeeRestaurant(employeeRestaurantRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeRestaurantResponse> getEmployeeRestaurant (@PathVariable(name="id") Long id){
        return ResponseEntity.ok(employeeRestaurantHandler.getEmployeeRestaurant(id));
    }


}
