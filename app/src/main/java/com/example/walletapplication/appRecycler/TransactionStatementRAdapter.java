package com.example.walletapplication.appRecycler;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.walletapplication.R;
import com.example.walletapplication.models.Transaction;

import java.util.List;

public class TransactionStatementRAdapter extends RecyclerView.Adapter<TransactionStatementRAdapter.ViewHolder> {
    private Context context;
    private List<Transaction> transactionList;

    public TransactionStatementRAdapter(Context context, List<Transaction> transactionList) {
        this.context = context;
        this.transactionList = transactionList;
    }

    @NonNull
    @Override
    public TransactionStatementRAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.table_item, parent, false);
        return new TransactionStatementRAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TransactionStatementRAdapter.ViewHolder holder, int position) {
        Transaction currentItem = transactionList.get(position);

        holder.textView1.setText(currentItem.getAccountNo());
        holder.textView2.setText(currentItem.getAmount());
    }

    @Override
    public int getItemCount() {
        return transactionList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView1 ;
        TextView textView2 ;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView1 = itemView.findViewById(R.id.ctId);
            textView2 = itemView.findViewById(R.id.ctA);

        }
    }
}
