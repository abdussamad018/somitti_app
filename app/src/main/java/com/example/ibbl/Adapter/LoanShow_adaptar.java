package com.example.ibbl.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ibbl.Holder.Loanshow_view;
import com.example.ibbl.Model.LoanApply;
import com.example.ibbl.R;

import java.util.List;

public class LoanShow_adaptar extends RecyclerView.Adapter<Loanshow_view> {
    List<LoanApply> usersList;
    Context context;

    public LoanShow_adaptar(List<LoanApply> usersList, Context context) {
        this.usersList = usersList;
        this.context = context;
    }

    @NonNull
    @Override
    public Loanshow_view onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.loanshow_item, parent, false);
        return new Loanshow_view(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Loanshow_view holder, int position) {
        final LoanApply loanApply = usersList.get(position);
        holder.User_name.setText("আবেদনকারীঃ "+loanApply.getUserName());
        holder.Loan_taka.setText("টাকা লাগবেঃ " + loanApply.getTaka());
        holder.LoanProblem.setText("সমস্যাঃ " + loanApply.getMessage());


    }

    @Override
    public int getItemCount() {
        return usersList.size();
    }
}
