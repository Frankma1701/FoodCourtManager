package org.pragma.foodcourtmanager.infrastructure.output.jpa.adapter;

import lombok.RequiredArgsConstructor;
import org.pragma.foodcourtmanager.domain.model.Dish;
import org.pragma.foodcourtmanager.domain.model.Restaurant;
import org.pragma.foodcourtmanager.domain.spi.IDishPersistencePort;
import org.pragma.foodcourtmanager.infrastructure.exception.*;
import org.pragma.foodcourtmanager.infrastructure.output.jpa.entity.DishEntity;
import org.pragma.foodcourtmanager.infrastructure.output.jpa.mapper.DishEntityMapper;
import org.pragma.foodcourtmanager.infrastructure.output.jpa.repository.IDishRepository;

import java.util.List;

@RequiredArgsConstructor
public class DishJpaAdapter implements IDishPersistencePort{

    private final IDishRepository iDishRepository;
    private final DishEntityMapper dishEntityMapper;

    @Override
    public Dish saveDish(Dish dish) {
        DishEntity dishEntity = iDishRepository.save(dishEntityMapper.toEntity(dish));
        return dishEntityMapper.toDish(dishEntity);
    }

    @Override
    public List<Dish> getAllDishes() {
        List<DishEntity> dishEntityList = iDishRepository.findAll();
        if(dishEntityList.isEmpty()){
            throw new NoDataFoundException();
        }
        return dishEntityMapper.toDishList(dishEntityList);
    }

    @Override
    public Dish getDish(Long id) {
        return dishEntityMapper.toDish(iDishRepository.findById(id).orElseThrow(DishNotFoundException::new));
    }

    @Override
    public void updateDish(Dish dish) {
        iDishRepository.save(dishEntityMapper.toEntity(dish));
    }

}
