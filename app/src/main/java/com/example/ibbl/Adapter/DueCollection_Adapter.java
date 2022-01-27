package com.example.ibbl.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.ibbl.Activity.Deposite;
import com.example.ibbl.Model.User;
import com.example.ibbl.R;
import com.example.ibbl.Holder.*;

import java.util.List;

public class DueCollection_Adapter extends RecyclerView.Adapter<dueCollection_View_Holders> {
    List<User> usersList;
    Context context;

    public DueCollection_Adapter(List<User> usersList, Context context) {
        this.usersList = usersList;
        this.context = context;
    }

    @NonNull
    @Override
    public dueCollection_View_Holders onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.duecolection,parent,false);
        return new dueCollection_View_Holders(view);
    }

    @Override
    public void onBindViewHolder(@NonNull dueCollection_View_Holders holder, int position) {
        final User user=usersList.get(position);
       holder.UserName.setText(user.getName());
       holder.Due.setText("বাকি আছেঃ " +user.getTotal_Due());

        if (user.getProfile_img().equals("noimg")){

            holder.User_profile.setImageResource(R.drawable.img_1);
        }else {
            Glide.with(context).load(user.getProfile_img()).into(holder.User_profile);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, Deposite.class);
                intent.putExtra("UserId",user.getID());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return usersList.size();
    }
}
