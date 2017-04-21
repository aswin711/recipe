package com.example.cedex.recipe.data.s.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by cedex on 4/12/2017.
 */

public class Preparations implements Parcelable {
    @SerializedName("title")
    public String title;
    @SerializedName("time")
    public String time;

    public Preparations() {
    }

    public Preparations(String title, String time) {
        this.title = title;
        this.time = time;
    }

    protected Preparations(Parcel in) {
        title = in.readString();
        time = in.readString();
    }

    public static final Creator<Preparations> CREATOR = new Creator<Preparations>() {
        @Override
        public Preparations createFromParcel(Parcel in) {
            return new Preparations(in);
        }

        @Override
        public Preparations[] newArray(int size) {
            return new Preparations[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(time);
    }


}
