package com.example.walletapplication.api.viewModels;

import android.content.Context;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.walletapplication.api.RetrofitInstance;
import com.example.walletapplication.api.customerApi;
import com.example.walletapplication.api.transactionApi;
import com.example.walletapplication.models.Customer;
import com.example.walletapplication.models.Transaction;
import com.google.gson.JsonObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class transactionViewModel extends ViewModel {
    public MutableLiveData<JsonObject> responseData = new MutableLiveData<>();
    public MutableLiveData<List<Transaction>> listMutableLiveData = new MutableLiveData<>();

    public void sendMoney(Context context, JsonObject jsonObject){
        Retrofit retrofit = RetrofitInstance.getInstance();
        transactionApi transactionApi = retrofit.create(transactionApi.class);
        Call<JsonObject> call = transactionApi.sendMoney(jsonObject);

        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(context, "response" + response.errorBody().toString(), Toast.LENGTH_SHORT).show();
                    return;
                }
                if (response.body() != null) {
                    responseData.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Toast.makeText(context.getApplicationContext(), "error" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
