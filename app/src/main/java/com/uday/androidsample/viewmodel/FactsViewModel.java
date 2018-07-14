package com.uday.androidsample.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.uday.androidsample.app.MyApplication;
import com.uday.androidsample.model.Country;
import com.uday.androidsample.network.Api;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class FactsViewModel extends ViewModel {

    @Inject
    Retrofit retrofit;
    //this is the data that we will fetch asynchronously
    private MutableLiveData<Country> factsObj;

    FactsViewModel(){
        MyApplication.getNetComponent().inject(this);
    }
    //we will call this method to get the data
    public LiveData<Country> getFacts() {
        //if the Object is null
        if (factsObj == null) {
            factsObj = new MutableLiveData<Country>();
            //we will load it asynchronously from server in this method
            loadFacts();
        }

        //finally we will return the Object
        return factsObj;
    }

    public boolean  isDataAvailableViewModel(){
        if (factsObj != null) {
            return true;
        } else {
            return false;
        }
    }

    public LiveData<Country>  getData(){
        return factsObj;
    }
    //This method is using Retrofit to get the JSON data from URL
    private void loadFacts() {

        Api api = retrofit.create(Api.class);
        Call<Country> call = api.getCountryFacts();


        call.enqueue(new Callback<Country>() {
            @Override
            public void onResponse(Call<Country> call, Response<Country> response) {

                factsObj.setValue(response.body());

            }

            @Override
            public void onFailure(Call<Country> call, Throwable t) {

            }
        });
    }

}