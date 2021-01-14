package com.example.loginwithdaggermvvm.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.loginwithdaggermvvm.di.PerActivity;
import com.example.loginwithdaggermvvm.model.Repositry;
import com.example.loginwithdaggermvvm.repository.UserRepository;

import javax.inject.Inject;

/*This class  is for dagger to work with viewmodel*/
@PerActivity
public class ViewModelFactory implements ViewModelProvider.Factory {

    @Inject
    UserRepository userRepository;

    @Inject
    public ViewModelFactory() {

    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(AppViewModel.class)) {
            return (T) new AppViewModel(userRepository);
        }
        throw new IllegalArgumentException("Wrong ViewModel class");
    }

}
