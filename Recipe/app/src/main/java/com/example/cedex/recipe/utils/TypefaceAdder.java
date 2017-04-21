package com.example.cedex.recipe.utils;

import android.content.Context;
import android.graphics.Typeface;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by cedex on 4/7/2017.
 */

public class TypefaceAdder {



    public static void setFont(Context context,TextView textView, String font){
        Typeface typeface = Typeface.createFromAsset(context.getAssets(),font);
        textView.setTypeface(typeface);
    }

    public static void setFont(Context context, EditText textView, String font){
        Typeface typeface = Typeface.createFromAsset(context.getAssets(),font);
        textView.setTypeface(typeface);
    }

}
