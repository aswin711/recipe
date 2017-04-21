package com.example.cedex.recipe.data.s.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by cedex on 4/12/2017.
 */

public class Recipe {

    @SerializedName("id")
    private int id;
    @SerializedName("name")
    private String name;
    @SerializedName("description")
    private String description;
    @SerializedName("image")
    private String image;
    @SerializedName("category_id")
    private int category_id;
    @SerializedName("category_name")
    private String category_name;
    @SerializedName("ingredients")
    private List<Ingredients> ingredientsList;
    @SerializedName("preparations")
    private List<Preparations> preparationsList;


    public Recipe() {
    }

    public Recipe(int id, String name, String description, String image, int category_id, String category_name, List<Ingredients> ingredientsList, List<Preparations> preparationsList) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.image = image;
        this.category_id = category_id;
        this.category_name = category_name;
        this.ingredientsList = ingredientsList;
        this.preparationsList = preparationsList;
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

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public List<Ingredients> getIngredientsList() {
        return ingredientsList;
    }

    public void setIngredientsList(List<Ingredients> ingredientsList) {
        this.ingredientsList = ingredientsList;
    }

    public List<Preparations> getPreparationsList() {
        return preparationsList;
    }

    public void setPreparationsList(List<Preparations> preparationsList) {
        this.preparationsList = preparationsList;
    }
}
