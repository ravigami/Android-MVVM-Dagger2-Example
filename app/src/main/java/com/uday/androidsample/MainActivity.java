package com.uday.androidsample;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import com.uday.androidsample.adapter.CountryFactsAdapter;
import com.uday.androidsample.fragments.FactsFragment;

public class MainActivity extends AppCompatActivity implements FactsFragment.CountrySelectedListener {
    RecyclerView recyclerView;
    CountryFactsAdapter adapter;
    ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        actionBar = getSupportActionBar();


        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragmentContainer);

        if (fragment == null) {
            fragment = new FactsFragment();
            ;
            fm.beginTransaction()
                    .add(R.id.fragmentContainer, fragment)
                    .commit();


        }

    }

    public void onCountrySelected(String title) {
        actionBar.setTitle(title);
    }


}
