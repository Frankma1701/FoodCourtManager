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
import org.pragma.foodcourtmanager.util.MessageTestsEnum;
import org.pragma.foodcourtmanager.util.FactoryOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
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
    public void saveOrder() {
        Order expectedObject = FactoryOrder.mockObject;
        when(orderPersistencePort.saveOrder(expectedObject)).thenReturn(expectedObject);
        Order resultObject = orderUseCase.saveOrder(expectedObject);
        assertEquals(expectedObject.getId(),resultObject.getId(), MessageTestsEnum.CHEF_ID_ORDER_EQUAL.getMessage());
        assertEquals(expectedObject.getCustomerId(),resultObject.getCustomerId(), MessageTestsEnum.CUSTOMER_ID_ORDER_EQUAL.getMessage());
        assertEquals(expectedObject.getDate(),resultObject.getDate(), MessageTestsEnum.DATE_ORDER_EQUAL.getMessage());
        assertEquals(expectedObject.getOrderStatus(),resultObject.getOrderStatus(), MessageTestsEnum.ORDER_STATUS_EQUAL.getMessage());
        assertEquals(expectedObject.getEmployeeId(),resultObject.getEmployeeId(), MessageTestsEnum.CHEF_ID_ORDER_EQUAL.getMessage());
        assertEquals(expectedObject.getRestaurantId(),resultObject.getRestaurantId(), MessageTestsEnum.RESTAURANT_ID_ORDER_EQUAL.getMessage());
        verify(orderPersistencePort, times(1)).saveOrder(expectedObject);
    }


    @Test
    public void saveCompleteOrder() {
        Order order = FactoryOrder.mockObject;
        List<OrderDish> orderDishList = new ArrayList<>();
        orderUseCase.saveCompleteOrder(order, orderDishList);
        verify(orderPersistencePort, times(1)).saveCompleteOrder(order, orderDishList);
    }

    @Test
    public void getAllOrders() {
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
    void getOrdersByCustomerId (){
        Long customerId = 1L;
        List<Order> mockOrderList = mock(List.class);
        when(orderPersistencePort.getOrdersByCustomerId(customerId)).thenReturn(mockOrderList);
        List<Order> result = orderUseCase.getOrdersByCustomerId(customerId);
        verify(orderPersistencePort, times(1)).getOrdersByCustomerId(customerId);
        Assertions.assertEquals(result.size(), mockOrderList.size());
    }


    @Test
    public void getOrder() {
        Long orderId = 1L;
        Order expectedObject = FactoryOrder.mockObject;
        when(orderPersistencePort.getOrder(orderId)).thenReturn(expectedObject);
        Order resultObject = orderUseCase.getOrder(orderId);
        assertEquals(expectedObject, resultObject);
        assertEquals(expectedObject.getId(),resultObject.getId(), MessageTestsEnum.CHEF_ID_ORDER_EQUAL.getMessage());
        assertEquals(expectedObject.getCustomerId(),resultObject.getCustomerId(), MessageTestsEnum.CUSTOMER_ID_ORDER_EQUAL.getMessage());
        assertEquals(expectedObject.getDate(),resultObject.getDate(), MessageTestsEnum.DATE_ORDER_EQUAL.getMessage());
        assertEquals(expectedObject.getOrderStatus(),resultObject.getOrderStatus(), MessageTestsEnum.ORDER_STATUS_EQUAL.getMessage());
        assertEquals(expectedObject.getEmployeeId(),resultObject.getEmployeeId(), MessageTestsEnum.CHEF_ID_ORDER_EQUAL.getMessage());
        assertEquals(expectedObject.getRestaurantId(),resultObject.getRestaurantId(), MessageTestsEnum.RESTAURANT_ID_ORDER_EQUAL.getMessage());
        verify(orderPersistencePort).getOrder(orderId);
    }

    @Test
    public void assignOrder() {
        Order orderToAssign = FactoryOrder.mockObject;
        orderUseCase.assignOrder(orderToAssign);
        verify(orderPersistencePort).assignOrder(orderToAssign);
    }

    @Test
    public void orderReady() {
        Order orderToReady = FactoryOrder.mockObject;
        orderUseCase.orderReady(orderToReady);
        verify(orderPersistencePort).orderReady(orderToReady);
    }

    @Test
    void deliverOrder (){
        Order orderToDelivery = FactoryOrder.mockObjectPin;
        orderUseCase.deliverOrder(orderToDelivery);
        verify(orderPersistencePort).deliverOrder(orderToDelivery);
    }

    @Test
    void cancelOrder (){
        Order orderToCancel = FactoryOrder.mockObject;
        orderUseCase.cancelOrder(orderToCancel);
        verify(orderPersistencePort).cancelOrder(orderToCancel);
    }


}
