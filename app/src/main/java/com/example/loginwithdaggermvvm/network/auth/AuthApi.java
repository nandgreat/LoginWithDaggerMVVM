package com.example.loginwithdaggermvvm.network.auth;

import com.google.gson.JsonElement;
import com.example.loginwithdaggermvvm.model.db.LoginResponse;
import com.example.loginwithdaggermvvm.model.db.User;
import com.example.loginwithdaggermvvm.model.requests.LoginRequest;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface AuthApi {

    @GET("users/{id}")
    Flowable<User> getUser(
            @Path("id") int id
    );

    @Headers("Content-Type: application/json")
    @POST("login/user")
    Observable<JsonElement> loginUser(@Body LoginRequest loginRequest);
}
