package com.uday.androidsample.fragments;

import android.app.Activity;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.uday.androidsample.R;
import com.uday.androidsample.adapter.CountryFactsAdapter;
import com.uday.androidsample.app.Constant;
import com.uday.androidsample.app.MyApplication;
import com.uday.androidsample.model.Country;
import com.uday.androidsample.network.ConnectivityReceiver;
import com.uday.androidsample.utils.MyDividerItemDecoration;
import com.uday.androidsample.viewmodel.FactsViewModel;

import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class FactsFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener, ConnectivityReceiver.ConnectivityReceiverListener {
    RecyclerView recyclerView;
    CountryFactsAdapter adapter;
    SwipeRefreshLayout mSwipeRefreshLayout;
    CountrySelectedListener mCallback;
    @BindView(R.id.rvFacts)
    RecyclerView rvFacts;
    @BindView(R.id.swipe_container)
    SwipeRefreshLayout swipeContainer;
    Unbinder unbinder;

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    // Container Activity must implement this interface
    public interface CountrySelectedListener {
        public void onCountrySelected(String Country);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_factlist, container, false);
        recyclerView = view.findViewById(R.id.rvFacts);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.addItemDecoration(new MyDividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL, 16));
// SwipeRefreshLayout
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_container);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary,
                android.R.color.holo_green_dark,
                android.R.color.holo_orange_dark,
                android.R.color.holo_blue_dark);

        /**
         * Showing Swipe Refresh animation on activity create
         * As animation won't start on onCreate, post runnable is used
         */
        mSwipeRefreshLayout.post(new Runnable() {

            @Override
            public void run() {

                // Fetching data from server
                loadDataToViewModel();
            }
        });

        //getFacts();
        unbinder = ButterKnife.bind(this, view);
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Activity a = null;

        if (context instanceof Activity) {
            a = (Activity) context;
        }
        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            mCallback = (CountrySelectedListener) a;
        } catch (ClassCastException e) {
            throw new ClassCastException(a.toString()
                    + " must implement OnHeadlineSelectedListener");
        }
    }


    private void loadDataToViewModel() {
        // Showing refresh animation before making http call
        if (checkConnection()) {
            mSwipeRefreshLayout.setRefreshing(true);
            final FactsViewModel model = ViewModelProviders.of(this).get(FactsViewModel.class);

            model.getFacts().observe(this, new Observer<Country>() {
                @Override
                public void onChanged(@Nullable Country country) {
                    adapter = new CountryFactsAdapter(Arrays.asList(country.getRows()), getActivity().getApplicationContext());
                    recyclerView.setAdapter(adapter);

                    mCallback.onCountrySelected(country.getTitle());
                    // Stopping swipe refresh
                    mSwipeRefreshLayout.setRefreshing(false);
                }
            });
        } else {
            showSnack(false);
        }
    }

    /**
     * This method is called when swipe refresh is pulled down
     */
    @Override
    public void onRefresh() {
        // Fetching data from server
        loadDataToViewModel();
    }


    // Method to manually check connection status
    private boolean checkConnection() {
        return ConnectivityReceiver.isConnected();

    }

    // Showing the status in Snackbar
    private void showSnack(boolean isConnected) {
        String message;
        int color;
        if (isConnected) {
            message = Constant.INTERNET_CONNECTED;
            color = Color.WHITE;
        } else {
            message = Constant.INTERNET_NOT_CONNECTED;
            color = Color.RED;
        }

        Snackbar snackbar = Snackbar
                .make(getActivity().findViewById(android.R.id.content), message, Snackbar.LENGTH_LONG);

        View sbView = snackbar.getView();
        TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(color);
        snackbar.show();
    }

    @Override
    public void onResume() {
        super.onResume();
        // register connection status listener
        MyApplication.getInstance().setConnectivityListener(this);
    }

    /**
     * Callback will be triggered when there is change in
     * network connection
     */
    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {
        showSnack(isConnected);
    }
}
