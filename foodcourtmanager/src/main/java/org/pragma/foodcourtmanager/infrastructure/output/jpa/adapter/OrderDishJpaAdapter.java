package org.pragma.foodcourtmanager.infrastructure.output.jpa.adapter;

import lombok.RequiredArgsConstructor;
import org.pragma.foodcourtmanager.domain.model.OrderDish;
import org.pragma.foodcourtmanager.domain.spi.IOrderDishPersistencePort;
import org.pragma.foodcourtmanager.infrastructure.output.jpa.entity.OrderDishEntity;
import org.pragma.foodcourtmanager.infrastructure.output.jpa.mapper.OrderDishEntityMapper;
import org.pragma.foodcourtmanager.infrastructure.output.jpa.repository.IOrderDishRepository;

@RequiredArgsConstructor
public class OrderDishJpaAdapter implements IOrderDishPersistencePort{

    private final IOrderDishRepository iOrderDishRepository;
    private final OrderDishEntityMapper orderDishEntityMapper;

    @Override
    public OrderDish saveOrderDish(OrderDish orderDish) {
        System.out.println("El orderdish que llega a saveOrderDish jpa " + orderDish.toString());

        System.out.println("El mapper a Entity " + orderDishEntityMapper.toEntity(orderDish).toString());

        OrderDishEntity orderDishEntity = iOrderDishRepository.save(orderDishEntityMapper.toEntity(orderDish));
        System.out.println("El orderdish que llega a saveOrderDish en entity " + orderDishEntity.toString());

        return orderDishEntityMapper.toOrderDish(orderDishEntity);
    }

}
