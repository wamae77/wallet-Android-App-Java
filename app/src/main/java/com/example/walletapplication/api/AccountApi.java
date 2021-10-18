package com.example.walletapplication.api;

import com.example.walletapplication.models.Account;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface AccountApi {
    @POST("accounts/balance")
    Call<Account> getBalance(@Body Account account);
}
