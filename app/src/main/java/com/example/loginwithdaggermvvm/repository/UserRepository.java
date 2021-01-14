package com.example.loginwithdaggermvvm.repository;

import android.util.Log;

import com.example.loginwithdaggermvvm.model.ApiEndPoint;
import com.example.loginwithdaggermvvm.model.db.LoginResponse;
import com.example.loginwithdaggermvvm.model.db.User;
import com.example.loginwithdaggermvvm.model.db.UserDao;
import com.example.loginwithdaggermvvm.model.requests.LoginRequest;

import java.util.concurrent.Executor;

import javax.inject.Singleton;

import io.reactivex.Flowable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@Singleton
public class UserRepository {

    private static final String TAG = "UserRepository";

    private Executor executor;
    private UserDao userDao;
    private ApiEndPoint apiEndPoint;

    public UserRepository(Executor executor, UserDao userDao, ApiEndPoint apiEndPoint) {
        this.executor = executor;
        this.userDao = userDao;
        this.apiEndPoint = apiEndPoint;
    }

    public void loginUser(LoginRequest loginRequest) {
        Log.d(TAG, "loginUser: loginRequest made");

        apiEndPoint.loginUser(loginRequest).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

                if (response.isSuccessful()) {

                    executor.execute(() -> {
                        userDao.insert(response.body().getUser());
                        Log.d(TAG, "onResponse: Login Successful " + response.body().getUser().getName());

                    });
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Log.i("ERROR", "onFailure: " + t.getMessage());
            }
        });
    }

    public Flowable<User> getUserFromDatabase() {
        return userDao.getDBUser();
    }
}
