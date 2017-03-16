package com.suri.example.app;


import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Date: 9/2/2016
 * Time: 18:54
 *
 * @author Artur Artikov
 */
@Module(includes = {RetrofitModule.class})
public class ApiModule {
	@Provides
	@Singleton
	public SuriApi provideAuthApi(Retrofit retrofit) {
		return retrofit.create(SuriApi.class);
	}
}
