package com.example.loginwithdaggermvvm.di.componet;


import com.example.loginwithdaggermvvm.di.PerActivity;
import com.example.loginwithdaggermvvm.di.module.NetworkModule;
import com.example.loginwithdaggermvvm.view.EditActivity;
import com.example.loginwithdaggermvvm.view.LoginActivity;
import com.example.loginwithdaggermvvm.view.MainActivity;

import dagger.Subcomponent;

@PerActivity
@Subcomponent(modules = NetworkModule.class)
public interface NetComponent {

    void inject(MainActivity mainActivity);

    void inject(EditActivity editActivity);

    void inject(LoginActivity loginActivity);

    void inject(com.example.loginwithdaggermvvm.MainActivity mainActivity);


}
