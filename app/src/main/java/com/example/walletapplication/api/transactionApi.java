package com.example.walletapplication.api;

import androidx.lifecycle.ViewModel;

import com.example.walletapplication.models.Customer;
import com.example.walletapplication.models.Transaction;
import com.google.gson.JsonObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface transactionApi {

    @POST("transactions/send-money")
    Call<JsonObject> sendMoney(@Body JsonObject jsonObject);

    @POST("transactions/last-100-transactions")
    Call<List<Transaction>> getTransactions(@Body Customer customer);

}
