package com.suri.example.app;

import android.app.Application;


/**
 * Created by K53SV on 3/13/2017.
 */

public class SuriApplication extends Application {
    private ApplicationComponent applicationComponent;

    private static SuriApplication mInstance;

    public static SuriApplication getInstance() {
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();

        mInstance = this;
    }

    public ApplicationComponent getApplicationComponent() {

        return applicationComponent;
    }
}
