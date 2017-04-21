package com.example.cedex.recipe.data.s.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by cedex on 4/12/2017.
 */

public class ItemFetch {
    @SerializedName("id")
    private int id;
    @SerializedName("name")
    private String name;
    @SerializedName("description")
    private String description;
    @SerializedName("image")
    private String image;
    @SerializedName("ingredients_count")
    private int ingredients_count;
    @SerializedName("preparation_time")
    private String preparation_time;
    @SerializedName("servings")
    private int servings;

    public ItemFetch() {
    }

    public ItemFetch(int id, String name, String description, String image, int ingredients_count, String preparation_time, int servings) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.image = image;
        this.ingredients_count = ingredients_count;
        this.preparation_time = preparation_time;
        this.servings = servings;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getIngredients_count() {
        return ingredients_count;
    }

    public void setIngredients_count(int ingredients_count) {
        this.ingredients_count = ingredients_count;
    }

    public String getPreparation_time() {
        return preparation_time;
    }

    public void setPreparation_time(String preparation_time) {
        this.preparation_time = preparation_time;
    }

    public int getServings() {
        return servings;
    }

    public void setServings(int servings) {
        this.servings = servings;
    }
}
