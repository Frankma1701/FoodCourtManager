package org.pragma.foodcourtmanager.infrastructure.output.jpa.adapter;

import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Or;
import org.pragma.foodcourtmanager.domain.model.Dish;
import org.pragma.foodcourtmanager.domain.model.Order;
import org.pragma.foodcourtmanager.domain.model.OrderDish;
import org.pragma.foodcourtmanager.domain.spi.IDishPersistencePort;
import org.pragma.foodcourtmanager.domain.spi.IOrderPersistencePort;
import org.pragma.foodcourtmanager.infrastructure.exception.DishNotFoundException;
import org.pragma.foodcourtmanager.infrastructure.exception.NoDataFoundException;
import org.pragma.foodcourtmanager.infrastructure.output.jpa.entity.DishEntity;
import org.pragma.foodcourtmanager.infrastructure.output.jpa.entity.OrderEntity;
import org.pragma.foodcourtmanager.infrastructure.output.jpa.mapper.DishEntityMapper;
import org.pragma.foodcourtmanager.infrastructure.output.jpa.mapper.OrderDishEntityMapper;
import org.pragma.foodcourtmanager.infrastructure.output.jpa.mapper.OrderEntityMapper;
import org.pragma.foodcourtmanager.infrastructure.output.jpa.repository.IDishRepository;
import org.pragma.foodcourtmanager.infrastructure.output.jpa.repository.IOrderDishRepository;
import org.pragma.foodcourtmanager.infrastructure.output.jpa.repository.IOrderRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class OrderJpaAdapter implements IOrderPersistencePort{

    private final IOrderRepository iOrderRepository;
    private final IOrderDishRepository iOrderDishRepository;
    private final OrderEntityMapper orderEntityMapper;
    private final OrderDishEntityMapper orderDishEntityMapper;


    @Override
    public Order saveOrder(Order order) {
        return orderEntityMapper.toOrder(iOrderRepository.save(orderEntityMapper.toEntity(order)));
    }

    @Override
    public void saveCompleteOrder(Order order , List<OrderDish> orderDishList){
        iOrderRepository.save(orderEntityMapper.toEntity(order));
        orderDishList.forEach(orderDish -> iOrderDishRepository.save(orderDishEntityMapper.toEntity(orderDish)));
    }

    @Override
    public List<Order> getOrdersByCustomerId (Long userId){
        List<OrderEntity> userOrdersEntity = iOrderRepository.getOrdersByCustomerId(userId);
        List<Order> userOrders = new ArrayList<>();
        for (OrderEntity orderEntity : userOrdersEntity) {
            Order order = orderEntityMapper.toOrder(orderEntity);
            userOrders.add(order);
        }
        return userOrders;
    }
}
