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
import android.widget.Toast;

import com.example.cedex.recipe.R;
import com.example.cedex.recipe.utils.ShoppingListCollector;
import com.example.cedex.recipe.utils.TypefaceAdder;

import java.util.List;
import java.util.Map;

/**
 * Created by cedex on 4/7/2017.
 */

public class ShoppingItemsAdapter extends RecyclerView.Adapter<ShoppingItemsAdapter.ViewHolder>{

    private Context context;
    private List<String> shoppingList;
    private String itemName;
    private int status;
    private ShoppingListCollector listCollector;




    public ShoppingItemsAdapter(Context context, List<String> shoppingList,String itemName) {
        this.context = context;
        this.shoppingList = shoppingList;
        this.itemName = itemName;
        this.listCollector = new ShoppingListCollector(context);

    }



    public class ViewHolder extends RecyclerView.ViewHolder {

        public CheckBox itemCheck;
        public TextView itemName;

        public ViewHolder(View itemView) {
            super(itemView);

            itemCheck = (CheckBox) itemView.findViewById(R.id.list_shopping_items_check);
            itemName = (TextView) itemView.findViewById(R.id.list_shopping_items_name);

            TypefaceAdder.setFont(context,itemName,"fonts/Roboto-Light.ttf");

        }
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.list_shopping_items,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        holder.itemName.setText(shoppingList.get(position));



        holder.itemCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });

        holder.itemName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(holder.itemCheck.isChecked()){

                    holder.itemCheck.setChecked(false);

                }else{


                    holder.itemCheck.setChecked(true);
                }


            }
        });

        holder.itemCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                //Log.d("list",position+"\n");
                if(isChecked){
                    listCollector.insert(itemName,  holder.itemName.getText().toString());
                    //Toast.makeText(context,holder.itemName.getText()+" "+itemName, Toast.LENGTH_SHORT).show();
                }else{
                    listCollector.delete(itemName,  holder.itemName.getText().toString());
                }
                //Log.d("item",listCollector.getListMap().toString());

            }
        });
    }

    @Override
    public int getItemCount() {
        return shoppingList.size();
    }

   /* public Map view(){
        return this.listCollector.getListMap();
    }*/

    public Map view(){
      return listCollector.getListMap();
    }
}
