package org.pragma.foodcourtmanager.application.handler;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.pragma.foodcourtmanager.application.dto.request.OrderDishRequest;
import org.pragma.foodcourtmanager.application.dto.request.OrderRequest;
import org.pragma.foodcourtmanager.application.mapper.request.OrderDishRequestMapper;
import org.pragma.foodcourtmanager.application.mapper.request.OrderRequestMapper;
import org.pragma.foodcourtmanager.domain.api.IOrderDishServicePort;
import org.pragma.foodcourtmanager.domain.api.IOrderServicePort;
import org.springframework.stereotype.Service;

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
}
