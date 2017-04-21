package com.example.cedex.recipe.ui.saved_recipe;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cedex.recipe.R;
import com.example.cedex.recipe.data.s.DatabaseHelper;
import com.example.cedex.recipe.data.s.adapters.ItemAdapter;
import com.example.cedex.recipe.data.s.models.Item;
import com.example.cedex.recipe.data.s.models.ItemFetch;
import com.example.cedex.recipe.ui.category.item.controller.DbController;

import java.util.Iterator;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class SavedRecipeFragment extends Fragment {

    private ImageView announcement;
    private TextView nofav;

    public SavedRecipeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_saved_recipe, container, false);

        //TextView msg = (TextView) view.findViewById(R.id.saved_recipe_text);
        DatabaseHelper databaseHelper = new DatabaseHelper(getContext());
        DbController dbController = new DbController(getContext());
        List<Item> itemList = databaseHelper.fetchAllItem();

        announcement = (ImageView) view.findViewById(R.id.saved_announcement);
        nofav = (TextView) view.findViewById(R.id.saved_nofav);



        //Log.d("saved",itemList.toString());
        //msg.setText(itemList.get(0).getItemIngredientsList().get(0).toString());
        List<ItemFetch> itemFetchList = dbController.itemFetchToList(itemList);

        if(itemFetchList.isEmpty()){
            announcement.setVisibility(View.VISIBLE);
            nofav.setVisibility(View.VISIBLE);
        }

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview_saved_recipe);
        ItemAdapter itemAdapter = new ItemAdapter(getContext(),itemFetchList,1);

        Iterator<ItemFetch> iterator = itemFetchList.iterator();
        String content = "";
        while (iterator.hasNext()){
            ItemFetch itemFetch = iterator.next();
            content += itemFetch.getPreparation_time()+"\n"+itemFetch.getServings()+"\n"
                    +itemFetch.getIngredients_count()+"\n"+itemFetch.getName()+"\n"+"\n";
        }
        Log.d("saved",content);

        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),1));
        recyclerView.setAdapter(itemAdapter);

        return view;
    }

}
