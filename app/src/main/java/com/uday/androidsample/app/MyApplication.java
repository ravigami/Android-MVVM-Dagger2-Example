package com.uday.androidsample.app;

import android.app.Application;
import com.uday.androidsample.diprovider.AppModule;
import com.uday.androidsample.diprovider.ApiModule;
/**
 * Created by Uday on 27/06/2018.
 */

public class MyApplication extends Application {

    private ApiComponent mApiComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        mApiComponent = DaggerApiComponent.builder()
                .appModule(new AppModule(this))
                .apiModule(new ApiModule(Constant.BASE_URL))
                .build();
    }

    public ApiComponent getNetComponent() {
        return mApiComponent;
    }
}