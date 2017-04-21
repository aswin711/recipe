package com.example.cedex.recipe;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cedex.recipe.R;
import com.example.cedex.recipe.data.s.DatabaseHelper;
import com.example.cedex.recipe.data.s.adapters.ShoppingListAdapter;
import com.example.cedex.recipe.data.s.models.ShoppingList;
import com.example.cedex.recipe.service.NetworkChecker;
import com.example.cedex.recipe.ui.category.CategoryFragment;
import com.example.cedex.recipe.ui.login.LoginActivity;
import com.example.cedex.recipe.ui.login.LoginPresenter;
import com.example.cedex.recipe.ui.saved_recipe.SavedRecipeFragment;
import com.example.cedex.recipe.ui.search.SearchActivity;
import com.example.cedex.recipe.ui.settings.SettingsFragment;
import com.example.cedex.recipe.ui.shoppinglist.ShoppingListFragment;
import com.example.cedex.recipe.utils.SnackBarNotify;
import com.example.cedex.recipe.utils.ToolbarChanger;

import java.util.List;

import static android.R.id.toggle;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,NetworkChecker{

    private LoginPresenter loginPresenter;
    private TextView title;
    private ImageButton search;
    private ImageButton settings;
    private Button btn1;
    private ProgressBar progressBar;
    private ImageButton refresh;
    private TextView errorText;

    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);


        databaseHelper = new DatabaseHelper(getApplicationContext());

        Typeface typeface = Typeface.createFromAsset(getAssets(),"fonts/Roboto-Regular.ttf");
        title = (TextView)findViewById(R.id.item_title);
        title.setTypeface(typeface);

        ImageButton nav = (ImageButton)findViewById(R.id.toolbar_nav);
        settings = (ImageButton) findViewById(R.id.item_menu);

        progressBar = (ProgressBar) findViewById(R.id.category_progress);

        refresh = (ImageButton) findViewById(R.id.refresh);
        errorText = (TextView) findViewById(R.id.errorText);



        search = (ImageButton)findViewById(R.id.item_search);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SearchActivity.class));
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);


        toggle.setHomeAsUpIndicator(R.drawable.ic_hamburger);

        toggle.setDrawerIndicatorEnabled(false);

        drawer.setDrawerListener(toggle);
        toggle.syncState();

        toggle.setToolbarNavigationClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                if (drawer.isDrawerOpen(GravityCompat.START)) {
                    drawer.closeDrawer(GravityCompat.START);
                } else {
                    drawer.openDrawer(GravityCompat.START);
                }
            }
        });

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

       nav.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                if (drawer.isDrawerOpen(GravityCompat.START)) {
                    drawer.closeDrawer(GravityCompat.START);
                } else {
                    drawer.openDrawer(GravityCompat.START);
                }
            }
        });




        CategoryFragment categoryFragment = new CategoryFragment();
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.content_main,categoryFragment).commit();
        search.setVisibility(View.VISIBLE);

        //settings.setVisibility(View.INVISIBLE);
        title.setText("Home");


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_category) {
            // Handle the camera action
            CategoryFragment categoryFragment = new CategoryFragment();
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.content_main,categoryFragment).commit();
            search.setVisibility(View.VISIBLE);
            //settings.setVisibility(View.INVISIBLE);
            title.setText("Home");

        } else if (id == R.id.nav_shopping_list) {
            ShoppingListFragment shoppingListFragment = new ShoppingListFragment();
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.content_main,shoppingListFragment).commit();
            search.setVisibility(View.INVISIBLE);
            //settings.setVisibility(View.VISIBLE);
            title.setText("Shopping List");

        }else if(id == R.id.nav_saved_recipes){
            SavedRecipeFragment shoppingListFragment = new SavedRecipeFragment();
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.content_main,shoppingListFragment).commit();
            search.setVisibility(View.INVISIBLE);
            //settings.setVisibility(View.VISIBLE);
            title.setText("Favourites");

        } else if (id == R.id.nav_promotions) {

        } else if (id == R.id.about) {

        } else if (id == R.id.nav_settings) {
            SettingsFragment shoppingListFragment = new SettingsFragment();
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.content_main,shoppingListFragment).commit();
            search.setVisibility(View.INVISIBLE);
            //settings.setVisibility(View.VISIBLE);
            title.setText("Contact Author");

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public final boolean isInternetOn(View view) {

        // get Connectivity Manager object to check connection

        ConnectivityManager connec =
                (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);

        // Check for network connections
        if ( connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.CONNECTED ||
                connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.CONNECTING ||
                connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.CONNECTING ||
                connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.CONNECTED ) {

            // if connected with internet

            //Toast.makeText(getContext(), " Connected ", Toast.LENGTH_LONG).show();
            progressBar.setVisibility(View.INVISIBLE);
            return true;

        } else if (
                connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.DISCONNECTED ||
                        connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.DISCONNECTED  ) {

            //Toast.makeText(getContext(), " Not Connected ", Toast.LENGTH_LONG).show();

            progressBar.setVisibility(View.INVISIBLE);
            refresh.setVisibility(View.VISIBLE);
            errorText.setVisibility(View.VISIBLE);

            SnackBarNotify.warning(view,"Please Check Your Internet Settings");

            return false;
        }
        return false;
    }

      @Override
    public void CheckInternet() {
        final View view = this.findViewById(R.id.content_main);
        progressBar.setVisibility(View.VISIBLE);
        refresh.setVisibility(View.INVISIBLE);
        errorText.setVisibility(View.INVISIBLE);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                isInternetOn(view);
                //Toast.makeText(MainActivity.this, "Entered", Toast.LENGTH_SHORT).show();

                refresh.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MainActivity.this, "Clicked", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        },3000);
    }
}
