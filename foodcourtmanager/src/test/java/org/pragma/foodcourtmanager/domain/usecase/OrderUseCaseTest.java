package org.pragma.foodcourtmanager.domain.usecase;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.pragma.foodcourtmanager.domain.model.Order;
import org.pragma.foodcourtmanager.domain.model.OrderDish;
import org.pragma.foodcourtmanager.domain.model.OrderStatus;
import org.pragma.foodcourtmanager.domain.spi.IOrderPersistencePort;
import org.pragma.foodcourtmanager.util.ConstantsTests;
import org.pragma.foodcourtmanager.util.FactoryOrder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class OrderUseCaseTest {

    @Mock
    private IOrderPersistencePort orderPersistencePort;

    private OrderUseCase orderUseCase;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        orderUseCase = new OrderUseCase(orderPersistencePort);
    }
    @Test
    public void testSaveOrder() {
        Order expectedObject = FactoryOrder.mockObject;
        when(orderPersistencePort.saveOrder(expectedObject)).thenReturn(expectedObject);
        Order resultObject = orderUseCase.saveOrder(expectedObject);
        Assertions.assertEquals(expectedObject.getId(),resultObject.getId(), ConstantsTests.CHEF_ID_ORDER_EQUAL);
        Assertions.assertEquals(expectedObject.getCustomerId(),resultObject.getCustomerId(), ConstantsTests.CUSTOMER_ID_ORDER_EQUAL);
        Assertions.assertEquals(expectedObject.getDate(),resultObject.getDate(), ConstantsTests.DATE_ORDER_EQUAL);
        Assertions.assertEquals(expectedObject.getOrderStatus(),resultObject.getOrderStatus(), ConstantsTests.ORDER_STATUS_EQUAL);
        Assertions.assertEquals(expectedObject.getChefId(),resultObject.getChefId(), ConstantsTests.CHEF_ID_ORDER_EQUAL);
        Assertions.assertEquals(expectedObject.getRestaurantId(),resultObject.getRestaurantId(), ConstantsTests.RESTAURANT_ID_ORDER_EQUAL);
        verify(orderPersistencePort, times(1)).saveOrder(expectedObject);
    }


    @Test
    public void testSaveCompleteOrder() {
        Order order = FactoryOrder.mockObject;
        List<OrderDish> orderDishList = new ArrayList<>();
        orderUseCase.saveCompleteOrder(order, orderDishList);
        verify(orderPersistencePort, times(1)).saveCompleteOrder(order, orderDishList);
    }
}
