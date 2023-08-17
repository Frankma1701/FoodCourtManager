package org.pragma.foodcourtmanager.domain.usecase;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.pragma.foodcourtmanager.domain.model.Dish;
import org.pragma.foodcourtmanager.domain.model.Order;
import org.pragma.foodcourtmanager.domain.model.OrderDish;
import org.pragma.foodcourtmanager.domain.model.OrderStatus;
import org.pragma.foodcourtmanager.domain.spi.IOrderPersistencePort;
import org.pragma.foodcourtmanager.util.ConstantsTests;
import org.pragma.foodcourtmanager.util.FactoryOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
        assertEquals(expectedObject.getId(),resultObject.getId(), ConstantsTests.CHEF_ID_ORDER_EQUAL);
        assertEquals(expectedObject.getCustomerId(),resultObject.getCustomerId(), ConstantsTests.CUSTOMER_ID_ORDER_EQUAL);
        assertEquals(expectedObject.getDate(),resultObject.getDate(), ConstantsTests.DATE_ORDER_EQUAL);
        assertEquals(expectedObject.getOrderStatus(),resultObject.getOrderStatus(), ConstantsTests.ORDER_STATUS_EQUAL);
        assertEquals(expectedObject.getEmployeeId(),resultObject.getEmployeeId(), ConstantsTests.CHEF_ID_ORDER_EQUAL);
        assertEquals(expectedObject.getRestaurantId(),resultObject.getRestaurantId(), ConstantsTests.RESTAURANT_ID_ORDER_EQUAL);
        verify(orderPersistencePort, times(1)).saveOrder(expectedObject);
    }


    @Test
    public void testSaveCompleteOrder() {
        Order order = FactoryOrder.mockObject;
        List<OrderDish> orderDishList = new ArrayList<>();
        orderUseCase.saveCompleteOrder(order, orderDishList);
        verify(orderPersistencePort, times(1)).saveCompleteOrder(order, orderDishList);
    }

    @Test
    public void testGetAllOrders() {
        Long restaurantId = 1L;
        OrderStatus orderStatus = OrderStatus.PENDING;
        Pageable pageable = Pageable.ofSize(10).withPage(0);
        Page<Order> mockOrderList = mock(Page.class);
        when(orderPersistencePort.getAllOrders(restaurantId, orderStatus, pageable)).thenReturn(mockOrderList);
        Page<Order> result = orderUseCase.getAllOrders(restaurantId, orderStatus, pageable);
        verify(orderPersistencePort, times(1)).getAllOrders(restaurantId, orderStatus, pageable);
        Assertions.assertEquals(result.getTotalElements(), mockOrderList.getTotalElements());
    }


    @Test
    public void testGetOrder() {
        Long orderId = 1L;
        Order expectedObject = FactoryOrder.mockObject;
        when(orderPersistencePort.getOrder(orderId)).thenReturn(expectedObject);
        Order resultObject = orderUseCase.getOrder(orderId);
        assertEquals(expectedObject, resultObject);
        assertEquals(expectedObject.getId(),resultObject.getId(), ConstantsTests.CHEF_ID_ORDER_EQUAL);
        assertEquals(expectedObject.getCustomerId(),resultObject.getCustomerId(), ConstantsTests.CUSTOMER_ID_ORDER_EQUAL);
        assertEquals(expectedObject.getDate(),resultObject.getDate(), ConstantsTests.DATE_ORDER_EQUAL);
        assertEquals(expectedObject.getOrderStatus(),resultObject.getOrderStatus(), ConstantsTests.ORDER_STATUS_EQUAL);
        assertEquals(expectedObject.getEmployeeId(),resultObject.getEmployeeId(), ConstantsTests.CHEF_ID_ORDER_EQUAL);
        assertEquals(expectedObject.getRestaurantId(),resultObject.getRestaurantId(), ConstantsTests.RESTAURANT_ID_ORDER_EQUAL);
        verify(orderPersistencePort).getOrder(orderId);
    }

    @Test
    public void testAssignOrder() {
        Order orderToAssign = FactoryOrder.mockObject;
        orderUseCase.assignOrder(orderToAssign);
        verify(orderPersistencePort).assignOrder(orderToAssign);
    }

    @Test
    void deliverOrder (){
        Order orderToDelivery = FactoryOrder.mockObjectPin;
        orderUseCase.deliverOrder(orderToDelivery);
        verify(orderPersistencePort).deliverOrder(orderToDelivery);
    }
}
