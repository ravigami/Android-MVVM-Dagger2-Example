package com.uday.androidsample.network;

import com.uday.androidsample.model.Country;
import com.uday.androidsample.model.Facts;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Uday on 25/06/2018.
 */

public interface Api {

    @GET("facts.json")
    Call<Country> getCountryFacts();
}