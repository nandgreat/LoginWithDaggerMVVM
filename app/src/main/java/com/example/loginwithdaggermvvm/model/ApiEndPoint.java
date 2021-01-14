package com.example.loginwithdaggermvvm.model;


import com.example.loginwithdaggermvvm.model.db.LoginResponse;
import com.example.loginwithdaggermvvm.model.db.User;
import com.example.loginwithdaggermvvm.model.requests.LoginRequest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiEndPoint {

    @GET("get-sample-farmers")
    Call<Records> getPostCall();

    @Headers("Content-Type: application/json")
    @POST("login/user")
    Call<LoginResponse> loginUser(@Body LoginRequest loginRequest);

}
