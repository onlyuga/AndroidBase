package com.suri.example.app;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by K53SV on 3/13/2017.
 */

@Module(includes = ApiModule.class)
public class ApplicationModule {
    Context context;

    public  ApplicationModule(Context context){
        this.context = context;
    }

    @Provides
    @Singleton
    SuriApplication provideApplication() {
        return (SuriApplication) context.getApplicationContext();
    }

    @Provides
    @Singleton
    Context provideContext() {
        return context;
    }

    @Provides
    @Singleton
    public SuriService provideGithubService(SuriApi authApi) {
        return new SuriService(authApi);
    }
}
