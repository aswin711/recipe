package com.example.cedex.recipe.data.s.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.cedex.recipe.R;
import com.example.cedex.recipe.data.s.models.Preparations;
import com.example.cedex.recipe.utils.TypefaceAdder;

import java.util.List;

/**
 * Created by cedex on 4/7/2017.
 */

public class PreparationListAdapter extends RecyclerView.Adapter<PreparationListAdapter.ViewHolder> {

    private Context context;
    private List<Preparations> preparationList;

    public PreparationListAdapter(Context context, List<Preparations> preparationList) {
        this.context = context;
        this.preparationList = preparationList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView index;
        public TextView step;
        public ViewHolder(View itemView) {
            super(itemView);
            index = (TextView) itemView.findViewById(R.id.pre_index);
            step = (TextView)itemView.findViewById(R.id.pre_step);

            TypefaceAdder.setFont(context,index,"fonts/RobotoCondensed-Regular.ttf");
            TypefaceAdder.setFont(context,step,"fonts/Roboto-Light.ttf");
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.list_preparation,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.index.setText(position+1+"");
        holder.step.setText(preparationList.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return preparationList.size();
    }
}
