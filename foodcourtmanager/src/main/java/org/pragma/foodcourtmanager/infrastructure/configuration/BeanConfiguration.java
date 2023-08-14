package org.pragma.foodcourtmanager.infrastructure.configuration;

import lombok.RequiredArgsConstructor;
import org.pragma.foodcourtmanager.domain.api.IDishServicePort;
import org.pragma.foodcourtmanager.domain.api.IOrderDishServicePort;
import org.pragma.foodcourtmanager.domain.api.IOrderServicePort;
import org.pragma.foodcourtmanager.domain.api.IRestaurantServicePort;
import org.pragma.foodcourtmanager.domain.spi.IDishPersistencePort;
import org.pragma.foodcourtmanager.domain.spi.IOrderDishPersistencePort;
import org.pragma.foodcourtmanager.domain.spi.IOrderPersistencePort;
import org.pragma.foodcourtmanager.domain.spi.IRestaurantPersistencePort;
import org.pragma.foodcourtmanager.domain.usecase.DishUseCase;
import org.pragma.foodcourtmanager.domain.usecase.OrderDishUseCase;
import org.pragma.foodcourtmanager.domain.usecase.OrderUseCase;
import org.pragma.foodcourtmanager.domain.usecase.RestaurantUseCase;
import org.pragma.foodcourtmanager.infrastructure.output.jpa.adapter.DishJpaAdapter;
import org.pragma.foodcourtmanager.infrastructure.output.jpa.adapter.OrderDishJpaAdapter;
import org.pragma.foodcourtmanager.infrastructure.output.jpa.adapter.OrderJpaAdapter;
import org.pragma.foodcourtmanager.infrastructure.output.jpa.adapter.RestaurantJpaAdapter;
import org.pragma.foodcourtmanager.infrastructure.output.jpa.mapper.DishEntityMapper;
import org.pragma.foodcourtmanager.infrastructure.output.jpa.mapper.OrderDishEntityMapper;
import org.pragma.foodcourtmanager.infrastructure.output.jpa.mapper.OrderEntityMapper;
import org.pragma.foodcourtmanager.infrastructure.output.jpa.mapper.RestaurantEntityMapper;
import org.pragma.foodcourtmanager.infrastructure.output.jpa.repository.IDishRepository;
import org.pragma.foodcourtmanager.infrastructure.output.jpa.repository.IOrderDishRepository;
import org.pragma.foodcourtmanager.infrastructure.output.jpa.repository.IOrderRepository;
import org.pragma.foodcourtmanager.infrastructure.output.jpa.repository.IRestaurantRepository;
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

}
