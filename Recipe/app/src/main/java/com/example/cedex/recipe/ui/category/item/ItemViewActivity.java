package com.example.cedex.recipe.ui.category.item;

import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.cedex.recipe.R;
import com.example.cedex.recipe.data.s.APIClient;
import com.example.cedex.recipe.data.s.DatabaseHelper;
import com.example.cedex.recipe.data.s.adapters.IngredientsListAdapter;
import com.example.cedex.recipe.data.s.adapters.PreparationListAdapter;
import com.example.cedex.recipe.data.s.models.Ingredients;
import com.example.cedex.recipe.data.s.models.Item;
import com.example.cedex.recipe.data.s.models.Preparations;
import com.example.cedex.recipe.data.s.models.Recipe;
import com.example.cedex.recipe.data.s.models.RecipeData;
import com.example.cedex.recipe.service.APIService;
import com.example.cedex.recipe.ui.category.item.controller.DbController;
import com.example.cedex.recipe.utils.DividerItemDecoration;
import com.example.cedex.recipe.utils.SnackBarNotify;
import com.example.cedex.recipe.utils.TypefaceAdder;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.R.attr.id;

public class ItemViewActivity extends AppCompatActivity {
    private RecyclerView recyclerview;
    private RecyclerView pre_recyclerview;
    private IngredientsListAdapter ingredientsAdapter;
    private PreparationListAdapter preparationListAdapter;
    private List<Ingredients> ingredientsList;
    private List<Preparations> preparationList;

    private TextView title;
    private TextView time;
    private TextView servings;
    private TextView ingredients;
    private ImageView itemImage;
    private TextView ingredientsName;
    private TextView preparations;

    private ToggleButton fav;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_view);

        Toolbar toolbar = (Toolbar)findViewById(R.id.itemview_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);

        ImageButton back = (ImageButton)findViewById(R.id.toolbar_back);

        fav = (ToggleButton) findViewById(R.id.item_add);

        title = (TextView) findViewById(R.id.item_title);
        time = (TextView) findViewById(R.id.item_time);
        servings = (TextView) findViewById(R.id.item_servings);
        ingredients = (TextView) findViewById(R.id.item_ingredients);
        itemImage = (ImageView) findViewById(R.id.itemview_img);
        ingredientsName = (TextView) findViewById(R.id.item_view_ingredients);
        preparations = (TextView) findViewById(R.id.item_view_preparations);

        TypefaceAdder.setFont(getApplicationContext(), title, "fonts/Roboto-Regular.ttf");
        TypefaceAdder.setFont(getApplicationContext(), time, "fonts/Roboto-Light.ttf");
        TypefaceAdder.setFont(getApplicationContext(), servings, "fonts/Roboto-Light.ttf");
        TypefaceAdder.setFont(getApplicationContext(), ingredients, "fonts/Roboto-Light.ttf");
        TypefaceAdder.setFont(getApplicationContext(), ingredientsName, "fonts/Roboto-Light.ttf");
        TypefaceAdder.setFont(getApplicationContext(), preparations, "fonts/Roboto-Light.ttf");



        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(ItemViewActivity.this, "Clicked", Toast.LENGTH_SHORT).show();
                onBackPressed();
            }
        });








        int mode = (int) getIntent().getExtras().get("mode");

        //Toast.makeText(this, mode+"", Toast.LENGTH_SHORT).show();

        if(mode==0) {
            int id = (int) getIntent().getExtras().get("id");

            final int serve = (int) getIntent().getExtras().get("serve");
            final int ingred_count = (int) getIntent().getExtras().get("ingred_count");
            final String preparation_time = (String) getIntent().getExtras().get("time");

            fav.setVisibility(View.INVISIBLE);

            APIService apiService = APIClient.getClient().create(APIService.class);
            Call<RecipeData> call = apiService.getRecipes(id);

            call.enqueue(new Callback<RecipeData>() {
                @Override
                public void onResponse(Call<RecipeData> call, Response<RecipeData> response) {


                    RecipeData recipeData = response.body();
                    if (recipeData == null) {
                        Log.d("recipe", response.body().toString());
                    } else {
                        Log.d("recipe", response.body().toString());

                        final Recipe recipe = recipeData.getRecipe();

                        if (!(recipe == null)) {


                            title.setText(recipe.getName());
                            time.setText(preparation_time + " Min");
                            servings.setText(serve + " Servings");
                            ingredients.setText(ingred_count + " Ingredients");

                            Picasso.with(getApplicationContext()).load(recipe.getImage()).into(itemImage);

                            ingredientsList = recipe.getIngredientsList();
                            preparationList = recipe.getPreparationsList();

                            recyclerview = (RecyclerView) findViewById(R.id.listview_ingredients);
                            ingredientsAdapter = new IngredientsListAdapter(getApplicationContext(), ingredientsList, recipe.getName());

                            recyclerview.setLayoutManager(new LinearLayoutManager(getApplicationContext()));


                            recyclerview.setAdapter(ingredientsAdapter);

                            recyclerview.addItemDecoration(new DividerItemDecoration(ContextCompat.getDrawable(getApplicationContext()
                                    , R.drawable.divider_list)));

                            pre_recyclerview = (RecyclerView) findViewById(R.id.listview_preparations);
                            preparationListAdapter = new PreparationListAdapter(getApplicationContext(), preparationList);

                            pre_recyclerview.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                            pre_recyclerview.setAdapter(preparationListAdapter);

                            pre_recyclerview.addItemDecoration(new DividerItemDecoration(ContextCompat.getDrawable(getApplicationContext()
                                    , R.drawable.divider_list)));

                            Button add = (Button) findViewById(R.id.add_to_shopping);

                            final DatabaseHelper databaseHelper = new DatabaseHelper(getApplicationContext());

                            add.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {

                                    Log.d("shoplist", ingredientsAdapter.list().toString());

                                    databaseHelper.addIngredients(ingredientsAdapter.list(), recipe.getId(), recipe.getName());

                                    preparationListAdapter.notifyDataSetChanged();
                                    SnackBarNotify.show(v, "Item(s) Added");

                                }
                            });

                        }


                    }

                }

                @Override
                public void onFailure(Call<RecipeData> call, Throwable t) {
                    Log.d("aswin", t.toString());
                }
            });

        }else{
            final Item item = (Item) getIntent().getExtras().get("item");

            title.setText(item.getItemName());
            time.setText(item.getItemTime() + " Min");
            servings.setText(item.getItemServings() + " Servings");
            ingredients.setText(item.getItemIngredients() + " Ingredients");

            fav.setVisibility(View.INVISIBLE);

            Picasso.with(getApplicationContext()).load(item.getItemImg()).into(itemImage);

            ingredientsList = item.getItemIngredientsList();
            preparationList = item.getItemPreparation();

            recyclerview = (RecyclerView) findViewById(R.id.listview_ingredients);
            ingredientsAdapter = new IngredientsListAdapter(getApplicationContext(), ingredientsList, item.getItemName());

            recyclerview.setLayoutManager(new LinearLayoutManager(getApplicationContext()));


            recyclerview.setAdapter(ingredientsAdapter);

            recyclerview.addItemDecoration(new DividerItemDecoration(ContextCompat.getDrawable(getApplicationContext()
                    , R.drawable.divider_list)));

            pre_recyclerview = (RecyclerView) findViewById(R.id.listview_preparations);
            preparationListAdapter = new PreparationListAdapter(getApplicationContext(), preparationList);

            pre_recyclerview.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
            pre_recyclerview.setAdapter(preparationListAdapter);

            pre_recyclerview.addItemDecoration(new DividerItemDecoration(ContextCompat.getDrawable(getApplicationContext()
                    , R.drawable.divider_list)));

            Button add = (Button) findViewById(R.id.add_to_shopping);

            final DatabaseHelper databaseHelper = new DatabaseHelper(getApplicationContext());

            add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Log.d("shoplist", ingredientsAdapter.list().toString());

                    databaseHelper.addIngredients(ingredientsAdapter.list(), item.getId(), item.getItemName());
                    preparationListAdapter.notifyDataSetChanged();

                    SnackBarNotify.show(v, "Item(s) Added");

                }
            });




        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
