package com.keeyoshi.foodmandu.ui;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.keeyoshi.foodmandu.R;
import com.keeyoshi.foodmandu.StrictModeClass;
import com.keeyoshi.foodmandu.model.Alcohol;
import com.keeyoshi.foodmandu.url.Url;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class AlcoholAdapter extends RecyclerView.Adapter<AlcoholAdapter.AlcoholViewHolder>{

    Context mContext;
    List<Alcohol> alcoholList;
    public AlcoholAdapter(Context mContext,List<Alcohol> alcoholList)
    {
        this.mContext=mContext;
        this.alcoholList=alcoholList;
    }

    @NonNull
    @Override
    public AlcoholViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.alcohol_view,parent,false);
        return new AlcoholAdapter.AlcoholViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AlcoholViewHolder holder, int position) {


        final Alcohol alcohol= alcoholList.get(position);
        String imgPath= Url.imagePath+alcohol.getImage();
        holder.tvAname.setText(alcohol.getName());
        holder.tvAmount.setText(alcohol.getQuantity());
        holder.tvPrice.setText(alcohol.getPrice());

        StrictModeClass.StrictMode();
        try{
            URL url=new URL(imgPath);
            holder.card2.setImageBitmap(BitmapFactory.decodeStream((InputStream)url.getContent()));
        }

        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return alcoholList.size();
    }

    public class AlcoholViewHolder extends RecyclerView.ViewHolder{

        ImageView card2;
        TextView tvAname,tvAmount, tvPrice;
        public AlcoholViewHolder(@NonNull View itemView) {
            super(itemView);

            card2=itemView.findViewById(R.id.card2);
            tvAname=itemView.findViewById(R.id.tvAname);
            tvAmount=itemView.findViewById(R.id.tvAmount);
            tvPrice=itemView.findViewById(R.id.tvPrice);
        }
    }
}
