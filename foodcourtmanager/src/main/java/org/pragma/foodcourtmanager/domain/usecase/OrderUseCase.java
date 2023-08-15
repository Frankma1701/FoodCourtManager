package org.pragma.foodcourtmanager.domain.usecase;
import org.pragma.foodcourtmanager.domain.api.IOrderServicePort;
import org.pragma.foodcourtmanager.domain.model.Order;
import org.pragma.foodcourtmanager.domain.model.OrderDish;
import org.pragma.foodcourtmanager.domain.spi.IOrderPersistencePort;

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

}
