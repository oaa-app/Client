package com.example.oaa.data.repository;

import android.content.Context;

import com.example.oaa.data.model.LoginRequest;
import com.example.oaa.data.model.LoginResponse;
import com.example.oaa.data.model.ProfileResponse;
import com.example.oaa.data.model.RegisterRequest;
import com.example.oaa.data.model.RegisterResponse;
import com.example.oaa.data.network.ApiService;
import com.example.oaa.util.RetrofitClient;
import com.example.oaa.util.Result;
import com.example.oaa.util.ResultCallback;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserRepository extends BaseRemoteDataSource {

    private final ApiService apiService;

    public UserRepository(Context context) {
        this.apiService = RetrofitClient.getApiService(context);
    }

    // ================== 对外业务接口 ==================

    public void login(LoginRequest request, ResultCallback<LoginResponse> callback) {
        apiService.login(request).enqueue(new Callback<Result<LoginResponse>>() {
            @Override
            public void onResponse(
                    Call<Result<LoginResponse>> call,
                    Response<Result<LoginResponse>> response
            ) {
                handleResponse(response, callback);
            }

            @Override
            public void onFailure(
                    Call<Result<LoginResponse>> call,
                    Throwable t
            ) {
                callback.onError(t.getMessage());
            }
        });
    }

    public void register(RegisterRequest request, ResultCallback<RegisterResponse> callback) {
        apiService.register(request).enqueue(new Callback<Result<RegisterResponse>>() {
            @Override
            public void onResponse(
                    Call<Result<RegisterResponse>> call,
                    Response<Result<RegisterResponse>> response
            ) {
                handleResponse(response, callback);
            }

            @Override
            public void onFailure(
                    Call<Result<RegisterResponse>> call,
                    Throwable t
            ) {
                callback.onError(t.getMessage());
            }
        });
    }

    public void sendCode(String email, ResultCallback<String> callback) {
        apiService.sendCode(email).enqueue(new Callback<Result<String>>() {
            @Override
            public void onResponse(
                    Call<Result<String>> call,
                    Response<Result<String>> response
            ) {
                handleResponse(response, callback);
            }

            @Override
            public void onFailure(
                    Call<Result<String>> call,
                    Throwable t
            ) {
                callback.onError(t.getMessage());
            }
        });
    }

    public void profile(ResultCallback<ProfileResponse> callback) {
        apiService.profile().enqueue(new Callback<Result<ProfileResponse>>() {
            @Override
            public void onResponse(
                    Call<Result<ProfileResponse>> call,
                    Response<Result<ProfileResponse>> response
            ) {
                handleResponse(response, callback);
            }

            @Override
            public void onFailure(
                    Call<Result<ProfileResponse>> call,
                    Throwable t
            ) {
                callback.onError(t.getMessage());
            }
        });
    }
}
