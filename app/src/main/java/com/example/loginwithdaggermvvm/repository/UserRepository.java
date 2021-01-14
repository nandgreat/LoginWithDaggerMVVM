package com.example.loginwithdaggermvvm.repository;

import android.util.Log;

import com.example.loginwithdaggermvvm.model.db.User;
import com.example.loginwithdaggermvvm.model.db.UserDao;
import com.example.loginwithdaggermvvm.model.requests.LoginRequest;
import com.example.loginwithdaggermvvm.network.auth.AuthApi;
import com.example.loginwithdaggermvvm.util.Helper;
import com.google.gson.JsonElement;

import java.util.concurrent.Executor;

import com.google.gson.JsonElement;


import javax.inject.Singleton;

import io.reactivex.Flowable;
import io.reactivex.Observable;

@Singleton
public class UserRepository {

    private static final String TAG = "UserRepository";

    private Executor executor;
    private UserDao userDao;
    private AuthApi apiEndPoint;
    private Helper helper;

    public UserRepository(Executor executor, UserDao userDao, AuthApi apiEndPoint, Helper helper) {
        this.executor = executor;
        this.userDao = userDao;
        this.apiEndPoint = apiEndPoint;
        this.helper = helper;
    }


    public Observable<JsonElement> loginUser(LoginRequest loginRequest) {
        Log.d(TAG, "loginUser: loginRequest made");

        return null;
    }

    public Flowable<User> getUserFromDatabase() {
        return userDao.getDBUser();
    }

    public boolean checkUserLogin() {
        return helper.isLoggedIn();
    }

    public User getCurrentUser() {
        return helper.getLoggedInUser();
    }
}
