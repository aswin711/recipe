package com.example.cedex.recipe.data.s.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.example.cedex.recipe.R;
import com.example.cedex.recipe.data.s.models.Ingredients;
import com.example.cedex.recipe.ui.category.item.ItemViewActivity;
import com.example.cedex.recipe.utils.ShoppingListAdder;
import com.example.cedex.recipe.utils.TypefaceAdder;

import java.util.List;

/**
 * Created by cedex on 4/5/2017.
 */

public class IngredientsListAdapter extends RecyclerView.Adapter<IngredientsListAdapter.ViewHolder>{

    private Context context;
    private List<Ingredients> ingredientsList;
    private ShoppingListAdder shoppingListAdder;
    private String itemName;


    public IngredientsListAdapter(Context context, List<Ingredients> ingredientsList, String itemName) {
        this.context = context;
        this.ingredientsList = ingredientsList;
        this.shoppingListAdder = new ShoppingListAdder(context);

        this.itemName = itemName;
    }



    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView ingredient;
        public CheckBox check;

        public ViewHolder(View itemView) {
            super(itemView);

            ingredient = (TextView)itemView.findViewById(R.id.ingredients_item_ingredient);
            check = (CheckBox)itemView.findViewById(R.id.ingredients_item_check);

            TypefaceAdder.setFont(context,ingredient,"fonts/Roboto-Light.ttf");
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_ingredients,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        String ingredient = "( "+ingredientsList.get(position).getAmount()+" ) "
                +ingredientsList.get(position).getName();
        holder.ingredient.setText(ingredient);

        holder.ingredient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(holder.check.isChecked()){
                    holder.check.setChecked(false);
                }else{
                    holder.check.setChecked(true);
                }
            }
        });

        holder.check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    shoppingListAdder.insert(holder.ingredient.getText().toString());
                }else{
                    shoppingListAdder.delete(holder.ingredient.getText().toString());
                }

                //shoppingListAdder.view();


            }

        });



    }

    @Override
    public int getItemCount() {
        return ingredientsList.size();
    }





    public List<String> list(){
        return this.shoppingListAdder.list();
    }



}
