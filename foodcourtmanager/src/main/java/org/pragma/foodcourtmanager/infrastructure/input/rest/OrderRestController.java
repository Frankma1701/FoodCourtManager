package org.pragma.foodcourtmanager.infrastructure.input.rest;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.pragma.foodcourtmanager.application.dto.request.*;
import org.pragma.foodcourtmanager.application.dto.response.*;
import org.pragma.foodcourtmanager.application.handler.OrderHandler;
import org.pragma.foodcourtmanager.domain.model.OrderStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/")
    public ResponseEntity<List<CompleteOrderResponse>> getCompleteOrder (
            @RequestParam OrderStatus orderStatus,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size){
        Pageable pageable = PageRequest.of(page, size);
        Page<CompleteOrderResponse> completeOrderResponses = orderHandler.getAllOrders(orderStatus, pageable);
        List<CompleteOrderResponse> result = completeOrderResponses.getContent();
        return ResponseEntity.ok(result);
    }

   @GetMapping("/traceability")
    public ResponseEntity<List<TraceabilityResponse>> getTraceability (){
        List<TraceabilityResponse> result = orderHandler.getTraceability();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/time-traceability")
    public ResponseEntity<List<TraceabilityTimeResponse>> getTimeOrders (){
        List<TraceabilityTimeResponse> result = orderHandler.getTimeOrders();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/ranking-employee")
    public ResponseEntity<List<TraceabilityRankingResponse>> getRankingEmployeesOrders (){
        List<TraceabilityRankingResponse> result = orderHandler.getRankingEmployeesOrders();
        return ResponseEntity.ok(result);
    }

    @PutMapping("/")
    public ResponseEntity<List<CompleteOrderResponse>> assignOrder (
            @RequestBody OrderUpdateRequest orderUpdateRequest,
            @RequestParam OrderStatus orderStatus,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size){
        orderHandler.assignOrder(orderUpdateRequest);
        Pageable pageable = PageRequest.of(page, size);
        Page<CompleteOrderResponse> completeOrderResponses = orderHandler.getAllOrders(orderStatus, pageable);
        List<CompleteOrderResponse> result = completeOrderResponses.getContent();

        return ResponseEntity.ok(result);
    }



    @PutMapping("/order-ready")
    public ResponseEntity<String> orderReady (
            @RequestBody OrderUpdateRequest orderUpdateRequest){
        orderHandler.orderReady(orderUpdateRequest);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/delivery-order")
    public ResponseEntity<String> deliverOrder (
            @RequestBody OrderValidatePinRequest orderValidatePinRequest){
        orderHandler.deliverOrder(orderValidatePinRequest);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/cancel-order")
    public ResponseEntity<String> cancelOrder (
            @RequestBody OrderUpdateRequest orderUpdateRequest){
        orderHandler.cancelOrder(orderUpdateRequest);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderResponse> getOrder (
            @PathVariable(name = "orderId") Long orderId){
        return ResponseEntity.ok(orderHandler.getOrder(orderId));
    }
}
