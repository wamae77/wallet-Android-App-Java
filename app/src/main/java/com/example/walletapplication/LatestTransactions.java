package com.example.walletapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.walletapplication.appRecycler.LatestTransactionsRAdapter;
import com.example.walletapplication.models.Customer;
import com.example.walletapplication.models.CustomerDao;
import com.example.walletapplication.models.DaoSession;
import com.example.walletapplication.models.Transaction;
import com.example.walletapplication.models.TransactionDao;

import java.util.List;

public class LatestTransactions extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_latest_transactions);
        getAllTransactionsFromDb();

    }

    private void getAllTransactionsFromDb() {
        DaoSession daoSession = ((App) getApplication()).getDaoSession();
        TransactionDao td = daoSession.getTransactionDao();
        List<Transaction> transaction = td.loadAll();

        LatestTransactionsRAdapter lta = new LatestTransactionsRAdapter(this,transaction);
        RecyclerView recyclerView;
        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(lta);
    }
}