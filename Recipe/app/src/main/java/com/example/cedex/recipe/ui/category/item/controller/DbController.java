package com.example.cedex.recipe.ui.category.item.controller;

import android.content.Context;
import android.util.Log;

import com.example.cedex.recipe.data.s.APIClient;
import com.example.cedex.recipe.data.s.DatabaseHelper;
import com.example.cedex.recipe.data.s.models.Ingredients;
import com.example.cedex.recipe.data.s.models.Item;
import com.example.cedex.recipe.data.s.models.ItemFetch;
import com.example.cedex.recipe.data.s.models.Preparations;
import com.example.cedex.recipe.data.s.models.Recipe;
import com.example.cedex.recipe.data.s.models.RecipeData;
import com.example.cedex.recipe.service.APIService;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by cedex on 4/17/2017.
 */

public class DbController {

    private DatabaseHelper databaseHelper;



    public DbController(Context context) {
        this.databaseHelper = new DatabaseHelper(context);
    }

    public  void addFav(ItemFetch itemFetch){
        Item item = itemFetchToItem(itemFetch);
       databaseHelper.insertItem(item,0,1);
    }
    public  void addRecipe(final ItemFetch itemFetch){


        APIService apiService = APIClient.getClient().create(APIService.class);
        Call<RecipeData> call = apiService.getRecipes(itemFetch.getId());
        call.enqueue(new Callback<RecipeData>() {
            @Override
            public void onResponse(Call<RecipeData> call, Response<RecipeData> response) {
                Item item = itemFetchToItem(itemFetch);
                RecipeData recipeData = response.body();
                if(recipeData!=null){
                    Recipe recipe = recipeData.getRecipe();
                    if(recipe!=null){
                        item.setItemName(recipe.getName());
                        item.setItemIngredientsList(recipe.getIngredientsList());
                        item.setItemPreparation(recipe.getPreparationsList());
                        databaseHelper.insertItem(item,0,1);
                        //Log.d("addrecipe",recipe.getIngredientsList()+"\n"+recipe.getPreparationsList()+"\n");
                    }
                }
            }

            @Override
            public void onFailure(Call<RecipeData> call, Throwable t) {

            }
        });


    }

    public void removeRecipe(ItemFetch item){
        databaseHelper.deleteItem(item.getId());
    }

    public List<ItemFetch> fetchAllRecipes(){
        List<Item> itemList = databaseHelper.fetchAllItem();
        List<ItemFetch> itemFetchList = this.itemFetchToList(itemList);

        return itemFetchList;
    }

    public Item fetchItem(int id){
        return databaseHelper.fetchItem(id);
    }

    public List<Integer> fetchAllRecipesById(){
        return databaseHelper.fetchAllItemById();
    }

    public void dropTable(){
        databaseHelper.dropTable(0);
    }

    public Item itemFetchToItem(ItemFetch itemFetch){
        final Item item = new Item();
        item.setId(itemFetch.getId());
        item.setItemName(itemFetch.getName());
        item.setItemTime(Integer.parseInt(itemFetch.getPreparation_time()));
        item.setItemServings(itemFetch.getServings());
        item.setItemIngredients(itemFetch.getIngredients_count());
        item.setItemImg(itemFetch.getImage());
        //Log.d("addrecipe",item.getItemIngredientsList()+"\n"+item.getItemPreparation()+"\n");
        return item;
    }

    public ItemFetch itemToItemFetch(Item item){
        ItemFetch itemFetch = new ItemFetch();
        itemFetch.setId(item.getId());
        itemFetch.setName(item.getItemName());
        itemFetch.setImage(item.getItemImg());
        itemFetch.setPreparation_time(item.getItemTime()+"");
        itemFetch.setServings(item.getItemServings());
        itemFetch.setIngredients_count(item.getItemIngredients());

        return itemFetch;
    }

    public List<ItemFetch> itemFetchToList(List<Item> itemList){
        List<ItemFetch> itemFetchList = new ArrayList<>();
        Iterator<Item> iterator = itemList.iterator();
        while (iterator.hasNext()){
            Item item = (Item) iterator.next();
            ItemFetch itemFetch = itemToItemFetch(item);
            itemFetchList.add(itemFetch);
        }
        return itemFetchList;
    }
}
