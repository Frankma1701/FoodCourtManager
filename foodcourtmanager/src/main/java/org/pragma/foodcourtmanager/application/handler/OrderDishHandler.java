package org.pragma.foodcourtmanager.application.handler;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.pragma.foodcourtmanager.application.dto.request.OrderDishRequest;
import org.pragma.foodcourtmanager.application.dto.request.OrderRequest;
import org.pragma.foodcourtmanager.application.dto.response.OrderDishResponse;
import org.pragma.foodcourtmanager.application.dto.response.RestaurantListResponse;
import org.pragma.foodcourtmanager.application.mapper.request.OrderDishRequestMapper;
import org.pragma.foodcourtmanager.application.mapper.request.OrderRequestMapper;
import org.pragma.foodcourtmanager.domain.api.IOrderDishServicePort;
import org.pragma.foodcourtmanager.domain.api.IOrderServicePort;
import org.pragma.foodcourtmanager.domain.model.OrderDish;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderDishHandler implements IOrderDishHandler{
    private final IOrderDishServicePort iOrderDishServicePort;
    private final OrderDishRequestMapper orderDishRequestMapper;
    @Override
    public void saveOrderDish(OrderDishRequest orderDishRequest) {
        iOrderDishServicePort.saveOrderDish(orderDishRequestMapper.toOrderDish(orderDishRequest));
    }

    @Override
    public List<OrderDishResponse> getAllOrderDish(Long orderId){
        List<OrderDish> orderDishList = iOrderDishServicePort.getAllOrderDish(orderId);
        orderDishList.forEach(orderDish -> System.out.println(orderDish.toString()));
        return orderDishRequestMapper.toResponseList(iOrderDishServicePort.getAllOrderDish(orderId));

    }

}
