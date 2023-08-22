package org.pragma.foodcourtmanager.domain.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest{

    @Test
    void testSetters() {
        Long id = 1L;
        Long customerId = 101L;
        LocalDateTime date = LocalDateTime.of(2023, 8, 14, 12, 0);
        OrderStatus orderStatus = OrderStatus.PENDING;
        Long employeeId = 201L;
        Long restaurantId = 301L;
        String verificationCode = "ABC123";

        Order order = new Order(id, customerId, date, orderStatus, employeeId, restaurantId, verificationCode);

        Long newId = 2L;
        Long newCustomerId = 102L;
        LocalDateTime newDate = LocalDateTime.of(2023, 8, 15, 12, 0);
        OrderStatus newOrderStatus = OrderStatus.DELIVERED;
        Long newEmployeeId = 202L;
        Long newRestaurantId = 302L;
        String newVerificationCode = "XYZ789";

        order.setId(newId);
        order.setCustomerId(newCustomerId);
        order.setDate(newDate);
        order.setOrderStatus(newOrderStatus);
        order.setEmployeeId(newEmployeeId);
        order.setRestaurantId(newRestaurantId);
        order.setVerificationCode(newVerificationCode);

        assertEquals(newId, order.getId());
        assertEquals(newCustomerId, order.getCustomerId());
        assertEquals(newDate, order.getDate());
        assertEquals(newOrderStatus, order.getOrderStatus());
        assertEquals(newEmployeeId, order.getEmployeeId());
        assertEquals(newRestaurantId, order.getRestaurantId());
        assertEquals(newVerificationCode, order.getVerificationCode());
    }

}