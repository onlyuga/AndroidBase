package com.suri.example.app;

import android.content.Context;


import com.suri.example.presenter.HomePresenter;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by K53SV on 3/13/2017.
 */

@Singleton
@Component(modules = {ApplicationModule.class})
public interface ApplicationComponent {
    SuriApplication getApplication();

    Context getContext();

    SuriService getSuriService();
}
