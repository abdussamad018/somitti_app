package com.example.ibbl.Holder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ibbl.R;

public class Loanshow_view extends RecyclerView.ViewHolder {

    public TextView User_name,Loan_taka,LoanProblem;

    public Loanshow_view(@NonNull View itemView) {
        super(itemView);


        User_name=itemView.findViewById(R.id.AdminloanUserNAmeID);
        Loan_taka=itemView.findViewById(R.id.AdminLoanColectionTAkacawaID);
        LoanProblem=itemView.findViewById(R.id.AdminLoanprobelmID);
    }
}
