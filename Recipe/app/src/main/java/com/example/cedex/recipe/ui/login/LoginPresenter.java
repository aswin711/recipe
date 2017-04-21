package com.example.cedex.recipe.ui.login;

import android.content.Context;

import com.example.cedex.recipe.data.s.PreferenceHelper;

/**
 * Created by cedex on 4/3/2017.
 */

public class LoginPresenter {

    private String userName;
    private String password;
    private Context context;

    private PreferenceHelper prefHelper;

    public LoginPresenter(Context context){
        this.prefHelper =  new PreferenceHelper(context.getSharedPreferences("Reg",context.MODE_PRIVATE));
    }

    public LoginPresenter(String userName, String password, Context context) {
        this.userName = userName;
        this.password = password;
        this.context = context;

        this.prefHelper =  new PreferenceHelper(this.context.getSharedPreferences("Reg",this.context.MODE_PRIVATE));
    }

    public boolean setSharedLoggedIn(){


        if(userName.equals(prefHelper.get("username",""))&&password.equals(prefHelper.get("password",""))){
           prefHelper.put("loggedIn","1");
            return true;
        }
        return false;
    }

    public boolean isLoggedIn(){
        if(prefHelper.get("loggedIn","").equals("1")){
            return true;
        }

        return false;
    }

    public void LogOut(String username){
        //username fetch informations

        prefHelper.deleteSavedData("loggedIn");
    }
}
