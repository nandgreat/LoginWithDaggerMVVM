package com.example.loginwithdaggermvvm.di.auth;


import androidx.lifecycle.ViewModel;

import com.example.loginwithdaggermvvm.di.ViewModelKey;
import com.example.loginwithdaggermvvm.view.login.LoginViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class AuthViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel.class)
    public abstract ViewModel bindAuthViewModel(LoginViewModel viewModel);
}
