package com.example.walletapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.example.walletapplication.api.viewModels.customerViewModel;
import com.example.walletapplication.models.Customer;
import com.example.walletapplication.models.CustomerDao;
import com.example.walletapplication.models.DaoSession;
import com.google.android.material.textfield.TextInputEditText;

import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.query.WhereCondition;

import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedPreferences = getSharedPreferences("prefs", Context.MODE_PRIVATE);

        Button button = findViewById(R.id.button_login);
        button.setOnClickListener(view -> {
            getUserInput();
    });
    }
    private void getUserInput(){
        TextInputEditText customerId = findViewById(R.id.customer_id_edit_text);
        TextInputEditText customerPin = findViewById(R.id.customer_pin_edit_text);

        Customer c = new Customer(customerId.getText().toString().toUpperCase(), customerPin.getText().toString());
        requestServiceLogin(c);
    }
    private void requestServiceLogin(Customer c){
        customerViewModel cv =  new ViewModelProvider(this).get(customerViewModel.class);
        cv.loginCustomerVM(this,c);
        cv.mutableLiveData.observe(this, this::insertUserIntoDb);

    }

    private void insertUserIntoDb(Customer customer){
        DaoSession daoSession = ((App) getApplication()).getDaoSession();
        CustomerDao c = daoSession.getCustomerDao();

        if (userExistByCustomerId(c,customer)){
            moveToHomepage(customer);
            return;
        }
        c.insert(customer);
        moveToHomepage(customer);
    }

    private Boolean userExistByCustomerId(CustomerDao customerDao,Customer customer) {

       long count = customerDao.queryBuilder().where(CustomerDao.Properties.CustomerId.eq(customer.getCustomerId()))
                .count();
     return   count > 0;
    }

    private void moveToHomepage(Customer customer) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("customerId", customer.getCustomerId());
        editor.putString("accountNo", customer.getAccountNo());
        editor.apply();
        Intent intent = new Intent(this, Homepage.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }

}