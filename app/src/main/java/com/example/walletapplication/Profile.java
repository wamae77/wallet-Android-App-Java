package com.example.walletapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.walletapplication.models.Customer;
import com.example.walletapplication.models.CustomerDao;
import com.example.walletapplication.models.DaoSession;

public class Profile extends AppCompatActivity {
    private SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        sharedPreferences = getSharedPreferences("prefs", Context.MODE_PRIVATE);
        getCustomerFromDb();

        Button button = findViewById(R.id.button);
        button.setOnClickListener(view -> logout());

    }

    private void logout() {
        Intent intent = new Intent(this, Homepage.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }

    private void getCustomerFromDb() {
        String cId =sharedPreferences.getString("customerId",null);
        DaoSession daoSession = ((App) getApplication()).getDaoSession();
        CustomerDao c = daoSession.getCustomerDao();
        Customer customer = c.queryBuilder().where(CustomerDao.Properties.CustomerId.eq(cId))
                .build().uniqueOrThrow();
        setOnUi(customer);
    }

    private void setOnUi(Customer customer) {
        TextView name = findViewById(R.id.name);
        TextView custId = findViewById(R.id.custId);
        TextView custAcc = findViewById(R.id.custAcc);
        TextView email = findViewById(R.id.email);

        name.setText("Name: "+customer.getFirstName()+""+customer.getLastName());
        custId.setText("CustomerId: " +customer.getCustomerId());
        custAcc.setText("Account No: "+customer.getAccountNo());
        email.setText("Email: " +customer.getEmail());

    }
}