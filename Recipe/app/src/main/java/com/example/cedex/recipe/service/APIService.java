package com.example.cedex.recipe.service;

import com.example.cedex.recipe.data.s.models.Category;
import com.example.cedex.recipe.data.s.models.CategoryData;
import com.example.cedex.recipe.data.s.models.ItemFetchData;
import com.example.cedex.recipe.data.s.models.RecipeData;

import org.json.JSONObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by cedex on 4/11/2017.
 */

public interface APIService {
    @GET("categories")
    Call<CategoryData> getCategories();

    @GET("recipes/category/{category_id}")
    Call<ItemFetchData> getItems(@Path("category_id") int id);

    @GET("recipes/{id}")
    Call<RecipeData> getRecipes(@Path("id") int id);
}
