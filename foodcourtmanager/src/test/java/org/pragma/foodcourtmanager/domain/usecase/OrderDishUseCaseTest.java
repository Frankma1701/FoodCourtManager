package org.pragma.foodcourtmanager.domain.usecase;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.pragma.foodcourtmanager.domain.model.OrderDish;
import org.pragma.foodcourtmanager.domain.spi.IOrderDishPersistencePort;
import org.pragma.foodcourtmanager.util.MessageTestsEnum;
import org.pragma.foodcourtmanager.util.FactoryOrderDish;

import java.util.List;

import static org.mockito.Mockito.*;

class OrderDishUseCaseTest{
    @Mock
    private IOrderDishPersistencePort orderDishPersistencePort;

    private OrderDishUseCase orderDishUseCase;

    @BeforeEach
    public void setUp (){
        MockitoAnnotations.initMocks(this);
        orderDishUseCase = new OrderDishUseCase(orderDishPersistencePort);
    }

    @Test
    public void saveOrderDish (){
        OrderDish expectedObject = FactoryOrderDish.mockObject;
        when(orderDishPersistencePort.saveOrderDish(expectedObject)).thenReturn(expectedObject);
        OrderDish resultObject = orderDishUseCase.saveOrderDish(expectedObject);
        Assertions.assertEquals(expectedObject.getDishId(),resultObject.getDishId(), MessageTestsEnum.ORDER_ID_DEBIL_ENTITY_EQUAL.getMessage());
        Assertions.assertEquals(expectedObject.getDishId(),resultObject.getDishId(), MessageTestsEnum.DISH_ID_DEBIL_ENTITY_EQUAL.getMessage());
        Assertions.assertEquals(expectedObject.getDishId(),resultObject.getDishId(), MessageTestsEnum.QUANTITY_DEBIL_ENTITY_EQUAL.getMessage());
        verify(orderDishPersistencePort, times(1)).saveOrderDish(expectedObject);
    }

    @Test
    void getAllOrderDish (){
        Long orderId = 1L;
        List<OrderDish> mockOrderList = mock(List.class);
        when(orderDishPersistencePort.getAllOrderDish(orderId)).thenReturn(mockOrderList);
        List<OrderDish> result = orderDishUseCase.getAllOrderDish(orderId);
        verify(orderDishPersistencePort, times(1)).getAllOrderDish(orderId);
        Assertions.assertEquals(result.size(), mockOrderList.size());
    }


}