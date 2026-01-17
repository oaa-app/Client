package com.example.oaa.data.network;

import com.example.oaa.data.model.LoginRequest;
import com.example.oaa.data.model.LoginResponse;
import com.example.oaa.data.model.RegisterRequest;
import com.example.oaa.data.model.RegisterResponse;
import com.example.oaa.util.Result;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {
    @POST("api/auth/login")
    Call<Result<LoginResponse>> login(@Body LoginRequest request);

    @POST("api/auth/register")
    Call<Result<RegisterResponse>> register(@Body RegisterRequest request);

    @POST("api/auth/sendCode")
    Call<Result<String>> sendCode(@Query("email")String email);
}
