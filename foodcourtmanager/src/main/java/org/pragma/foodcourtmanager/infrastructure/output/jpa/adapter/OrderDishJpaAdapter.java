package org.pragma.foodcourtmanager.infrastructure.output.jpa.adapter;

import lombok.RequiredArgsConstructor;
import org.pragma.foodcourtmanager.domain.model.OrderDish;
import org.pragma.foodcourtmanager.domain.spi.IOrderDishPersistencePort;
import org.pragma.foodcourtmanager.application.exception.NoDataFoundException;
import org.pragma.foodcourtmanager.infrastructure.output.jpa.entity.OrderDishEntity;
import org.pragma.foodcourtmanager.infrastructure.output.jpa.mapper.OrderDishEntityMapper;
import org.pragma.foodcourtmanager.infrastructure.output.jpa.repository.IOrderDishRepository;

import java.util.List;

@RequiredArgsConstructor
public class OrderDishJpaAdapter implements IOrderDishPersistencePort{

    private final IOrderDishRepository iOrderDishRepository;
    private final OrderDishEntityMapper orderDishEntityMapper;

    @Override
    public OrderDish saveOrderDish(OrderDish orderDish) {
        OrderDishEntity orderDishEntity = iOrderDishRepository.save(orderDishEntityMapper.toEntity(orderDish));
        return orderDishEntityMapper.toOrderDish(orderDishEntity);
    }

    @Override
    public List<OrderDish> getAllOrderDish (Long orderId){

        List<OrderDishEntity> orderDishEntityList = iOrderDishRepository.findByOrderEntity_Id(orderId);

        System.out.println("La lista de OrderDishEntity en el Jpa ");
        orderDishEntityList.forEach(orderDishEntity -> System.out.println(orderDishEntity.toString()));

        List<OrderDish> orderDishList = orderDishEntityMapper.toOrderDishList(orderDishEntityList);
        System.out.println("La lista de OrderDish en el Jpa ");
        orderDishList.forEach(orderDish -> System.out.println(orderDish.toString()));
        if(orderDishList.isEmpty()){
            throw new NoDataFoundException();
        }
        return orderDishList;
    }

}
