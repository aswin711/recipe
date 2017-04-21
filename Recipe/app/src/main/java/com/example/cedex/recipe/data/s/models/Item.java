package com.example.cedex.recipe.data.s.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by cedex on 4/5/2017.
 */

public class Item implements Parcelable {

    private int id;
    private String itemName;
    private int itemTime;
    private int itemServings;
    private int itemIngredients;
    private int itemAdd;
    private int itemFav;
    private String itemImg;
    private List<Ingredients> itemIngredientsList;
    private List<Preparations> itemPreparation;

    public Item() {
    }

    public Item(String itemName, int itemTime, int itemServings, int itemIngredients, int itemAdd, int itemFav, String itemImg) {
        this.itemName = itemName;
        this.itemTime = itemTime;
        this.itemServings = itemServings;
        this.itemIngredients = itemIngredients;
        this.itemAdd = itemAdd;
        this.itemFav = itemFav;
        this.itemImg = itemImg;
    }

    public Item(int id,String itemName, int itemTime, int itemServings, int itemIngredients, int itemAdd, int itemFav, String itemImg, List<Ingredients> itemIngredientsList, List<Preparations> itemPreparation) {
        this.id =id;
        this.itemName = itemName;
        this.itemTime = itemTime;
        this.itemServings = itemServings;
        this.itemIngredients = itemIngredients;
        this.itemAdd = itemAdd;
        this.itemFav = itemFav;
        this.itemImg = itemImg;
        this.itemIngredientsList = itemIngredientsList;
        this.itemPreparation = itemPreparation;
    }

    protected Item(Parcel in) {
        id = in.readInt();
        itemName = in.readString();
        itemTime = in.readInt();
        itemServings = in.readInt();
        itemIngredients = in.readInt();
        itemAdd = in.readInt();
        itemFav = in.readInt();
        itemImg = in.readString();
        itemIngredientsList = in.createTypedArrayList(Ingredients.CREATOR);
        itemPreparation = in.createTypedArrayList(Preparations.CREATOR);
    }

    public static final Creator<Item> CREATOR = new Creator<Item>() {
        @Override
        public Item createFromParcel(Parcel in) {
            return new Item(in);
        }

        @Override
        public Item[] newArray(int size) {
            return new Item[size];
        }
    };

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getItemTime() {
        return itemTime;
    }

    public void setItemTime(int itemTime) {
        this.itemTime = itemTime;
    }

    public int getItemServings() {
        return itemServings;
    }

    public void setItemServings(int itemServings) {
        this.itemServings = itemServings;
    }

    public int getItemIngredients() {
        return itemIngredients;
    }

    public void setItemIngredients(int itemIngredients) {
        this.itemIngredients = itemIngredients;
    }

    public int getItemAdd() {
        return itemAdd;
    }

    public void setItemAdd(int itemAdd) {
        this.itemAdd = itemAdd;
    }

    public int getItemFav() {
        return itemFav;
    }

    public void setItemFav(int itemFav) {
        this.itemFav = itemFav;
    }

    public String getItemImg() {
        return itemImg;
    }

    public void setItemImg(String itemImg) {
        this.itemImg = itemImg;
    }

    public List<Ingredients> getItemIngredientsList() {
        return itemIngredientsList;
    }

    public void setItemIngredientsList(List<Ingredients> itemIngredientsList) {
        this.itemIngredientsList = itemIngredientsList;
    }

    public List<Preparations> getItemPreparation() {
        return itemPreparation;
    }

    public void setItemPreparation(List<Preparations> itemPreparation) {
        this.itemPreparation = itemPreparation;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(itemName);
        dest.writeInt(itemTime);
        dest.writeInt(itemServings);
        dest.writeInt(itemIngredients);
        dest.writeInt(itemAdd);
        dest.writeInt(itemFav);
        dest.writeString(itemImg);
        dest.writeTypedList(itemIngredientsList);
        dest.writeTypedList(itemPreparation);
    }

    /* @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(itemName);
        dest.writeInt(itemTime);
        dest.writeInt(itemServings);
        dest.writeInt(itemIngredients);
        dest.writeInt(itemAdd);
        dest.writeInt(itemFav);
        dest.writeInt(itemImg);
        dest.writeStringList(itemIngredientsList);
        dest.writeStringList(itemPreparation);
    }

    public static final Parcelable.Creator<Item> CREATOR = new Creator<Item>() {
        @Override
        public Item createFromParcel(Parcel source) {
            Item item = new Item();
            item.itemName = source.readString();
            item.itemTime=source.readInt();
            item.itemServings = source.readInt();
            item.itemIngredients=source.readInt();
            item.itemAdd=source.readInt();
            item.itemFav=source.readInt();
            item.itemImg=source.readInt();
            item.itemIngredientsList=source.createStringArrayList();
            item.itemPreparation = source.createStringArrayList();
            return item;
        }

        @Override
        public Item[] newArray(int size) {
            return new Item[size];
        }
    };*/
}
