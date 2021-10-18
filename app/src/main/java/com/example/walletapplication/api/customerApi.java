package com.example.walletapplication.api;

import com.example.walletapplication.models.Account;
import com.example.walletapplication.models.Customer;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface customerApi {

    @POST("customers/login")
    Call<Customer> loginCustomer(@Body Customer customer);

}
