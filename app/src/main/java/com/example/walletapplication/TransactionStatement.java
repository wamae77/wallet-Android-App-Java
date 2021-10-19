package com.example.walletapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.walletapplication.api.viewModels.AccountViewModel;
import com.example.walletapplication.api.viewModels.TransactionViewModel;
import com.example.walletapplication.appRecycler.LatestTransactionsRAdapter;
import com.example.walletapplication.appRecycler.TransactionStatementRAdapter;
import com.example.walletapplication.models.Account;
import com.example.walletapplication.models.Customer;
import com.example.walletapplication.models.Transaction;
import com.google.gson.JsonObject;

import java.util.List;

public class TransactionStatement extends AppCompatActivity {
    private SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_statement);
        sharedPreferences = getSharedPreferences("prefs", Context.MODE_PRIVATE);
        String customerId = sharedPreferences.getString("customerId",null);

        queryLast100Transactions(customerId);

    }

    private void queryLast100Transactions(String customerId) {
        Customer customer = new Customer();
        customer.setCustomerId(customerId);

        TransactionViewModel avm = new ViewModelProvider(this).get(TransactionViewModel.class);
        avm.getListOfTransactions(this,customer);
        avm.listMutableLiveData.observe(this, transactions -> {
            TransactionStatementRAdapter lta = new TransactionStatementRAdapter(TransactionStatement.this,transactions);
            RecyclerView recyclerView;
            recyclerView = (RecyclerView) findViewById(R.id.recycler);
            recyclerView.setLayoutManager(new LinearLayoutManager(TransactionStatement.this));
            recyclerView.setAdapter(lta);
        });

    }
}