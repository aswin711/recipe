package com.example.cedex.recipe.ui.category.item;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.cedex.recipe.R;
import com.example.cedex.recipe.data.s.APIClient;
import com.example.cedex.recipe.data.s.ApiHelper;
import com.example.cedex.recipe.data.s.adapters.ItemAdapter;
import com.example.cedex.recipe.data.s.models.Item;
import com.example.cedex.recipe.data.s.models.ItemFetch;
import com.example.cedex.recipe.data.s.models.ItemFetchData;
import com.example.cedex.recipe.service.APIService;
import com.example.cedex.recipe.ui.search.SearchActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ItemActivity extends AppCompatActivity{

    private RecyclerView recyclerView;
    private ItemAdapter itemAdapter;
    private List<ItemFetch> itemList;

    private TextView Title;
    private ImageButton search;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_main);



        ImageButton back = (ImageButton)findViewById(R.id.toolbar_back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        Title = (TextView)findViewById(R.id.item_title);
        Title.setText(getIntent().getExtras().get("name").toString());

        Typeface typeface = Typeface.createFromAsset(getAssets(),"fonts/Roboto-Regular.ttf");

        Title.setTypeface(typeface);

        search = (ImageButton)findViewById(R.id.item_search);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), SearchActivity.class));
            }
        });


        recyclerView = (RecyclerView)findViewById(R.id.item_recyclerview);
        itemList = new ArrayList<>();


        APIService apiService = APIClient.getClient().create(APIService.class);

        Call<ItemFetchData> call = apiService.getItems((Integer) getIntent().getExtras().get("id"));
        call.enqueue(new Callback<ItemFetchData>() {
            @Override
            public void onResponse(Call<ItemFetchData> call, Response<ItemFetchData> response) {
                ItemFetchData itemFetchData = response.body();

                if(!(itemFetchData==null)){
                    itemList = itemFetchData.getItemFetchList();
                    itemAdapter = new ItemAdapter(getApplicationContext(),itemList);

                    if (getApplicationContext().getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));

                    } else{
                        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(),1));
                    }

                    recyclerView.setItemAnimator(new DefaultItemAnimator());
                    recyclerView.setAdapter(itemAdapter);
                }

            }

            @Override
            public void onFailure(Call<ItemFetchData> call, Throwable t) {

            }
        });


    }

}
