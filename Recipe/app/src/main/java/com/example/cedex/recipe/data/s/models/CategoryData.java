package com.example.cedex.recipe.data.s.models;

import android.util.Log;

import com.google.gson.annotations.SerializedName;

import java.util.Iterator;
import java.util.List;

/**
 * Created by cedex on 4/11/2017.
 */

public class CategoryData {
    @SerializedName("data")
    private List<Category> categories;


    public List<Category> getCategories(){
        return categories;
    }

    public void View(){
        Iterator<Category> l = categories.iterator();
        while (l.hasNext()){
            Log.d("category",l.next().getName()+"\n");
        }
    }
}
