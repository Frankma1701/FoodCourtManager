package org.pragma.foodcourtmanager.util;

import org.pragma.foodcourtmanager.domain.model.Order;
import org.pragma.foodcourtmanager.domain.model.OrderDish;
import org.pragma.foodcourtmanager.domain.model.OrderStatus;

import java.time.LocalDate;

public class FactoryOrderDish{

    public static final OrderDish mockObject = new OrderDish(1L,20L,2L);
}
