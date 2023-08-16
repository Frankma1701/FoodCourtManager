package org.pragma.foodcourtmanager.application.mapper.request;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import org.pragma.foodcourtmanager.application.dto.request.CompleteOrderRequest;
import org.pragma.foodcourtmanager.application.dto.request.OrderRequest;
import org.pragma.foodcourtmanager.application.dto.request.OrderUpdateRequest;
import org.pragma.foodcourtmanager.application.dto.response.CompleteOrderResponse;
import org.pragma.foodcourtmanager.application.dto.response.OrderDishResponse;
import org.pragma.foodcourtmanager.application.dto.response.RestaurantListResponse;
import org.pragma.foodcourtmanager.domain.model.Order;
import org.pragma.foodcourtmanager.domain.model.OrderStatus;
import org.pragma.foodcourtmanager.domain.model.Restaurant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.time.LocalDate;
import java.util.List;


@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface OrderRequestMapper{
    // @Mapping(target = "employeeId" , source = "orderRequest.employeeId")
    Order toOrder(OrderRequest orderRequest);

    // @Mapping(target = "employeeId" , source = "completeOrderRequest.employeeId")
    Order toOrder(CompleteOrderRequest completeOrderRequest);

    Order toOrder(OrderUpdateRequest orderUpdateRequest);

    OrderUpdateRequest toOrder(Order order);


    CompleteOrderResponse toResponse(Order order);

    OrderRequest toOrderRequest(CompleteOrderRequest completeOrderRequest);

    default Page<CompleteOrderResponse> toResponseList(Page<Order> orderPage) {
        List<CompleteOrderResponse> completeOrderResponseList = orderPage.getContent().stream()
                .map(order -> {
                    CompleteOrderResponse completeOrderResponse = new CompleteOrderResponse();
                    completeOrderResponse.setEmployeeId(order.getEmployeeId());
                    completeOrderResponse.setRestaurantId(order.getRestaurantId());
                    completeOrderResponse.setCustomerId(order.getCustomerId());
                    return completeOrderResponse;
                })
                .toList();

        return new PageImpl<>(completeOrderResponseList, orderPage.getPageable(), orderPage.getTotalElements());
    }


}
