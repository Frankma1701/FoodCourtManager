package org.pragma.foodcourtmanager.application.mapper.request;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import org.pragma.foodcourtmanager.application.dto.request.OrderDishRequest;
import org.pragma.foodcourtmanager.application.dto.request.OrderRequest;
import org.pragma.foodcourtmanager.domain.model.Order;
import org.pragma.foodcourtmanager.domain.model.OrderDish;

import java.util.List;


@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface OrderDishRequestMapper{
    OrderDish toOrderDish(OrderDishRequest orderDishRequest);


    List<OrderDish> toOrderDishList(List<OrderDishRequest> orderDishRequestList);
}
