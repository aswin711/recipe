package com.example.cedex.recipe.data.s.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by cedex on 4/12/2017.
 */

public class RecipeData {
    @SerializedName("data")
    private Recipe recipe;

    public Recipe getRecipe(){
        return recipe;
    }
}
