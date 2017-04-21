package com.example.cedex.recipe.ui.login;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.cedex.recipe.MainActivity;
import com.example.cedex.recipe.R;
import com.example.cedex.recipe.service.Validator;

public class LoginActivity extends AppCompatActivity {

    private EditText userName,password;
    private Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Typeface typeface = Typeface.createFromAsset(getAssets(),"fonts/Helvetica LT 67 Medium Condensed.ttf");

        userName = (EditText)findViewById(R.id.login_username);
        password = (EditText)findViewById(R.id.login_password);
        login = (Button)findViewById(R.id.login_button);

        userName.setTypeface(typeface);
        password.setTypeface(typeface);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Validator loginValidator = new Validator(userName.getText().toString()
                        ,password.getText().toString());
                String snack = "Logged in";
                if(!loginValidator.isUserName()){
                    snack="Invalid Username";
                }else if(!loginValidator.isPassword()){
                    snack="Password is too short.";
                }else{
                        LoginPresenter loginPresenter = new LoginPresenter(userName.getText().toString()
                                ,password.getText().toString(),getApplicationContext());
                    if(loginPresenter.setSharedLoggedIn()){
                        startActivity(new Intent(LoginActivity.this,MainActivity.class));
                        finish();
                    }
                    else{
                        snack = "Invalid Credentials";
                    }


                }

                Snackbar snackbar = Snackbar.make(v,snack,Snackbar.LENGTH_SHORT);
                snackbar.show();



            }
        });
    }
}
