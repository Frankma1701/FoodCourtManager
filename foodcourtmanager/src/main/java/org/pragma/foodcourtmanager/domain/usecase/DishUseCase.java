package org.pragma.foodcourtmanager.domain.usecase;

import org.pragma.foodcourtmanager.application.dto.request.DishUpdateRequest;
import org.pragma.foodcourtmanager.domain.api.IDishServicePort;
import org.pragma.foodcourtmanager.domain.api.IRestaurantServicePort;
import org.pragma.foodcourtmanager.domain.model.Dish;
import org.pragma.foodcourtmanager.domain.model.Restaurant;
import org.pragma.foodcourtmanager.domain.spi.IDishPersistencePort;
import org.pragma.foodcourtmanager.domain.spi.IRestaurantPersistencePort;

import java.util.List;
// Conection with the infrastructure

public class DishUseCase implements IDishServicePort{

    private IDishPersistencePort iDishPersistencePort;

    public DishUseCase (IDishPersistencePort iDishPersistencePort) {
        this.iDishPersistencePort = iDishPersistencePort;
    }
    @Override
    public Dish saveDish(Dish dish) {
        return iDishPersistencePort.saveDish(dish);
    }

    @Override
    public Dish getDish(Long id) {
        return iDishPersistencePort.getDish(id);
    }
    @Override
    public List<Dish> getAllDishes() {
        return iDishPersistencePort.getAllDishes();
    }

    @Override
    public void updateDish(Dish dish) {
        iDishPersistencePort.updateDish(dish);
    }

}
