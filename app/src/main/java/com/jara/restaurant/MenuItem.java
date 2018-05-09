package com.jara.restaurant;

import java.io.Serializable;

public class MenuItem implements Serializable {
    /** MenuItem class containing all properties and methods for MenuItem objects **/

    /* Properties */
    private String category;
    private String description;
    private String price;
    private String imageUrl;
    private String name;

    /* Constructor */
    public MenuItem(String category, String description, String price, String imageUrl, String name) {
        this.category = category;
        this.description = description;
        this.price = price;
        this.imageUrl = imageUrl;
        this.name = name;
    }

    /* Getters */
    public String getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }

    public String getPrice() {
        return price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getName() {
        return name;
    }

    /* Setters */
    public void setCategory(String category) {
        this.category = category;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setName(String name) {
        this.name = name;
    }
}
