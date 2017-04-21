package com.example.cedex.recipe.data.s.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by cedex on 4/12/2017.
 */

public class ItemFetchData {
    @SerializedName("data")
    private List<ItemFetch> itemFetchList;

    public List<ItemFetch> getItemFetchList(){
        return this.itemFetchList;
    }
}
