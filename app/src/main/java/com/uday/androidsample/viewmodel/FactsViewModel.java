package com.uday.androidsample.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.widget.Toast;

import com.uday.androidsample.app.Constant;
import com.uday.androidsample.model.Country;
import com.uday.androidsample.model.Facts;
import com.uday.androidsample.network.Api;

import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FactsViewModel extends ViewModel {

    //this is the data that we will fetch asynchronously
    private MutableLiveData<List<Facts>> factsList;

    //we will call this method to get the data
    public LiveData<List<Facts>> getHeroes() {
        //if the list is null
        if (factsList == null) {
            factsList = new MutableLiveData<List<Facts>>();
            //we will load it asynchronously from server in this method
            loadFacts();
        }

        //finally we will return the list
        return factsList;
    }


    //This method is using Retrofit to get the JSON data from URL
    private void loadFacts() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api api = retrofit.create(Api.class);
        Call<Country> call = api.getCountryFacts();


        call.enqueue(new Callback<Country>(){
            @Override
            public void onResponse(Call<Country> call, Response<Country> response) {

                //finally we are setting the list to our MutableLiveData
                factsList.setValue(Arrays.asList(response.body().getRows()));
            }

            @Override
            public void onFailure(Call<Country> call, Throwable t) {

            }
        });
    }
}