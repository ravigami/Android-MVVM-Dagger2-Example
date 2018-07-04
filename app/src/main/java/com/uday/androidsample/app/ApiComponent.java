package com.uday.androidsample.app;

import com.uday.androidsample.MainActivity;
import com.uday.androidsample.diprovider.AppModule;
import com.uday.androidsample.diprovider.ApiModule;
import com.uday.androidsample.fragments.FactsFragment;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Uday on 27/06/2018.
 */

@Singleton
@Component(modules = {AppModule.class, ApiModule.class})
public interface ApiComponent {
    void inject(MainActivity activity);
    void inject(FactsFragment fragment);
}
