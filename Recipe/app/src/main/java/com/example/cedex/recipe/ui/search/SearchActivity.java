package com.example.cedex.recipe.ui.search;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SearchView;
import android.widget.TextView;

import com.example.cedex.recipe.R;

public class SearchActivity extends AppCompatActivity {

    private TextView title;
    private SearchView searchView;
    private ImageButton close;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        Toolbar toolbar = (Toolbar) findViewById(R.id.search_toolbar);
        setSupportActionBar(toolbar);

        Typeface typeface = Typeface.createFromAsset(getAssets(),"fonts/Helvetica LT 67 Medium Condensed.ttf");
        title = (TextView)findViewById(R.id.search_title);
        title.setTypeface(typeface);

        searchView = (SearchView)findViewById(R.id.search_searchview);
        searchView.setQueryHint("What would you like to cook?");

        close = (ImageButton)findViewById(R.id.search_close);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        ImageButton back = (ImageButton)findViewById(R.id.search_back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}
