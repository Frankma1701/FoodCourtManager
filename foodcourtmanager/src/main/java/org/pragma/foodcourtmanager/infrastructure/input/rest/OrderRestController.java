package org.pragma.foodcourtmanager.infrastructure.input.rest;
import lombok.RequiredArgsConstructor;
import org.pragma.foodcourtmanager.application.dto.request.CompleteOrderRequest;
import org.pragma.foodcourtmanager.application.dto.request.OrderRequest;
import org.pragma.foodcourtmanager.application.handler.OrderHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderRestController{

    private final OrderHandler orderHandler;

    @PostMapping("/")
    public ResponseEntity<Void> saveCompleteOrder (@RequestBody CompleteOrderRequest completeOrderRequest){
        orderHandler.saveCompleteOrder(completeOrderRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
