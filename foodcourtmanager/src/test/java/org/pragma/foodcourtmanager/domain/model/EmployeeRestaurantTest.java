package org.pragma.foodcourtmanager.domain.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeRestaurantTest{

    @Test
     void testSetters() {
        EmployeeRestaurant employeeRestaurant = new EmployeeRestaurant(100L,200L);
        Long employeeId = 101L;
        Long restaurantId = 201L;
        Long newEmployeeId = 102L;
        Long newRestaurantId = 202L;
        employeeRestaurant.setEmployeeId(newEmployeeId);
        employeeRestaurant.setRestaurantId(newRestaurantId);
        assertEquals(newEmployeeId, employeeRestaurant.getEmployeeId());
        assertEquals(newRestaurantId, employeeRestaurant.getRestaurantId());
    }

}