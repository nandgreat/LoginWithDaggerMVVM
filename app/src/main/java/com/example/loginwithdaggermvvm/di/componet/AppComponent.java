package com.example.loginwithdaggermvvm.di.componet;


import com.example.loginwithdaggermvvm.di.module.AppModule;
import com.example.loginwithdaggermvvm.di.module.NetworkModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {

    NetComponent appComponent(NetworkModule networkModule);

}
