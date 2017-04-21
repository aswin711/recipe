package com.example.cedex.recipe.ui.splash;

import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.cedex.recipe.MainActivity;
import com.example.cedex.recipe.R;
import com.example.cedex.recipe.ui.login.LoginActivity;
import com.example.cedex.recipe.ui.login.LoginPresenter;
import com.example.cedex.recipe.ui.register.RegisterActivity;

public class SplashScreenActivity extends AppCompatActivity {

    private TextView splashlets;
    private TextView splashcook;
    private static int SPLASH_TIME_OUT = 3000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        Typeface typeface = Typeface.createFromAsset(getAssets(),"fonts/OpenSans-CondLight.ttf");
        Typeface typeface1 = Typeface.createFromAsset(getAssets(),"fonts/OpenSans-CondBold.ttf");

        splashlets = (TextView)findViewById(R.id.splashName);
        splashcook = (TextView)findViewById(R.id.splashName2);
        splashcook.setTypeface(typeface);
        splashlets.setTypeface(typeface1);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().setStatusBarColor(getResources().getColor(R.color.baseColorTransparent));
        }


        new Handler().postDelayed(new Runnable() {
           @Override
            public void run() {

               startActivity(new Intent(SplashScreenActivity.this,MainActivity.class));
               finish();
            }
        }, SPLASH_TIME_OUT);
    }
}
