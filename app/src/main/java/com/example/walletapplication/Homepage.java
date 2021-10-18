package com.example.walletapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.walletapplication.api.viewModels.AccountViewModel;
import com.example.walletapplication.api.viewModels.customerViewModel;
import com.example.walletapplication.models.Account;
import com.example.walletapplication.models.Customer;
import com.example.walletapplication.models.CustomerDao;
import com.example.walletapplication.models.DaoSession;
import com.google.android.material.card.MaterialCardView;

public class Homepage extends AppCompatActivity {
    private String customerId;
    private SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        sharedPreferences = getSharedPreferences("prefs", Context.MODE_PRIVATE);
        getCustomIdFromPref();
        getCustomerDetails();

        MaterialCardView card_balance = findViewById(R.id.card_balance);
        MaterialCardView card_sendMoney = findViewById(R.id.card_sendMoney);
        MaterialCardView card_viewStatement = findViewById(R.id.card_viewStatement);
        MaterialCardView card_lastTransaction = findViewById(R.id.card_lastTransaction);
        MaterialCardView card_profile = findViewById(R.id.card_profile);
        MaterialCardView card_logout = findViewById(R.id.card_logout);

        card_balance.setOnClickListener(view -> {
            CustomerBalance();
        });

        card_sendMoney.setOnClickListener(view -> {
            Intent intent = new Intent(this, SendMoney.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        });

        card_viewStatement.setOnClickListener(view -> {
            Intent intent = new Intent(this, TransactionStatement.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        });

        card_lastTransaction.setOnClickListener(view -> {
            Intent intent = new Intent(this, LatestTransactions.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        });

        card_profile.setOnClickListener(view -> {
            Intent intent = new Intent(this, Profile.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        });

        card_logout.setOnClickListener(view -> {
            Intent intent = new Intent(this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
        });

    }

    private void getCustomIdFromPref() {
        customerId = sharedPreferences.getString("customerId",null);
    }

    private void getCustomerDetails() {
        DaoSession daoSession = ((App) getApplication()).getDaoSession();
        CustomerDao c = daoSession.getCustomerDao();
        Customer customer = c.queryBuilder().where(CustomerDao.Properties.CustomerId.eq(customerId))
                .build().uniqueOrThrow();

        setUsername(customer);
    }

    private void setUsername(Customer customer) {
        TextView textView = findViewById(R.id.textViewName);
        textView.setText("Welcome " + customer.getFirstName());
    }
    private void CustomerBalance(){
        Account account = new Account();
        account.setCustomerId(customerId);

        AccountViewModel avm = new ViewModelProvider(this).get(AccountViewModel.class);
        avm.getBalanceVm(this,account);
        avm.responseData.observe(this, new Observer<Account>() {
            @Override
            public void onChanged(Account account) {
                initBalanceDialog(account);            }
        });

    }
    private void initBalanceDialog(Account account){
        View v= View.inflate(this,R.layout.balance_dialog,null);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(v);

        Dialog dialog = builder.create();
        dialog.show();
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        TextView textView = v.findViewById(R.id.Customerbalance);
        textView.setText("ksh " + account.getBalance());
        v.findViewById(R.id.closer2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }
}