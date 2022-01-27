package com.example.ibbl.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.ibbl.Holder.Due_list_view;
import com.example.ibbl.Model.User;
import com.example.ibbl.R;

import java.util.List;

public class Due_list_adapter extends RecyclerView.Adapter<Due_list_view> {
    List<User> userList;
    Context context;

    public Due_list_adapter(List<User> userList, Context context) {
        this.userList = userList;
        this.context = context;
    }

    @NonNull
    @Override
    public Due_list_view onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.due_list_item, parent, false);
        return new Due_list_view(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Due_list_view holder, int position) {
        User user = userList.get(position);
        holder.UserName.setText(user.getName());
        holder.Due_taka.setText(user.getTotal_Due());
        if (user.getProfile_img().equals("noimg")){
            holder.profile_pic.setImageResource(R.drawable.img_1);
        }else {
            Glide.with(context).load(user.getProfile_img()).into(holder.profile_pic);
        }

    }

    @Override
    public int getItemCount() {
        return userList.size();
    }
}
