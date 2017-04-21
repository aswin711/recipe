package com.example.cedex.recipe.data.s.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by cedex on 4/12/2017.
 */

public class Ingredients implements Parcelable{
    @SerializedName("name")
    public String name;
    @SerializedName("amount")
    public String amount;
    @SerializedName("note")
    public String note;

    public Ingredients() {
    }

    public Ingredients(String name, String amount, String note) {
        this.name = name;
        this.amount = amount;
        this.note = note;
    }

    protected Ingredients(Parcel in) {
        name = in.readString();
        amount = in.readString();
        note = in.readString();
    }

    public static final Creator<Ingredients> CREATOR = new Creator<Ingredients>() {
        @Override
        public Ingredients createFromParcel(Parcel in) {
            return new Ingredients(in);
        }

        @Override
        public Ingredients[] newArray(int size) {
            return new Ingredients[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(amount);
        dest.writeString(note);
    }
}
