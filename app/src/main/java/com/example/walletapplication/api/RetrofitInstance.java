package com.example.walletapplication.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {
    private static Retrofit OUR_INSTANCE;
    public static Retrofit getInstance(){

        /*
            change the url
         */
        if (OUR_INSTANCE == null)
            OUR_INSTANCE = new Retrofit.Builder()
                    .baseUrl("http:Localhost:8092/api/v1/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        return OUR_INSTANCE;
    }

    public RetrofitInstance() {
    }
}

