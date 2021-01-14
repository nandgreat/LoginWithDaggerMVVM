package com.example.loginwithdaggermvvm.viewmodel;

import androidx.lifecycle.ViewModel;

import com.example.loginwithdaggermvvm.model.Repositry;
import com.example.loginwithdaggermvvm.model.db.FarmersBean;
import com.example.loginwithdaggermvvm.model.db.User;
import com.example.loginwithdaggermvvm.model.requests.LoginRequest;
import com.example.loginwithdaggermvvm.repository.UserRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;

public class AppViewModel extends ViewModel {

    private UserRepository repository;

    @Inject
    public AppViewModel(UserRepository repository) {
        this.repository = repository;
    }

    public void userLogin(String email, String password) {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail(email);
        loginRequest.setPassword(password);
        repository.loginUser(loginRequest);
    }

    public Flowable<User> getUserFromDB() {
        return repository.getUserFromDatabase();
    }

//    public void insert(){
//       repository.insertData();
//    }
//
//    public void updateFarmerData(final String firstName, final String middleName, final String address, final int id){
//       repository.updateFarmerData(firstName, middleName, address,id);
//    }

}
