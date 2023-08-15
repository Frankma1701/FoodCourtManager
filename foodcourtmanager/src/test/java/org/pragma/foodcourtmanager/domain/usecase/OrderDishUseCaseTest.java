package org.pragma.foodcourtmanager.domain.usecase;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.pragma.foodcourtmanager.domain.model.OrderDish;
import org.pragma.foodcourtmanager.domain.spi.IOrderDishPersistencePort;
import org.pragma.foodcourtmanager.domain.spi.IOrderPersistencePort;
import org.pragma.foodcourtmanager.util.ConstantsTests;
import org.pragma.foodcourtmanager.util.FactoryOrderDish;

import static org.junit.jupiter.api.Assertions.*;
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
    public void testSaveOrderDish (){
        OrderDish expectedObject = FactoryOrderDish.mockObject;
        when(orderDishPersistencePort.saveOrderDish(expectedObject)).thenReturn(expectedObject);
        OrderDish resultObject = orderDishUseCase.saveOrderDish(expectedObject);
        Assertions.assertEquals(expectedObject.getDishId(),resultObject.getDishId(), ConstantsTests.ORDER_ID_DEBIL_ENTITY_EQUAL);
        Assertions.assertEquals(expectedObject.getDishId(),resultObject.getDishId(), ConstantsTests.DISH_ID_DEBIL_ENTITY_EQUAL);
        Assertions.assertEquals(expectedObject.getDishId(),resultObject.getDishId(), ConstantsTests.QUANTITY_DEBIL_ENTITY_EQUAL);
        verify(orderDishPersistencePort, times(1)).saveOrderDish(expectedObject);
    }
}