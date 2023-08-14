package org.pragma.foodcourtmanager.domain.usecase;
import org.pragma.foodcourtmanager.domain.api.IOrderDishServicePort;
import org.pragma.foodcourtmanager.domain.model.OrderDish;
import org.pragma.foodcourtmanager.domain.spi.IOrderDishPersistencePort;

public class OrderDishUseCase implements IOrderDishServicePort{

private final IOrderDishPersistencePort iOrderDishPersistencePort;

public OrderDishUseCase (IOrderDishPersistencePort iOrderDishPersistencePort) {
    this.iOrderDishPersistencePort = iOrderDishPersistencePort;
}
@Override
public OrderDish saveOrderDish (OrderDish orderDish) {
    return iOrderDishPersistencePort.saveOrderDish(orderDish);
}


}
