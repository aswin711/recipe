package com.example.cedex.recipe.utils;

import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.view.View;

/**
 * Created by cedex on 4/17/2017.
 */

public class SnackBarNotify {
    private View view;

    public static void show(View view,String msg){
        Snackbar snackbar = Snackbar.make(view,msg,Snackbar.LENGTH_SHORT);
        snackbar.show();
    }

    public static void warning(View view,String msg){
        Snackbar snackbar = Snackbar.make(view,msg,Snackbar.LENGTH_SHORT).setActionTextColor(Color.RED);
        snackbar.show();
    }
}
