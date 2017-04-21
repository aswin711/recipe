package com.example.cedex.recipe.service;

import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;

/**
 * Created by cedex on 4/3/2017.
 */

public class Validator {
    private String userName;
    private String password;

    public Validator() {
    }

    public Validator(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public boolean isPassword(){
        if(password.length()>=8){
            return true;
        }
        return false;
    }

   public boolean isUserName(){
       if(userName.matches("^\\s*[\\da-zA-Z][\\da-zA-Z\\s]*$")){
           return true;
       }
       return false;
   }
}
