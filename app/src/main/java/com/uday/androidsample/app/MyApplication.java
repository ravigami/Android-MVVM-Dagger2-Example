package com.uday.androidsample.app;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;
import com.uday.androidsample.diprovider.ApiModule;
import com.uday.androidsample.diprovider.AppModule;
import com.uday.androidsample.network.ConnectivityReceiver;

/**
 * Created by Uday on 27/06/2018.
 */

public class MyApplication extends Application {

    private static ApiComponent mApiComponent;
    private static MyApplication mInstance;


    public static synchronized MyApplication getInstance() {
        return mInstance;
    }

    public void setConnectivityListener(ConnectivityReceiver.ConnectivityReceiverListener listener) {
        ConnectivityReceiver.connectivityReceiverListener = listener;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        mApiComponent = DaggerApiComponent.builder()
                .appModule(new AppModule(this))
                .apiModule(new ApiModule(Constant.BASE_URL))
                .build();
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);
        // Normal app init code...
    }


    public static ApiComponent getNetComponent() {
        return mApiComponent;
    }
}