package com.example.cedex.recipe.data.s.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cedex.recipe.R;
import com.example.cedex.recipe.data.s.models.Category;
import com.example.cedex.recipe.ui.category.item.ItemActivity;
import com.example.cedex.recipe.utils.TypefaceAdder;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by cedex on 4/4/2017.
 */

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder>{
    private Context context;
    private List<Category> categoryList;


    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView category_img;
        public TextView category_name,category_count;
        public ViewHolder(View itemView) {
            super(itemView);
            category_img = (ImageView)itemView.findViewById(R.id.category_img);
            category_name = (TextView)itemView.findViewById(R.id.category_name);
            category_count = (TextView)itemView.findViewById(R.id.category_count);

            TypefaceAdder.setFont(context,category_name,"fonts/Roboto-Light.ttf");
            TypefaceAdder.setFont(context,category_count,"fonts/Roboto-Regular.ttf");

        }
    }

    public CategoryAdapter(Context context, List<Category> categoryList) {
        this.context = context;
        this.categoryList = categoryList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.category_list,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        final Category category = categoryList.get(position);
        holder.category_name.setText(category.getName());
        //Toast.makeText(context, holder.category_name.getText(), Toast.LENGTH_SHORT).show();
        //holder.category_count.setText(category.getRecipes_count()+" Recipes");
        Picasso.with(context).load(R.drawable.bg_cat1).fit().into(holder.category_img);
        Picasso.with(context).load(category.getImage()).fit().into(holder.category_img);

        holder.category_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, ItemActivity.class);
                i.putExtra("name",category.getName());
                i.putExtra("id",category.getId());
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }
}
