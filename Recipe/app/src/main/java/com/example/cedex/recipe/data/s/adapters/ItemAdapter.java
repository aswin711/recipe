package com.example.cedex.recipe.data.s.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.cedex.recipe.R;
import com.example.cedex.recipe.data.s.models.Item;
import com.example.cedex.recipe.data.s.models.ItemFetch;
import com.example.cedex.recipe.ui.category.item.ItemViewActivity;
import com.example.cedex.recipe.ui.category.item.controller.DbController;
import com.example.cedex.recipe.utils.DialogBoxNotify;
import com.example.cedex.recipe.utils.SnackBarNotify;
import com.example.cedex.recipe.utils.TypefaceAdder;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by cedex on 4/5/2017.
 */

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {

    private Context context;
    private List<ItemFetch> itemList;
    private DbController dbController;
    private int MODE = 0;

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView itemImage;
        public TextView itemName;
        public TextView itemTime;
        public TextView itemServings;
        public TextView itemIngredients;
        public ToggleButton itemAdd;

        public ViewHolder(View itemView) {
            super(itemView);
            itemImage = (ImageView)itemView.findViewById(R.id.item_img);
            itemName = (TextView)itemView.findViewById(R.id.item_name);
            itemTime = (TextView)itemView.findViewById(R.id.item_time);
            itemServings = (TextView)itemView.findViewById(R.id.item_servings);
            itemIngredients = (TextView)itemView.findViewById(R.id.item_ingredients);

            itemAdd = (ToggleButton) itemView.findViewById(R.id.item_add);

            TypefaceAdder.setFont(context,itemName,"fonts/Roboto-Regular.ttf");
            TypefaceAdder.setFont(context,itemTime,"fonts/Roboto-Light.ttf");
            TypefaceAdder.setFont(context,itemServings,"fonts/Roboto-Light.ttf");
            TypefaceAdder.setFont(context,itemIngredients,"fonts/Roboto-Light.ttf");


        }
    }

    public ItemAdapter(Context context, List<ItemFetch> itemList) {
        this.context = context;
        this.itemList = itemList;
        this.dbController = new DbController(context);
        //Toast.makeText(context, "Entered", Toast.LENGTH_SHORT).show();
    }

    public ItemAdapter(Context context, List<ItemFetch> itemList, int MODE) {
        this.context = context;
        this.itemList = itemList;
        this.dbController = new DbController(context);
        this.MODE = MODE;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list,parent,false);
        return new ItemAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        final ItemFetch item = itemList.get(position);
        holder.itemName.setText(item.getName());
        holder.itemTime.setText(item.getPreparation_time()+" Min");
        holder.itemServings.setText(item.getServings()+" Servings");
        holder.itemIngredients.setText(item.getIngredients_count()+" Ingredients");

        Picasso.with(context).load(item.getImage()).into(holder.itemImage);

        if(MODE==1){
            holder.itemAdd.setChecked(true);
            holder.itemAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    holder.itemAdd.setChecked(true);
                        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
                        alertDialog.setTitle("Remove from Favourites");
                        alertDialog.setMessage("Do you want to remove recipe?");
                        alertDialog.setIcon(R.drawable.ic_delete_black);
                        alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                                dbController.removeRecipe(item);
                                List<ItemFetch> itemFetchList = dbController.fetchAllRecipes();
                                Notify(itemFetchList);

                            }
                        });
                        alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                                holder.itemAdd.setChecked(true);
                            }
                        });

                    alertDialog.show();
                }
            });


            holder.itemImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(context, ItemViewActivity.class);

                    Item item1 = dbController.fetchItem(item.getId());

                    i.putExtra("mode",1);
                    i.putExtra("item",item1);
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(i);
                }
            });
        }else{

            List<Integer> existingFav = dbController.fetchAllRecipesById();

            if(existingFav.contains(item.getId())){
                holder.itemAdd.setChecked(true);
            }

            holder.itemAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(holder.itemAdd.isChecked()){
                        SnackBarNotify.show(v,"Recipe saved");
                        dbController.addRecipe(item);

                    }else{
                        SnackBarNotify.show(v,"Recipe removed from wishlist");
                        dbController.removeRecipe(item);
                    }
                }
            });



            holder.itemImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(context, ItemViewActivity.class);

                    i.putExtra("id",item.getId());
                    i.putExtra("serve",item.getServings());
                    i.putExtra("ingred_count",item.getIngredients_count());
                    i.putExtra("time",item.getPreparation_time());
                    i.putExtra("mode",0);
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(i);
                }
            });
        }




    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public void Notify(List<ItemFetch> itemList){
        this.itemList = itemList;
        notifyDataSetChanged();
    }
}
