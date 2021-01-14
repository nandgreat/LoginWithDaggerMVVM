package com.example.loginwithdaggermvvm.di.module;


import com.example.loginwithdaggermvvm.model.ApiEndPoint;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class AppModule {

    @Provides
    @Singleton
    public Retrofit getRerofit() {
        return new Retrofit.Builder()
                .baseUrl("https://eatnaija.com/api/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Provides
    @Singleton
    public ApiEndPoint getApiEndPoint(Retrofit retrofit) {
        return retrofit.create(ApiEndPoint.class);
    }

    @Provides
    @Singleton
    public Executor executor() {
        return Executors.newFixedThreadPool(2);
    }

}
