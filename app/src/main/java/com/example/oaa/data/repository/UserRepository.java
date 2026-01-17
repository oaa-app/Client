package com.example.oaa.data.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.oaa.data.model.LoginRequest;
import com.example.oaa.data.model.LoginResponse;
import com.example.oaa.data.model.RegisterRequest;
import com.example.oaa.data.model.RegisterResponse;
import com.example.oaa.data.network.ApiService;
import com.example.oaa.data.network.RetrofitClient;
import com.example.oaa.domain.entity.User;
import com.example.oaa.util.Resource;
import com.example.oaa.util.Result;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserRepository {

    private final ApiService apiService;

    public UserRepository() {
        apiService = RetrofitClient.getApiService();
    }

    // 泛型处理 Result<T>
    private <T> MutableLiveData<Resource<T>> handleResult(Call<Result<T>> call) {
        MutableLiveData<Resource<T>> liveData = new MutableLiveData<>();
        liveData.setValue(Resource.loading(null));

        call.enqueue(new Callback<Result<T>>() {
            @Override
            public void onResponse(Call<Result<T>> call, Response<Result<T>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Result<T> result = response.body();
                    if (result.getCode() == 200) {
                        liveData.setValue(Resource.success(result.getData()));
                    } else {
                        liveData.setValue(Resource.error(result.getMessage(), null));
                    }
                } else {
                    liveData.setValue(Resource.error("服务器返回错误", null));
                }
            }

            @Override
            public void onFailure(Call<Result<T>> call, Throwable t) {
                liveData.setValue(Resource.error(t.getMessage(), null));
            }
        });

        return liveData;
    }


    public LiveData<Resource<LoginResponse>> login(LoginRequest request) {
        return handleResult(apiService.login(request));
    }

    public LiveData<Resource<RegisterResponse>> register(RegisterRequest request) {
        return handleResult(apiService.register(request));
    }

    public LiveData<Resource<String>> sendCode(String email) {
        return handleResult(apiService.sendCode(email));
    }
}
