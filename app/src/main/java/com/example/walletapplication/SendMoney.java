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

import com.example.walletapplication.api.viewModels.customerViewModel;
import com.example.walletapplication.api.viewModels.transactionViewModel;
import com.example.walletapplication.models.Customer;
import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.JsonObject;

public class SendMoney extends AppCompatActivity {
    private SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_money);
        sharedPreferences = getSharedPreferences("prefs", Context.MODE_PRIVATE);

        Button button = findViewById(R.id.button_send);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getInputData();
            }
        });

    }

    private void getInputData() {
        TextInputEditText editText1 = findViewById(R.id.Account_To_edit_text);
        TextInputEditText editText2 = findViewById(R.id.amount_edit_text);

        String customerId = sharedPreferences.getString("customerId",null);
        String accountNo = sharedPreferences.getString("accountNo",null);
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("customerId",customerId);
        jsonObject.addProperty("accountFrom",accountNo);
        jsonObject.addProperty("accountTo",editText1.getText().toString().toUpperCase());
        jsonObject.addProperty("amount",editText2.getText().toString());
        requestSendMoney(jsonObject);
    }

    private void requestSendMoney(JsonObject jsonObject){
        transactionViewModel cv =  new ViewModelProvider(this).get(transactionViewModel.class);
        cv.sendMoney(this,jsonObject);
        cv.responseData.observe(this, jsonObject1 -> initDialog());

    }
    private void initDialog(){
         View v= View.inflate(this,R.layout.custom_dialog_success,null);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(v);

        Dialog dialog = builder.create();
        dialog.show();
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        v.findViewById(R.id.closer).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                Intent intent = new Intent(SendMoney.this, Homepage.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        });
    }
}