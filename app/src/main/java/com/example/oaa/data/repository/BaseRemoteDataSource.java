package com.example.oaa.data.repository;

import com.example.oaa.util.Result;
import com.example.oaa.util.ResultCallback;

import retrofit2.Response;

public abstract class BaseRemoteDataSource {

    protected <T> void handleResponse(
            Response<Result<T>> response,
            ResultCallback<T> callback
    ) {
        if (response.isSuccessful() && response.body() != null) {
            Result<T> result = response.body();
            if (result.getCode() == 200) {
                callback.onSuccess(result.getData());
            } else {
                callback.onError(result.getMessage());
            }
        } else {
            callback.onError("服务器返回错误");
        }
    }
}
