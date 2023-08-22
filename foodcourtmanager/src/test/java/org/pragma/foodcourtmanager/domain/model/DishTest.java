package org.pragma.foodcourtmanager.domain.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DishTest{

    @Test
    public void testGettersAndSetters() {
        Long id = 1L;
        String name = "Plato 1";
        Long categoryId = 101L;
        String description = "Descripcion del plato 1";
        Double price = 10.99;
        Long restaurantId = 201L;
        String imageUrl = "http://example.com/image.jpg";
        Boolean isActive = true;

        Dish dish = new Dish(id, name, categoryId, description, price, restaurantId, imageUrl, isActive);

        assertEquals(id, dish.getId());
        assertEquals(name, dish.getName());
        assertEquals(categoryId, dish.getCategoryId());
        assertEquals(description, dish.getDescription());
        assertEquals(price, dish.getPrice());
        assertEquals(restaurantId, dish.getRestaurantId());
        assertEquals(imageUrl, dish.getImageUrl());
        assertEquals(isActive, dish.getActive());

        Long newId = 2L;
        String newName = "Nuevo Plato 1";
        Long newCategoryId = 102L;
        String newDescription = "Nueva descripción del plato 1";
        Double newPrice = 15.99;
        Long newRestaurantId = 202L;
        String newImageUrl = "http://example.com/new-image.jpg";
        Boolean newIsActive = false;

        dish.setId(newId);
        dish.setName(newName);
        dish.setCategoryId(newCategoryId);
        dish.setDescription(newDescription);
        dish.setPrice(newPrice);
        dish.setRestaurantId(newRestaurantId);
        dish.setImageUrl(newImageUrl);
        dish.setActive(newIsActive);

        assertEquals(newId, dish.getId());
        assertEquals(newName, dish.getName());
        assertEquals(newCategoryId, dish.getCategoryId());
        assertEquals(newDescription, dish.getDescription());
        assertEquals(newPrice, dish.getPrice());
        assertEquals(newRestaurantId, dish.getRestaurantId());
        assertEquals(newImageUrl, dish.getImageUrl());
        assertEquals(newIsActive, dish.getActive());
    }

}