package com.example.walletapplication.api.viewModels;

import android.content.Context;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.walletapplication.api.RetrofitInstance;
import com.example.walletapplication.api.customerApi;
import com.example.walletapplication.models.Customer;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class customerViewModel extends ViewModel {

    public MutableLiveData<Customer>mutableLiveData = new MutableLiveData<>();

    public void loginCustomerVM(Context context, Customer customer) {

        Retrofit retrofit = RetrofitInstance.getInstance();
        customerApi customer_api = retrofit.create(customerApi.class);
        Call<Customer> call = customer_api.loginCustomer(customer);
        call.enqueue(new Callback<Customer>() {
            @Override
            public void onResponse(Call<Customer> call, Response<Customer> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(context.getApplicationContext(), "response" + response.headers().toString(), Toast.LENGTH_SHORT).show();
                    return;
                }
                if (response.body() != null) {
                    mutableLiveData.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<Customer> call, Throwable t) {
                Toast.makeText(context.getApplicationContext(), "error" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
