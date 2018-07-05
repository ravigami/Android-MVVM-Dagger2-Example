package com.uday.androidsample;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import java.util.Arrays;
import com.uday.androidsample.adapter.CountryFactsAdapter;
import com.uday.androidsample.app.Constant;
import com.uday.androidsample.app.MyApplication;
import com.uday.androidsample.fragments.FactsFragment;
import com.uday.androidsample.model.Country;
import com.uday.androidsample.model.Facts;
import com.uday.androidsample.network.Api;
import com.uday.androidsample.viewmodel.FactsViewModel;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.observers.DisposableSingleObserver;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import javax.inject.Inject;
import android.support.v7.widget.Toolbar;
import com.uday.androidsample.utils.MyDividerItemDecoration;

public class MainActivity extends AppCompatActivity implements FactsFragment.CountrySelectedListener{
    @Inject Retrofit retrofit;
    RecyclerView recyclerView;
    CountryFactsAdapter adapter;
    ActionBar actionBar;
    FactsViewModel usersViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ((MyApplication) getApplication()).getNetComponent().inject(this);
        actionBar = getSupportActionBar();
        actionBar.setTitle("");


        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragmentContainer);

        if (fragment == null) {
            fragment = new FactsFragment();
            ;
            fm.beginTransaction()
                    .add(R.id.fragmentContainer, fragment)
                    .commit();


        }
       /*FactsViewModel model = ViewModelProviders.of(this).get(FactsViewModel.class);

        model.getFacts().observe(this, new Observer<List<Facts>>() {
            @Override
            public void onChanged(@Nullable List<Facts> factsList) {
                adapter = new CountryFactsAdapter(factsList, getApplicationContext());
                recyclerView.setAdapter(adapter);
            }
        });*/
       // getFacts();
    }
    public void onCountrySelected(String title) {

        actionBar.setTitle(title);
    }


}
