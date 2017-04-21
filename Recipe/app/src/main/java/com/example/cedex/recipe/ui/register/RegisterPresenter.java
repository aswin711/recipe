package com.example.cedex.recipe.ui.register;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.cedex.recipe.data.s.PreferenceHelper;

/**
 * Created by cedex on 4/3/2017.
 */

public class RegisterPresenter {

    private String userName;
    private String password;
    private Context context;

    public RegisterPresenter(Context context,String userName, String password) {
        this.context = context;
        this.userName = userName;
        this.password = password;
    }

    public void regSharedPreferences(){
        SharedPreferences sharedPreferences = context.getSharedPreferences("Reg", 0);
        PreferenceHelper preferenceHelper = new PreferenceHelper(sharedPreferences);

        preferenceHelper.put("username",userName);
        preferenceHelper.put("password",password);
    }
}
