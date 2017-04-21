package com.example.cedex.recipe.ui.category;


import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ProgressBar;

import com.example.cedex.recipe.R;
import com.example.cedex.recipe.data.s.ApiHelper;
import com.example.cedex.recipe.data.s.adapters.CategoryAdapter;
import com.example.cedex.recipe.data.s.models.Category;
import com.example.cedex.recipe.data.s.models.CategoryData;
import com.example.cedex.recipe.service.NetworkChecker;
import com.example.cedex.recipe.utils.ToolbarChanger;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class CategoryFragment extends Fragment implements ApiHelper.ExtractValue {

    private RecyclerView recyclerView;
    private CategoryAdapter categoryAdapter;
    private List<Category> categoryList;
    private ImageButton refresh;
    private ProgressBar progressBar;

    private NetworkChecker networkChecker = null;


    private ToolbarChanger toolbarChanger = null;


    public CategoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        final View view =  inflater.inflate(R.layout.fragment_category, container, false);

        networkChecker.CheckInternet();
        progressBar = (ProgressBar) view.findViewById(R.id.category_progress);
        recyclerView = (RecyclerView)view.findViewById(R.id.recyclerview);
        categoryList = new ArrayList<>();
        /*refresh = (ImageButton) view.findViewById(R.id.refresh);

        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Clicked", Toast.LENGTH_SHORT).show();
            }
        });*/



        //refresh.setVisibility(View.VISIBLE);



        //progressBar.setVisibility(View.VISIBLE);

        ApiHelper apiHelper = new ApiHelper();

        apiHelper.getCategories(new ApiHelper.ExtractValue() {
            @Override
            public void getCategoryDetails(CategoryData cat) {
                categoryList = cat.getCategories();
                categoryAdapter = new CategoryAdapter(getContext(),categoryList);
                recyclerView.setAdapter(categoryAdapter);



                recyclerView.setItemAnimator(new DefaultItemAnimator());


            }
        });


       //progressBar.setVisibility(View.INVISIBLE);

        if (getActivity().getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));

        } else{
            recyclerView.setLayoutManager(new GridLayoutManager(getContext(),1));
        }


/*
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
               if(!isInternetOn(view)) {
                   refresh.setVisibility(View.VISIBLE);
               }
            }
        },3000);*/




        return view;


    }





    @Override
    public void getCategoryDetails(CategoryData cat) {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        networkChecker = (NetworkChecker) context;
    }
}
