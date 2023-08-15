package org.pragma.foodcourtmanager.application.mapper.request;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import org.pragma.foodcourtmanager.application.dto.request.CompleteOrderRequest;
import org.pragma.foodcourtmanager.application.dto.request.OrderRequest;
import org.pragma.foodcourtmanager.domain.model.Order;
import org.pragma.foodcourtmanager.domain.model.OrderStatus;

import java.time.LocalDate;


@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface OrderRequestMapper{
    @Mapping(target = "chefId" , source = "orderRequest.chefId")
    Order toOrder(OrderRequest orderRequest);

    @Mapping(target = "chefId" , source = "completeOrderRequest.chefId")
    Order toOrder(CompleteOrderRequest completeOrderRequest);


    OrderRequest toOrderRequest(CompleteOrderRequest completeOrderRequest);


}
