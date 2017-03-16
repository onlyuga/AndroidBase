package com.suri.example.activities.home.component;

import com.suri.example.app.ApplicationComponent;
import com.suri.example.activities.MainActivity;
import com.suri.example.activities.home.module.HomeModule;
import com.suri.example.util.ActivityScoped;

import dagger.Component;

/**
 * Created by K53SV on 3/13/2017.
 */

@ActivityScoped
@Component(modules = HomeModule.class, dependencies = ApplicationComponent.class)
public interface HomeComponent {
    void inject(MainActivity mainActivity);
}

