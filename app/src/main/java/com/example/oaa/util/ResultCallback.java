package com.example.oaa.util;

public interface ResultCallback<T> {
    void onSuccess(T data);
    void onError(String message);
}
