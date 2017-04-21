package com.example.cedex.recipe.data.s.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.cedex.recipe.MainActivity;
import com.example.cedex.recipe.R;
import com.example.cedex.recipe.data.s.DatabaseHelper;
import com.example.cedex.recipe.data.s.models.Item;
import com.example.cedex.recipe.data.s.models.ItemFetch;
import com.example.cedex.recipe.data.s.models.ShoppingList;
import com.example.cedex.recipe.utils.DividerItemDecoration;
import com.example.cedex.recipe.utils.SnackBarNotify;
import com.example.cedex.recipe.utils.TypefaceAdder;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by cedex on 4/7/2017.
 */

public class ShoppingListAdapter extends RecyclerView.Adapter<ShoppingListAdapter.ViewHolder> {

    private Context context;
    private List<ShoppingList> shoppingList;
    private DatabaseHelper databaseHelper;



    public ShoppingListAdapter(Context context, List<ShoppingList> shoppingList) {
        this.context = context;
        this.shoppingList = shoppingList;
        this.databaseHelper = new DatabaseHelper(context);


    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView shoppingItemName;
        public RecyclerView shoppingItemList;
        public ShoppingItemsAdapter shoppingItemsAdapter;
        public ImageButton deleteList;
        public ViewHolder(View itemView) {
            super(itemView);

            shoppingItemName = (TextView)itemView.findViewById(R.id.listview_shopping_name);
            shoppingItemList = (RecyclerView)itemView.findViewById(R.id.listview_shopping_items);
            deleteList = (ImageButton) itemView.findViewById(R.id.listview_shopping_delete);

            TypefaceAdder.setFont(context,shoppingItemName,"fonts/Roboto-Regular.ttf");







        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context)
                .inflate(R.layout.list_shopping,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        final ShoppingList list = shoppingList.get(position);

        holder.shoppingItemName.setText(list.getName()+" ");



        holder.shoppingItemsAdapter = new ShoppingItemsAdapter(context,list.getIngredientsAdded(),list.getItem_id()+"");
        holder.shoppingItemList.setLayoutManager(new LinearLayoutManager(context));
        holder.shoppingItemList.addItemDecoration(new DividerItemDecoration(ContextCompat.getDrawable(context,R.drawable.divider_list)));
        holder.shoppingItemList.setAdapter(holder.shoppingItemsAdapter);

        holder.deleteList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(holder.shoppingItemsAdapter.view().isEmpty()){
                    SnackBarNotify.show(v,"Select an item");
                }else{
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
                    alertDialog.setTitle("Remove items from ShoppingList");
                    alertDialog.setMessage("Do you want to remove selected items?");
                    alertDialog.setIcon(R.drawable.ic_delete_black);
                    alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {

                            Map<String,List<String>> deleteList = holder.shoppingItemsAdapter.view();
                            Log.d("list",deleteList.toString());

                            Iterator<Map.Entry<String,List<String>>> iterator = deleteList.entrySet().iterator();
                            List<String> stringList = new ArrayList<String>();
                            while (iterator.hasNext()){
                                Map.Entry entry = iterator.next();
                                if (entry.getValue() instanceof List)

                                {
                                    stringList = (List<String>) entry.getValue();
                                }else{
                                    stringList.add(entry.getValue().toString());
                                }
                            }

                            ShoppingList shoppingList = new ShoppingList();
                            shoppingList.setName(list.getName());
                            shoppingList.setIngredientsAdded(stringList);
                            shoppingList.setItem_id(list.getItem_id());
                            databaseHelper.deleteIngredients(shoppingList);


                            List<ShoppingList> ingredients = databaseHelper.fetchIngredientsAll();
                            Notify(ingredients);


                        }
                    });
                    alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();

                        }
                    });

                    alertDialog.show();

                }


            }
        });



    }

    @Override
    public int getItemCount() {
        return shoppingList.size();
    }






    public void Notify(List<ShoppingList> list){
         this.shoppingList = list;
        notifyDataSetChanged();
    }
}
