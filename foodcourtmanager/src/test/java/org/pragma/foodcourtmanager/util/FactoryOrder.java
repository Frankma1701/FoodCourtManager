package org.pragma.foodcourtmanager.util;

import org.pragma.foodcourtmanager.domain.model.Order;
import org.pragma.foodcourtmanager.domain.model.OrderStatus;

import java.time.LocalDate;

public class FactoryOrder{

    public static final Order mockObject = new Order(1L, 101L, LocalDate.of(2023, 8, 14), OrderStatus.PENDING, 201L, 301L , "");
    public static final Order mockObjectPin = new Order(1L, 101L, LocalDate.of(2023, 8, 14), OrderStatus.PENDING, 201L, 301L,"2312");

    public static final Order order2 = new Order(2L, 102L, LocalDate.of(2023, 8, 15), OrderStatus.CANCELED, 202L, 302L,"");



}
