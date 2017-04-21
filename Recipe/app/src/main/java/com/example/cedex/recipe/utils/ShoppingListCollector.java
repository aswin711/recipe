package com.example.cedex.recipe.utils;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by cedex on 4/10/2017.
 */

public class ShoppingListCollector {
    private Map listMap;
    private String view;
    private Context context;

    public ShoppingListCollector(Context context) {
        this.listMap = new HashMap<String,List<String>>();
        this.view = "";
        this.context = context;
    }

    public void insert(String item,String list){
        if(listMap.isEmpty()){
            listMap.put(item,list);
        }else{
            ArrayList<String> tempList = new ArrayList<>();
            if(listMap.get(item) instanceof String){
                //Toast.makeText(context, "String", Toast.LENGTH_SHORT).show();
                tempList.add((String) listMap.get(item));
                tempList.add(list);
                listMap.remove(item);
                listMap.put(item,tempList);
            }else{
                tempList = (ArrayList<String>) listMap.get(item);
                //Toast.makeText(context, "String array", Toast.LENGTH_SHORT).show();
                tempList.add(list);
                listMap.remove(item);
                listMap.put(item,tempList);

            }
        }

    }
    public void delete(String item,String list){

            //Toast.makeText(context, "deleting....", Toast.LENGTH_SHORT).show();
            if(listMap.get(item) instanceof String){
                listMap.remove(item);
            }else{
                List<String> tempList = (List<String>) listMap.get(item);
                Iterator<String> temp = tempList.iterator();
                Log.d("list",tempList.toString());

               while (temp.hasNext()){

                    String s = temp.next();
                    if(s.equals(list)){
                        temp.remove();
                    }
                }
                listMap.remove(item);
                if(!tempList.isEmpty()){
                    listMap.put(item,tempList);
                }
            }



    }

    public Map getListMap(){
        return listMap;

    }




    public void view(){
        for (Object entry:listMap.entrySet()){
            Log.d("list",entry.toString());
        }
    }

}
