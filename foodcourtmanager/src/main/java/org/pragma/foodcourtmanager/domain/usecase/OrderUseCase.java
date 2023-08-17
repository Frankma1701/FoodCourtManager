package org.pragma.foodcourtmanager.domain.usecase;
import org.pragma.foodcourtmanager.domain.api.IOrderServicePort;
import org.pragma.foodcourtmanager.domain.model.Order;
import org.pragma.foodcourtmanager.domain.model.OrderDish;
import org.pragma.foodcourtmanager.domain.model.OrderStatus;
import org.pragma.foodcourtmanager.domain.spi.IOrderPersistencePort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class OrderUseCase implements IOrderServicePort{

    private IOrderPersistencePort iOrderPersistencePort;

    public OrderUseCase (IOrderPersistencePort iOrderPersistencePort) {
        this.iOrderPersistencePort = iOrderPersistencePort;
    }
    @Override
    public Order saveOrder(Order order) {
        return iOrderPersistencePort.saveOrder(order);
    }
    @Override
    public void saveCompleteOrder (Order order, List<OrderDish> orderDishList){
         iOrderPersistencePort.saveCompleteOrder(order,orderDishList);
    }

    @Override
    public List<Order> getOrdersByCustomerId (Long userId){
        return iOrderPersistencePort.getOrdersByCustomerId(userId);
    }

    @Override
    public void assignOrder (Order order){
        iOrderPersistencePort.assignOrder(order);
    }

    @Override
    public void orderReady (Order order){
        iOrderPersistencePort.orderReady(order);
    }

    @Override
    public void deliverOrder (Order order){
        iOrderPersistencePort.deliverOrder(order);
    }

    @Override
    public void cancelOrder (Order order){
        iOrderPersistencePort.cancelOrder(order);
    }


    @Override
    public Order getOrder (Long orderId){
        return iOrderPersistencePort.getOrder(orderId);
    }

    @Override
    public Page<Order> getAllOrders(Long restaurantId, OrderStatus orderStatus, Pageable pageable){
        return iOrderPersistencePort.getAllOrders(restaurantId,orderStatus, pageable);
    }



}
