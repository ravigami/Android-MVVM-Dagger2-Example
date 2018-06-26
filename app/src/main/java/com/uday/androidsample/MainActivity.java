package com.uday.androidsample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import java.util.Arrays;
import com.uday.androidsample.adapter.CountryFactsAdapter;
import com.uday.androidsample.app.Constant;
import com.uday.androidsample.model.Country;
import com.uday.androidsample.model.Facts;
import com.uday.androidsample.network.Api;
import com.uday.androidsample.viewmodel.FactsViewModel;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    CountryFactsAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // set up the RecyclerView
        recyclerView = findViewById(R.id.rvFacts);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //calling the method to display the heroes

        FactsViewModel model = ViewModelProviders.of(this).get(FactsViewModel.class);

        model.getHeroes().observe(this, new Observer<List<Facts>>() {
            @Override
            public void onChanged(@Nullable List<Facts> factsList) {
                adapter = new CountryFactsAdapter(factsList, getApplicationContext());
                recyclerView.setAdapter(adapter);
            }
        });
       // getHeroes();
    }

    private void getHeroes() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()) //Here we are using the GsonConverterFactory to directly convert json data to object
                .build();

        Api api = retrofit.create(Api.class);

        Call<Country> call = api.getCountryFacts();

        call.enqueue(new Callback<Country>() {
            @Override
            public void onResponse(Call<Country> call, Response<Country> response) {
                Country country = response.body();


                List<Facts> facts =  Arrays.asList(country.getRows());
                recyclerView.setAdapter(new CountryFactsAdapter(facts, getApplicationContext()));


            }

            @Override
            public void onFailure(Call<Country> call, Throwable t) {

                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}
