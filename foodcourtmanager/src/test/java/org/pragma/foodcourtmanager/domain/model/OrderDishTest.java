package org.pragma.foodcourtmanager.domain.model;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
@ExtendWith(MockitoExtension.class)

class OrderDishTest{



    @Test
    void testSetters() {
        OrderDish orderDish = new OrderDish(331L,654L,2L);
        Long newOrderId = 123L;
        Long newDishId = 456L;
        Long newQuantity = 3L;
        orderDish.setOrderId(newOrderId);
        orderDish.setDishId(newDishId);
        orderDish.setQuantity(newQuantity);
        assertEquals(newOrderId, orderDish.getOrderId());
        assertEquals(newDishId, orderDish.getDishId());
        assertEquals(newQuantity, orderDish.getQuantity());

    }

}