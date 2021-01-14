package com.example.loginwithdaggermvvm.di;

import androidx.lifecycle.ViewModelProvider;

import com.example.loginwithdaggermvvm.viewmodel.ViewModelProviderFactory;

import dagger.Binds;
import dagger.Module;


@Module
public abstract class ViewModelFactoryModule {

    @Binds
    public abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelProviderFactory viewModelFactory);

}
