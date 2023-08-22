package org.pragma.foodcourtmanager.domain.model;

public class Dish {

    private Long id;
    private String name;
    private Long categoryId;
    private String description;
    private Double price;
    private String imageUrl;
    private Boolean isActive;
    private Long restaurantId;


    public Dish (Long id, String name, Long categoryId, String description, Double price, Long restaurantId, String imagenUrl, Boolean isActive){
        this.id = id;
        this.name = name;
        this.categoryId = categoryId;
        this.description = description;
        this.price = price;
        this.restaurantId = restaurantId;
        this.imageUrl = imagenUrl;
        this.isActive = isActive;
    }

    public Long getId (){
        return id;
    }

    public void setId (Long id){
        this.id = id;
    }

    public String getName (){
        return name;
    }

    public void setName (String name){
        this.name = name;
    }

    public Long getCategoryId (){
        return categoryId;
    }

    public void setCategoryId (Long categoryId){
        this.categoryId = categoryId;
    }

    public String getDescription (){
        return description;
    }

    public void setDescription (String description){
        this.description = description;
    }

    public Double getPrice (){
        return price;
    }

    public void setPrice (Double price){
        this.price = price;
    }

    public Long getRestaurantId (){
        return restaurantId;
    }

    public void setRestaurantId (Long restaurantId){
        this.restaurantId = restaurantId;
    }

    public String getImageUrl (){
        return imageUrl;
    }

    public void setImageUrl (String imageUrl){
        this.imageUrl = imageUrl;
    }

    public Boolean getActive (){
        return isActive;
    }

    public void setActive (Boolean active){
        isActive = active;
    }

}
