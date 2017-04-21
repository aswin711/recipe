package com.example.cedex.recipe.utils;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by cedex on 4/10/2017.
 */

public class ShoppingListAdder {
    private List<String> stringList;


    public ShoppingListAdder(Context context) {
        this.stringList = new ArrayList<>();
    }

    public void insert(String list){
        stringList.add(list);
    }

    public void delete(String list){
        Iterator<String> iterator = stringList.iterator();
        while (iterator.hasNext()){
            String s = iterator.next();
            if(s.equals(list)){
                iterator.remove();
            }
        }

    }

    public List<String> list(){
        return stringList;
    }


}
