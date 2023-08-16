package org.pragma.foodcourtmanager.infrastructure.configuration;

import lombok.RequiredArgsConstructor;
import org.pragma.foodcourtmanager.domain.api.*;
import org.pragma.foodcourtmanager.domain.spi.*;
import org.pragma.foodcourtmanager.domain.usecase.*;
import org.pragma.foodcourtmanager.infrastructure.output.jpa.adapter.*;
import org.pragma.foodcourtmanager.infrastructure.output.jpa.mapper.*;
import org.pragma.foodcourtmanager.infrastructure.output.jpa.repository.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {

    private final IRestaurantRepository iRestaurantRepository;
    private final RestaurantEntityMapper restaurantEntityMapper;
    private final IDishRepository iDishRepository;
    private final DishEntityMapper dishEntityMapper;

    private final IOrderRepository iOrderRepository;
    private final OrderEntityMapper orderEntityMapper;

    private final IOrderDishRepository iOrderDishRepository;
    private final OrderDishEntityMapper orderDishEntityMapper;

    private final IEmployeeRestaurantRepository iEmployeeRestaurantRepository;
    private final EmployeeRestaurantEntityMapper employeeRestaurantEntityMapper;


    @Bean
    public IRestaurantPersistencePort restaurantPersistencePort(){
        return new RestaurantJpaAdapter(iRestaurantRepository,restaurantEntityMapper);
    }
    @Bean
    public IRestaurantServicePort restaurantServicePort(){
        return new RestaurantUseCase(restaurantPersistencePort());
    }

    @Bean
    public IDishPersistencePort dishPersistencePort(){
        return new DishJpaAdapter(iDishRepository,dishEntityMapper);
    }

    @Bean
    public IDishServicePort dishServicePort(){
        return new DishUseCase(dishPersistencePort());
    }

    @Bean
    public IOrderPersistencePort orderPersistencePort(){
        return new OrderJpaAdapter(iOrderRepository,iOrderDishRepository,orderEntityMapper,orderDishEntityMapper);
    }
    @Bean
    public IOrderServicePort orderServicePort(){
        return new OrderUseCase(orderPersistencePort());
    }

    @Bean
    public IOrderDishPersistencePort orderDishPersistencePort(){
        return new OrderDishJpaAdapter(iOrderDishRepository,orderDishEntityMapper);
    }
    @Bean
    public IOrderDishServicePort orderDishServicePort(){
        return new OrderDishUseCase(orderDishPersistencePort());
    }

    @Bean
    public IEmployeeRestaurntPersistencePort employeeRestaurantPersistencePort(){
        return new EmployeeRestaurantJpaAdapter(iEmployeeRestaurantRepository,employeeRestaurantEntityMapper);
    }
    @Bean
    public IEmployeeRestaurantServicePort employeeRestaurantServicePort(){
        return new EmployeeRestaurantUseCase(employeeRestaurantPersistencePort());
    }
}
