package com.suri.example.activities.home.module;

import com.suri.example.contract.HomeContract;
import com.suri.example.contract.StrangerContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by K53SV on 3/13/2017.
 */

@Module
public class HomeModule {
    private HomeContract.View view;
    private StrangerContract.View v;

    public HomeModule(HomeContract.View view, StrangerContract.View v) {
        this.v = v;
        this.view = view;
    }

    @Provides
    HomeContract.View provideHomePageContractView() {
        return view;
    }

    @Provides
    StrangerContract.View provideStrangerContractView() {
        return v;
    }
}
