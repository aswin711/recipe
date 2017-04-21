package com.example.cedex.recipe.data.s;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.ParcelUuid;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.cedex.recipe.data.s.models.Ingredients;
import com.example.cedex.recipe.data.s.models.Item;
import com.example.cedex.recipe.data.s.models.Preparations;
import com.example.cedex.recipe.data.s.models.ShoppingList;
import com.example.cedex.recipe.utils.TypefaceAdder;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by cedex on 4/4/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "LetsCook";
    private static final String TABLE_SHOPPING = "ShoppingList";
    private static final String TABLE_ITEM = "ItemTable";

    //Common attributes

    private static final String KEY_ID = "id";
    private static final String KEY_DATE = "date";
    private static final String KEY_ITEM = "item_id";


    //Table attributes for Shopping List


    private static final String KEY_INGREDIENT = "ingredient";

    //Table attributes for Item Table

    private static final String KEY_CATEGORY = "category_id";
    private static final String KEY_NAME = "item_name";
    private static final String KEY_TIME = "preparation_time";
    private static final String KEY_SERVE = "servings";
    private static final String KEY_INGREDIENTS = "ingredients_count";
    private static final String KEY_ADD = "item_added";
    private static final String KEY_FAV = "item_favourites";
    private static final String KEY_IMG_URL = "item_img";
    private static final String KEY_ING_LIST = "item_ingredients_list";
    private static final String KEY_PREP_LIST = "item_preparation_list";


    private Context context;



    public DatabaseHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
        this.context = context;

    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_ITEM_table = "CREATE TABLE " + TABLE_ITEM + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"+KEY_ITEM+" INTEGER,"+KEY_NAME + " TEXT,"
                + KEY_TIME + " INTEGER," +KEY_SERVE+" INTEGER,"+KEY_INGREDIENTS+" INTEGER,"+KEY_ADD+" INTEGER,"+
                KEY_FAV+" INTEGER,"+KEY_IMG_URL+" TEXT,"+KEY_ING_LIST+" TEXT,"+KEY_PREP_LIST+" TEXT,"+KEY_DATE+" TEXT"+")";
        db.execSQL(CREATE_ITEM_table);

        String CREATE_SHOPPING_LIST_TABLE = "CREATE TABLE "+TABLE_SHOPPING + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"+ KEY_ITEM +" INTEGER,"+ KEY_NAME+" TEXT,"+KEY_INGREDIENT+" TEXT"
                +")";
        db.execSQL(CREATE_SHOPPING_LIST_TABLE);

        Toast.makeText(context, "Tables Created", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ITEM);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SHOPPING);

        // Create tables again
        onCreate(db);




    }

    public  void addIngredients(List<String> ingredients,int id,String name){
        SQLiteDatabase db = this.getWritableDatabase();

        Iterator iterator = ingredients.iterator();

        List<String> existingIngredients = this.fetchIngredients(id);
        //Log.d("addingred",existingIngredients.toString());

        if(existingIngredients.isEmpty()){
            while (iterator.hasNext()){
                ContentValues values = new ContentValues();
                values.put(KEY_INGREDIENT,iterator.next().toString());
                values.put(KEY_NAME,name);
                values.put(KEY_ITEM,id);
                db.insert(TABLE_SHOPPING,null,values);
            }
        }else{
            //update list

            List<String> uniqueList = this.distinctIngredientsSort(ingredients,existingIngredients);
            Iterator<String> iterator1 = uniqueList.iterator();
            while (iterator1.hasNext()){
                ContentValues values = new ContentValues();
                values.put(KEY_INGREDIENT,iterator1.next().toString());
                values.put(KEY_NAME,name);
                values.put(KEY_ITEM,id);
                db.insert(TABLE_SHOPPING,null,values);
            }
        }



        db.close();




    }

    public List<String> fetchIngredients(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT "+KEY_INGREDIENT+" FROM "+TABLE_SHOPPING+" WHERE "+
                KEY_ITEM+" = "+id;

        Cursor cursor = db.rawQuery(selectQuery,null);
        List<String> ingredientsList = new ArrayList<>();
        while(cursor.moveToNext()){
            ingredientsList.add(cursor.getString(0));
        }

        return ingredientsList;
    }

    public List<ShoppingList> fetchIngredientsAll(){
        List<String> ingredientsList = new ArrayList<>();
        List<Integer> distinctIds = this.fetchDistinctId();
        List<ShoppingList> shoppingList = new ArrayList<>();
        for(Integer i:distinctIds){

            List<String> ingredients = this.fetchIngredients(i);
            ShoppingList shopping = new ShoppingList();
            shopping.setItem_id(i);
            shopping.setName(getItemName(i));
            shopping.setIngredientsAdded(ingredients);
            Log.d("fetchall",i+" "+ingredients+"\n");
            shoppingList.add(shopping);
        }

        return shoppingList;
    }

    public List<Integer> fetchDistinctId(){
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT DISTINCT "+KEY_ITEM+" FROM "+TABLE_SHOPPING;



        Cursor cursor = db.rawQuery(selectQuery,null);
        List<Integer> distinctIds = new ArrayList<>();
        while(cursor.moveToNext()){
            distinctIds.add(cursor.getInt(0));

            //Toast.makeText(context, cursor.getColumnCount()+" ", Toast.LENGTH_SHORT).show();
        }

        return distinctIds;
    }

    public void deleteIngredients(ShoppingList shoppingList){
        SQLiteDatabase db = this.getWritableDatabase();
        for(String ingredient:shoppingList.getIngredientsAdded()){
            db.delete(TABLE_SHOPPING, KEY_ITEM + " = ?"+" AND "+KEY_INGREDIENT+" = ?",
                    new String[] { String.valueOf(shoppingList.getItem_id()),ingredient });


        }
        db.close();
    }

    public String getItemName(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT "+KEY_NAME+" FROM "+TABLE_SHOPPING+" WHERE "+KEY_ITEM+" = "+id,null);
        String name = "Hallo";
        while (cursor.moveToNext()){
            name = cursor.getString(0);
        }
        return name;

    }

    public void dropTable(int id){

       /* if(id==0){
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_ITEM);

            String CREATE_ITEM_table = "CREATE TABLE " + TABLE_ITEM + "("
                    + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"+KEY_ITEM+" INTEGER,"+KEY_NAME + " TEXT,"
                    + KEY_TIME + " INTEGER," +KEY_SERVE+" INTEGER,"+KEY_INGREDIENTS+" INTEGER,"+KEY_ADD+" INTEGER,"+
                    KEY_FAV+" INTEGER,"+KEY_IMG_URL+" TEXT,"+KEY_ING_LIST+" TEXT,"+KEY_PREP_LIST+" TEXT,"+KEY_DATE+" TEXT"+")";
            db.execSQL(CREATE_ITEM_table);
        }else if(id==1){
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_SHOPPING);

            String CREATE_SHOPPING_LIST_TABLE = "CREATE TABLE "+TABLE_SHOPPING + "("
                    + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"+ KEY_ITEM +" INTEGER,"+ KEY_NAME+" TEXT,"+KEY_INGREDIENT+" TEXT"
                    +")";
            db.execSQL(CREATE_SHOPPING_LIST_TABLE);
        }else if(id==2){
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_ITEM);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_SHOPPING);

            String CREATE_ITEM_table = "CREATE TABLE " + TABLE_ITEM + "("
                    + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"+KEY_ITEM+" INTEGER,"+KEY_NAME + " TEXT,"
                    + KEY_TIME + " INTEGER," +KEY_SERVE+" INTEGER,"+KEY_INGREDIENTS+" INTEGER,"+KEY_ADD+" INTEGER,"+
                    KEY_FAV+" INTEGER,"+KEY_IMG_URL+" TEXT,"+KEY_ING_LIST+" TEXT,"+KEY_PREP_LIST+" TEXT,"+KEY_DATE+" TEXT"+")";
            db.execSQL(CREATE_ITEM_table);

            String CREATE_SHOPPING_LIST_TABLE = "CREATE TABLE "+TABLE_SHOPPING + "("
                    + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"+ KEY_ITEM +" INTEGER,"+ KEY_NAME+" TEXT,"+KEY_INGREDIENT+" TEXT"
                    +")";
            db.execSQL(CREATE_SHOPPING_LIST_TABLE);
        }



        // Create tables again
        db.close();*/

       SQLiteDatabase database = this.getWritableDatabase();
        onUpgrade(database,0,1);
    }

    public void insertItem(Item item,int add,int fav){
        SQLiteDatabase db = this.getWritableDatabase();
        Item existingItem = this.fetchItem(item.getId());
        Log.d("fetchitem",existingItem.toString());
        if(existingItem!=null){
            ContentValues values = new ContentValues();
            values.put(KEY_ITEM,item.getId());
            values.put(KEY_NAME,item.getItemName());
            values.put(KEY_TIME,item.getItemTime());
            values.put(KEY_SERVE,item.getItemServings());
            values.put(KEY_INGREDIENTS,item.getItemIngredients());
            values.put(KEY_ADD,add);
            values.put(KEY_FAV,fav);
            values.put(KEY_IMG_URL,item.getItemImg());
            Gson gson = new Gson();
            values.put(KEY_ING_LIST,gson.toJson(item.getItemIngredientsList()));
            values.put(KEY_PREP_LIST,gson.toJson(item.getItemPreparation()));
            String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());
            values.put(KEY_DATE,currentDateTimeString);
            db.insert(TABLE_ITEM,null,values);
        }

        db.close();

    }

    public List<Item> fetchAllItem(){
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM "+TABLE_ITEM;

        Cursor cursor = db.rawQuery(selectQuery,null);
        List<Item> itemList = new ArrayList<>();
        while(cursor.moveToNext()){
            Item item = new Item();
            item.setId(cursor.getInt(1));
            item.setItemName(cursor.getString(2));
            item.setItemTime(cursor.getInt(3));
            item.setItemServings(cursor.getInt(4));
            item.setItemIngredients(cursor.getInt(5));
            item.setItemAdd(cursor.getInt(6));
            item.setItemFav(cursor.getInt(7));
            item.setItemImg(cursor.getString(8));
            String json = cursor.getString(9);
            List<Ingredients> ingredientsList = new ArrayList<>();
            Type type = new TypeToken<List<Ingredients>>(){}.getType();
            Gson gson = new Gson();
            ingredientsList = gson.fromJson(json,type);
            item.setItemIngredientsList(ingredientsList);
            List<Preparations> preparationsList = new ArrayList<>();
            Type type1 = new TypeToken<List<Preparations>>(){}.getType();
            json = cursor.getString(10);
            preparationsList = gson.fromJson(json,type1);
            item.setItemPreparation(preparationsList);

            itemList.add(item);

        }

        return itemList;

    }


    public List<Integer> fetchAllItemById(){
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM "+TABLE_ITEM;

        Cursor cursor = db.rawQuery(selectQuery,null);
        List<Integer> itemList = new ArrayList<>();
        while(cursor.moveToNext()){
            Item item = new Item();

            itemList.add(cursor.getInt(1));

        }

        return itemList;

    }

    public Item fetchItem(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM "+TABLE_ITEM+" WHERE "+
                KEY_ITEM+" = "+id;

        Cursor cursor = db.rawQuery(selectQuery,null);
        Item item = new Item();
        while(cursor.moveToNext()){
            item.setId(cursor.getInt(1));
            item.setItemName(cursor.getString(2));
            item.setItemTime(cursor.getInt(3));
            item.setItemServings(cursor.getInt(4));
            item.setItemIngredients(cursor.getInt(5));
            item.setItemAdd(cursor.getInt(6));
            item.setItemFav(cursor.getInt(7));
            item.setItemImg(cursor.getString(8));
            String json = cursor.getString(9);
            List<Ingredients> ingredientsList = new ArrayList<>();
            Type type = new TypeToken<List<Ingredients>>(){}.getType();
            Gson gson = new Gson();
            ingredientsList = gson.fromJson(json,type);
            item.setItemIngredientsList(ingredientsList);
            List<Preparations> preparationsList = new ArrayList<>();
            Type type1 = new TypeToken<List<Preparations>>(){}.getType();
            json = cursor.getString(10);
            preparationsList = gson.fromJson(json,type1);
            item.setItemPreparation(preparationsList);

        }

        return item;
    }

    public void deleteItem(int id){
        SQLiteDatabase db = this.getWritableDatabase();

            db.delete(TABLE_ITEM, KEY_ITEM + " = ?",
                    new String[] {String.valueOf(id)});



        db.close();
    }

    public List<String> distinctIngredientsSort(List<String> entryList,List<String> exitList){
        List<String> uniqueList = new ArrayList<>();

        for(String entry:entryList){
            if(!exitList.contains(entry)){
                uniqueList.add(entry);
            }
        }

        return uniqueList;
    }


}
