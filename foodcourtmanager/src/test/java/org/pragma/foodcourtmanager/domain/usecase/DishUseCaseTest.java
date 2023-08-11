package org.pragma.foodcourtmanager.domain.usecase;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.pragma.foodcourtmanager.domain.model.Dish;
import org.pragma.foodcourtmanager.domain.spi.IDishPersistencePort;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class DishUseCaseTest{

    @Mock
    private IDishPersistencePort iDishPersistencePort;

    @InjectMocks
    private DishUseCase dishUseCase;

    private Dish mockObject;
    private Dish mockObject1;

    private Dish expectedObject;

    private Dish expectedUpdateObject;

    @BeforeEach
    void setUp (){
         mockObject = new Dish(1L, "Plato 1", 101L, "Descripción del plato 1", 10.99, 201L, "url_imagen_1", true);
         mockObject1 = new Dish(2L, "Plato 2", 102L, "Descripción del plato 2", 15.50, 202L, "url_imagen_2", true);
         expectedObject = new Dish(1L, "Plato 1", 101L, "Descripción del plato 1", 10.99, 201L, "url_imagen_1", true);
         expectedUpdateObject = new Dish(4L, "Plato 4", 104L, "Descripción del plato 4", 12.00, 204L, "url_imagen_4", true);
    }

    @Test
    void saveDish (){
        Mockito.when(iDishPersistencePort.saveDish(mockObject)).thenReturn(mockObject);
        Dish resultObject = dishUseCase.saveDish(mockObject);
        Assertions.assertEquals(expectedObject.getId(),resultObject.getId(), "Los ids de los platos son iguales");
        Assertions.assertEquals(expectedObject.getName(),resultObject.getName(), "Los ids de los platos son iguales");
        Mockito.verify(iDishPersistencePort).saveDish(mockObject);
    }

    @Test
    void getDish (){
        Mockito.when(iDishPersistencePort.getDish(1L)).thenReturn(mockObject);
        Dish resultObject = dishUseCase.getDish(1L);
        Assertions.assertEquals(expectedObject.getId(),resultObject.getId(), "Los ids de los platos son iguales");
        Assertions.assertEquals(expectedObject.getName(),resultObject.getName(), "Los ids de los platos son iguales");
        Mockito.verify(iDishPersistencePort).getDish(1L);
    }


    @Test
    void updateDish (){
        dishUseCase.updateDish(expectedUpdateObject);
        Mockito.verify(iDishPersistencePort).updateDish(expectedUpdateObject);
    }

    @Test
    void getAllDishes (){
        Mockito.when(iDishPersistencePort.getAllDishes()).thenReturn(Arrays.asList(mockObject,mockObject1));
        Assertions.assertNotNull(dishUseCase.getAllDishes());
        dishUseCase.saveDish(mockObject);
        dishUseCase.saveDish(mockObject1);
        List<Dish> dishList = dishUseCase.getAllDishes();
        Assertions.assertNotNull(dishList);
        Assertions.assertEquals(2,dishList.size());
    }
}