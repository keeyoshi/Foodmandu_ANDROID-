package com.keeyoshi.foodmandu.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.keeyoshi.foodmandu.R;
import com.keeyoshi.foodmandu.model.Food;

import java.util.List;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.FoodViewHolder> {
    Context mContext;
    List<Food> foodList;

    public FoodAdapter(Context mContext, List<Food> foodList){
        this.mContext=mContext;
        this.foodList=foodList;
    }

    @NonNull
    @Override
    public FoodAdapter.FoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_food,parent,false);
        return new FoodViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodAdapter.FoodViewHolder holder, int position) {

        final Food food=foodList.get(position);
        holder.txtView.setText(food.getName());
        holder.imgView.setImageResource(food.getImageId());
    }

    @Override
    public int getItemCount() {
        return foodList.size();
    }

    public class FoodViewHolder extends RecyclerView.ViewHolder{
        ImageView imgView;
        TextView txtView;
        public FoodViewHolder(@NonNull View itemView) {
            super(itemView);
            imgView=itemView.findViewById(R.id.imgView);
            txtView=itemView.findViewById(R.id.txtView);
        }
    }
}
