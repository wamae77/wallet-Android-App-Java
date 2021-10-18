package com.example.walletapplication.api.viewModels;

import android.content.Context;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.walletapplication.api.AccountApi;
import com.example.walletapplication.api.RetrofitInstance;
import com.example.walletapplication.models.Account;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class AccountViewModel extends ViewModel {
    public MutableLiveData<Account> responseData = new MutableLiveData<>();

    public void getBalanceVm(Context context, Account account) {

        Retrofit retrofit = RetrofitInstance.getInstance();
        AccountApi accountApi = retrofit.create(AccountApi.class);
        Call<Account> call = accountApi.getBalance(account);

        call.enqueue(new Callback<Account>() {
            @Override
            public void onResponse(Call<Account> call, Response<Account> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(context.getApplicationContext(), "response" + response.headers().toString(), Toast.LENGTH_SHORT).show();
                    return;
                }
                if (response.body() != null) {
                    responseData.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<Account> call, Throwable t) {
                Toast.makeText(context.getApplicationContext(), "error" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
