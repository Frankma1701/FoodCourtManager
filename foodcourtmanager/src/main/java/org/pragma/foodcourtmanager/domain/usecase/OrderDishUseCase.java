package org.pragma.foodcourtmanager.domain.usecase;

import org.aspectj.weaver.ast.Or;
import org.pragma.foodcourtmanager.domain.api.IOrderDishServicePort;
import org.pragma.foodcourtmanager.domain.model.OrderDish;
import org.pragma.foodcourtmanager.domain.spi.IOrderDishPersistencePort;
import java.util.List;

public class OrderDishUseCase implements IOrderDishServicePort{

    private final IOrderDishPersistencePort iOrderDishPersistencePort;

    public OrderDishUseCase (IOrderDishPersistencePort iOrderDishPersistencePort){
        this.iOrderDishPersistencePort = iOrderDishPersistencePort;
    }

    @Override
    public OrderDish saveOrderDish (OrderDish orderDish){
        return iOrderDishPersistencePort.saveOrderDish(orderDish);
    }

    @Override
    public List<OrderDish> getAllOrderDish (Long orderId){
        List<OrderDish> orderDishList = iOrderDishPersistencePort.getAllOrderDish(orderId);
        System.out.println("En el usecase llega");
        orderDishList.forEach(orderDish -> System.out.println(orderDish.toString()));
        return orderDishList;
    }


}
