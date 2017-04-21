package com.example.cedex.recipe.ui.shoppinglist;


import android.content.Context;
import android.media.Image;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cedex.recipe.MainActivity;
import com.example.cedex.recipe.R;
import com.example.cedex.recipe.data.s.DatabaseHelper;
import com.example.cedex.recipe.data.s.adapters.ShoppingListAdapter;
import com.example.cedex.recipe.data.s.models.Item;
import com.example.cedex.recipe.data.s.models.ShoppingList;
import com.example.cedex.recipe.utils.ToolbarChanger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShoppingListFragment extends Fragment{

    private RecyclerView shoppingListRecyclerview;
    private ShoppingListAdapter shoppingListAdapter;
    private List<ShoppingList> shoppingList;


    private DatabaseHelper databaseHelper = null;

    private ImageView announcement;
    private TextView nofav;

    public ShoppingListFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_shopping_list, container, false);
        announcement = (ImageView) view.findViewById(R.id.img_announcement);
        nofav = (TextView) view.findViewById(R.id.nofavourites);
        shoppingListRecyclerview = (RecyclerView) view.findViewById(R.id.listview_shopping);
        shoppingList = new ArrayList<>();
        databaseHelper = new DatabaseHelper(getContext());

            List<ShoppingList> ingredients = databaseHelper.fetchIngredientsAll();

           if(ingredients.isEmpty()){
               announcement.setVisibility(View.VISIBLE);
               nofav.setVisibility(View.VISIBLE);

           }else{
               announcement.setVisibility(View.INVISIBLE);
               nofav.setVisibility(View.INVISIBLE);
               shoppingList = ingredients;
               shoppingListAdapter = new ShoppingListAdapter(getContext(), shoppingList);
               shoppingListRecyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
               shoppingListRecyclerview.setAdapter(shoppingListAdapter);
           }
        return view;
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.main,menu);
        super.onCreateOptionsMenu(menu, inflater);
    }



    public void prepareData(ShoppingListAdapter shoppingListAdapter){
      this.shoppingListAdapter = shoppingListAdapter;
    }
}
