package com.keeyoshi.foodmandu.ui;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.keeyoshi.foodmandu.R;
import com.keeyoshi.foodmandu.StrictModeClass;
import com.keeyoshi.foodmandu.model.Detail;
import com.keeyoshi.foodmandu.url.Url;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class DetailAdapter extends RecyclerView.Adapter<DetailAdapter.DetailViewHolder> {
    Context mContext;
    List<Detail> detailList;
    public DetailAdapter(Context mContext,List<Detail> detailList){
        this.mContext=mContext;
        this.detailList=detailList;
    }
    @NonNull
    @Override
    public DetailAdapter.DetailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_view,parent,false);
        return new DetailViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DetailAdapter.DetailViewHolder holder, int position) {

        final Detail detail= detailList.get(position);
        String imgPath= Url.imagePath + detail.getImage();
        Log.e("Image path is","this is image" + imgPath);
        holder.tvname.setText(detail.getName());
        holder.tvtitle.setText(detail.getItemType());
        holder.tvaddress.setText(detail.getLocation());

        StrictModeClass.StrictMode();
        try{
            URL url=new URL(imgPath);
            holder.card1.setImageBitmap(BitmapFactory.decodeStream((InputStream) url.getContent()));
        }

        catch (IOException e)
        {
            e.printStackTrace();
        }

    }

    @Override
    public int getItemCount() {
        return detailList.size();
    }

    public class DetailViewHolder extends RecyclerView.ViewHolder{

        TextView tvtitle,tvname,tvaddress;
        ImageView imgbox,card1;
        public DetailViewHolder(@NonNull View itemView) {
            super(itemView);

            tvtitle=itemView.findViewById(R.id.tvtitle);
            tvname=itemView.findViewById(R.id.tvname);
            tvaddress=itemView.findViewById(R.id.tvaddress);
            imgbox=itemView.findViewById(R.id.imgbox);
            card1=itemView.findViewById(R.id.card1);

        }
    }
}
