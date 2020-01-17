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
import com.keeyoshi.foodmandu.model.Member;
import com.keeyoshi.foodmandu.url.Url;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class MembersAdapter extends RecyclerView.Adapter<MembersAdapter.MembersViewHolder> {
    Context mContext;
    List<Member> memberList;
    public MembersAdapter(Context mContext,List<Member> memberList){
        this.mContext=mContext;
        this.memberList=memberList;
    }
    @NonNull
    @Override
    public MembersAdapter.MembersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.members,parent,false);
        return new MembersAdapter.MembersViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MembersAdapter.MembersViewHolder holder, int position) {

        final Member member= memberList.get(position);
        String imgPath= Url.imagePath+member.getImage();
        holder.tvName.setText(member.getName());
        holder.tvFood.setText(member.getItemtype());
        holder.tvLocation.setText(member.getLocation());

        StrictModeClass.StrictMode();
        try{
            URL url=new URL(imgPath);
            holder.imgmember.setImageBitmap(BitmapFactory.decodeStream((InputStream)url.getContent()));
        }

        catch (IOException e)
        {
            e.printStackTrace();
        }

    }

    @Override
    public int getItemCount() {
        return memberList.size();
    }

    public class MembersViewHolder extends RecyclerView.ViewHolder{

        ImageView imgmember;
        TextView tvName,tvFood,tvLocation;
        public MembersViewHolder(@NonNull View itemView) {
            super(itemView);

            imgmember=itemView.findViewById(R.id.imgmember);
            tvFood=itemView.findViewById(R.id.tvFood);
            tvName=itemView.findViewById(R.id.tvName);
            tvLocation=itemView.findViewById(R.id.tvLocation);

        }
    }
}
