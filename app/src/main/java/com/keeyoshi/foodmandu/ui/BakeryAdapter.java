package com.keeyoshi.foodmandu.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.keeyoshi.foodmandu.R;
import com.keeyoshi.foodmandu.model.Bakery;

import java.util.List;

public class BakeryAdapter extends RecyclerView.Adapter<BakeryAdapter.BakeryViewHolder> {
        Context mContext;
        List<Bakery> cakeList;

public BakeryAdapter(Context mContext, List<Bakery> cakeList){
        this.mContext=mContext;
        this.cakeList=cakeList;
        }

    @NonNull
    @Override
    public BakeryAdapter.BakeryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cake,parent,false);
        return new BakeryAdapter.BakeryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BakeryAdapter.BakeryViewHolder holder, int position) {

    final Bakery bakery = cakeList.get(position);

    holder.imgCake.setImageResource(bakery.getImageId());

    }

    @Override
    public int getItemCount() {
        return cakeList.size() ;
    }


    public class BakeryViewHolder extends RecyclerView.ViewHolder{
    ImageView imgCake;

    public BakeryViewHolder(@NonNull View itemView) {
        super(itemView);
        imgCake=itemView.findViewById(R.id.imgCake);

    }
}
}

