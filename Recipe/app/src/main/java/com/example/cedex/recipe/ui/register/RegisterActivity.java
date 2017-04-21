package com.example.cedex.recipe.ui.register;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.cedex.recipe.R;
import com.example.cedex.recipe.service.Validator;
import com.example.cedex.recipe.ui.login.LoginActivity;

public class RegisterActivity extends AppCompatActivity {

    private EditText userName,password,confirmPassword;
    private Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        userName = (EditText)findViewById(R.id.register_username);
        password = (EditText)findViewById(R.id.register_password);
        confirmPassword = (EditText)findViewById(R.id.register_confirm_password);
        register = (Button)findViewById(R.id.register_button);


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Validator validator = new Validator(userName.getText().toString()
                ,password.getText().toString());

                String snack = "Registered.";

                if(!validator.isUserName()){
                    snack = "UserName is not Correct.";
                }else if(!validator.isPassword()){
                    snack = "Password length is less than 8.";
                }else if(!password.getText().toString().equals(
                        confirmPassword.getText().toString()
                )){
                    snack = "Passwords doesn't match.";
                }else{

                    Snackbar snackbar = Snackbar.make(v,snack,Snackbar.LENGTH_SHORT);
                    snackbar.show();


                    RegisterPresenter regPresenter = new RegisterPresenter(getApplicationContext(),userName.getText().toString()
                    ,password.getText().toString());

                    regPresenter.regSharedPreferences();

                    startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                    finish();
                }


            }
        });
    }
}
