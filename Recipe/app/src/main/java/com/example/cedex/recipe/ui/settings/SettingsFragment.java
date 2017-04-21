package com.example.cedex.recipe.ui.settings;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;

import com.example.cedex.recipe.R;
import com.example.cedex.recipe.data.s.DatabaseHelper;
import com.example.cedex.recipe.utils.SnackBarNotify;

/**
 * A simple {@link Fragment} subclass.
 */
public class SettingsFragment extends Fragment {

    private CheckBox fav;
    private CheckBox ingredients;


    public SettingsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_settings, container, false);

         fav = (CheckBox) view.findViewById(R.id.settings_fav_clear);
         ingredients = (CheckBox) view.findViewById(R.id.settings_ingredients_clear);

        Button clear = (Button) view.findViewById(R.id.settings_clear);

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseHelper databaseHelper = new DatabaseHelper(getContext());

                if (ingredients.isChecked()&&fav.isChecked()){
                    databaseHelper.dropTable(2);
                    ingredients.setChecked(false);
                    fav.setChecked(false);
                    SnackBarNotify.show(v,"Datas cleared");
                }else if(ingredients.isChecked()){
                    databaseHelper.dropTable(1);
                    ingredients.setChecked(false);
                    SnackBarNotify.show(v,"Ingredients List cleared");
                }else if(fav.isChecked()){
                    databaseHelper.dropTable(0);
                    fav.setChecked(false);
                    SnackBarNotify.show(v,"Favourites cleared");
                }else {
                    SnackBarNotify.show(v,"Select any one");
                }
            }
        });

        return view;
    }

}
