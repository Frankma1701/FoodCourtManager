package org.pragma.foodcourtmanager.infrastructure.output.jpa.adapter;

import lombok.RequiredArgsConstructor;
import org.pragma.foodcourtmanager.domain.model.Order;
import org.pragma.foodcourtmanager.domain.model.OrderDish;
import org.pragma.foodcourtmanager.domain.model.OrderStatus;
import org.pragma.foodcourtmanager.domain.spi.IOrderPersistencePort;
import org.pragma.foodcourtmanager.infrastructure.exception.NoDataOrderFoundException;
import org.pragma.foodcourtmanager.infrastructure.output.jpa.entity.OrderEntity;
import org.pragma.foodcourtmanager.infrastructure.output.jpa.mapper.OrderDishEntityMapper;
import org.pragma.foodcourtmanager.infrastructure.output.jpa.mapper.OrderEntityMapper;
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
        order.setEmployeeId(0L);
        order.setVerificationCode("");
        return orderEntityMapper.toOrder(iOrderRepository.save(orderEntityMapper.toEntity(order)));
    }

    @Override
    public void saveCompleteOrder(Order order , List<OrderDish> orderDishList){
        order.setEmployeeId(0L);
        order.setVerificationCode("");
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

    @Override
    public void assignOrder (Order order){
        System.out.println("La order a actualizar es " + order.toString());
        iOrderRepository.save(orderEntityMapper.toEntity(order));
    }

    @Override
    public void orderReady (Order order){
        iOrderRepository.save(orderEntityMapper.toEntity(order));
    }

    @Override
    public Order getOrder (Long orderId){
        return  orderEntityMapper.toOrder(iOrderRepository.getReferenceById(orderId));
    }


    @Override
    public Page<Order> getAllOrders ( Long restaurantId,OrderStatus orderStatus , Pageable pageable){
        Page<OrderEntity> orderEntityPage = iOrderRepository.getOrdersByRestaurantIdId(1L,orderStatus, pageable);

        orderEntityPage.forEach(orderEntity -> System.out.println(orderEntity.toString()));
        if(orderEntityPage.isEmpty()){
            throw new NoDataOrderFoundException();
        }
        return orderEntityMapper.toOrderPage(orderEntityPage);    }
}
