package com.example.cedex.recipe.data.s.models;

import java.util.List;

/**
 * Created by cedex on 4/12/2017.
 */

public class ShoppingList {
    private Integer item_id;
    private String name;
    private List<String> ingredientsAdded;

    public ShoppingList() {
    }

    public ShoppingList(Integer item_id, List<String> ingredientsAdded) {
        this.item_id = item_id;
        this.ingredientsAdded = ingredientsAdded;
    }

    public ShoppingList(Integer item_id, String name, List<String> ingredientsAdded) {
        this.item_id = item_id;
        this.name = name;
        this.ingredientsAdded = ingredientsAdded;
    }

    public Integer getItem_id() {
        return item_id;
    }

    public void setItem_id(Integer item_id) {
        this.item_id = item_id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getIngredientsAdded() {
        return ingredientsAdded;
    }

    public void setIngredientsAdded(List<String> ingredientsAdded) {
        this.ingredientsAdded = ingredientsAdded;
    }

}
