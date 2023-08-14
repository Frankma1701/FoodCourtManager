package org.pragma.foodcourtmanager.infrastructure.output.jpa.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import org.pragma.foodcourtmanager.domain.model.Dish;
import org.pragma.foodcourtmanager.domain.model.Restaurant;
import org.pragma.foodcourtmanager.infrastructure.output.jpa.entity.DishEntity;
import org.pragma.foodcourtmanager.infrastructure.output.jpa.entity.RestaurantEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface DishEntityMapper{


    @Mapping(target = "restaurantEntity.id" , source = "dish.restaurantId")
    @Mapping(target = "categoryEntity.id" , source = "dish.categoryId")
    DishEntity toEntity(Dish dish);

    @Mapping(target = "restaurantId" , source = "dishEntity.restaurantEntity.id")
    @Mapping(target = "id" , source = "dishEntity.id")
    @Mapping(target = "categoryId" , source = "dishEntity.categoryEntity.id")

    Dish toDish(DishEntity dishEntity);
    default Page<Dish> toDishPage (Page<DishEntity> dishEntityPage) {
        List<Dish> dishes = dishEntityPage.stream()
                .map(this::toDish)
                .toList();

        return new PageImpl<>(dishes);
    }

}
