package com.example.ibbl.Holder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ibbl.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class dueCollection_View_Holders extends RecyclerView.ViewHolder {
  public   CircleImageView User_profile;
  public   TextView UserName,Due;

    public dueCollection_View_Holders(@NonNull View itemView) {
        super(itemView);
        User_profile=itemView.findViewById(R.id.AdminUserLIstProfilePIcId);
        UserName=itemView.findViewById(R.id.AdmindueColectionUserNAmeID);
        Due=itemView.findViewById(R.id.AdmindueColectionDueID);
    }
}
