package com.example.cedex.recipe.ui.shoppinglist;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by cedex on 4/10/2017.
 */

public class ListCollector {
    private Map listItems;
    private List<String> lists;
    private int flag;
    private String view = "";

    public ListCollector() {
        this.listItems = new HashMap<>();
        this.lists = new ArrayList<>();
        this.flag = 0;
        this.view = "";
    }

    public void insert(String item,String list){
        if(listItems.isEmpty()){
            listItems.put(item,list);
        }else if(listItems.get(item)==null){
            listItems.put(item,list);
        }else{
            List<String> tempList = new ArrayList<>();
             if(listItems.get(item) instanceof String){
                 String temp = (String) listItems.get(item);
                 tempList.add(temp);
                 tempList.add(list);

             }
             else{

                 tempList = (List<String>) listItems.get(item);
                 tempList.add(list);
             }


            listItems.remove(item);
            listItems.put(item,tempList);
        }
    }

    public void delete(String item,String list){



        if(listItems.get(item) instanceof String){
            String temp = (String) listItems.get(item);
            if(temp.equals(list)){
                listItems.remove(item);
            }
        }else if(listItems.get(item) instanceof List){
            List<String> tempLists = (List<String>) listItems.get(item);
            for(String s:tempLists){

                if(s.equals(list)){
                    tempLists.remove(s);
                }
            }
            listItems.remove(item);
            if(!tempLists.isEmpty()){
                listItems.put(item,lists);
            }
        }


    }

    public String view(){

        Iterator<Map.Entry<String,List<String>>> entries = listItems.entrySet().iterator();
        while(entries.hasNext()){
            Map.Entry<String,List<String>> entry = entries.next();
            view += entry.getKey()+"\n";
            /*List<String> tempList = entry.getValue();
            for(String temp:tempList){
                view += temp+" ";
            }*/
        }
        return view;
    }

    public Map fetch(){
        return this.listItems;
    }
}
