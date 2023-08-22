package org.pragma.foodcourtmanager.domain.usecase;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.pragma.foodcourtmanager.domain.model.EmployeeRestaurant;
import org.pragma.foodcourtmanager.domain.model.Restaurant;
import org.pragma.foodcourtmanager.domain.spi.IEmployeeRestaurntPersistencePort;
import org.pragma.foodcourtmanager.util.MessageTestsEnum;
import org.pragma.foodcourtmanager.util.FactoryEmployeeRestaurant;

import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)

class EmployeeRestaurantUseCaseTest{

    @Mock
    private IEmployeeRestaurntPersistencePort iEmployeeRestaurntPersistencePort;

    @InjectMocks
    private EmployeeRestaurantUseCase employeeRestaurantUseCase;
    @Test
    void saveEmployeeRestaurant (){
        EmployeeRestaurant expectedObject = FactoryEmployeeRestaurant.mockObject;
        when(iEmployeeRestaurntPersistencePort.saveEmployeeRestaurant(expectedObject)).thenReturn(expectedObject);
        EmployeeRestaurant resultObject = employeeRestaurantUseCase.saveEmployeeRestaurant(expectedObject);
        Assertions.assertEquals(expectedObject.getEmployeeId(),resultObject.getEmployeeId(), MessageTestsEnum.ORDER_ID_DEBIL_ENTITY_EQUAL.getMessage());
        Assertions.assertEquals(expectedObject.getRestaurantId(),resultObject.getRestaurantId(), MessageTestsEnum.RESTAURANT_ID_ORDER_EQUAL.getMessage());
        verify(iEmployeeRestaurntPersistencePort, times(1)).saveEmployeeRestaurant(expectedObject);
    }

    @Test
    void getEmployeeRestaurant (){
        EmployeeRestaurant expectedObject = FactoryEmployeeRestaurant.mockObject;
        when(iEmployeeRestaurntPersistencePort.getEmployeeRestaurant(1L)).thenReturn(expectedObject);
        EmployeeRestaurant resultObject = employeeRestaurantUseCase.getEmployeeRestaurant(1L);
        Assertions.assertEquals(expectedObject.getEmployeeId(),resultObject.getEmployeeId(), MessageTestsEnum.ORDER_ID_DEBIL_ENTITY_EQUAL.getMessage());
        Assertions.assertEquals(expectedObject.getRestaurantId(),resultObject.getRestaurantId(), MessageTestsEnum.RESTAURANT_ID_ORDER_EQUAL.getMessage());
        verify(iEmployeeRestaurntPersistencePort, times(1)).getEmployeeRestaurant(1L);

    }
}