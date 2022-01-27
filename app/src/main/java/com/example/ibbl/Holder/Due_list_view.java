package com.example.ibbl.Holder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ibbl.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class Due_list_view extends RecyclerView.ViewHolder {
    public CircleImageView profile_pic;
    public TextView UserName,Due_taka;

    public Due_list_view(@NonNull View itemView) {
        super(itemView);
        profile_pic=itemView.findViewById(R.id.AdminDueListUserLIstProfilePIcId);
        UserName=itemView.findViewById(R.id.AdmindueListUserNAmeID);
        Due_taka=itemView.findViewById(R.id.AdmindueLiatDueID);
    }
}
