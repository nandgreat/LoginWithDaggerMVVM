package com.example.loginwithdaggermvvm.di;

import com.example.loginwithdaggermvvm.di.auth.AuthModule;
import com.example.loginwithdaggermvvm.di.auth.AuthScope;
import com.example.loginwithdaggermvvm.di.auth.AuthViewModelsModule;
import com.example.loginwithdaggermvvm.view.login.LoginActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuildersModule {

    @AuthScope
    @ContributesAndroidInjector(
            modules = {AuthViewModelsModule.class, AuthModule.class})
    abstract LoginActivity contributeAuthActivity();


//    @MainScope
//    @ContributesAndroidInjector(
//            modules = {MainFragmentBuildersModule.class, MainViewModelsModule.class, MainModule.class}
//    )
//    abstract MainActivity contributeMainActivity();

}
