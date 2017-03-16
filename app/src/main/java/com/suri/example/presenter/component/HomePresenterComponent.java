package com.suri.example.presenter.component;

import com.suri.example.app.ApplicationModule;
import com.suri.example.presenter.HomePresenter;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by K53SV on 3/13/2017.
 */

@Singleton
@Component(modules = {ApplicationModule.class})
public interface HomePresenterComponent {

    //void inject(HomePresenter presenter);
    //void inject(StrangerPresenter strangerPresenter);
}
