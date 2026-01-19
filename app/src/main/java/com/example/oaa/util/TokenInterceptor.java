package com.example.oaa.util;

import android.content.Context;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class TokenInterceptor implements Interceptor {

    private final AuthManager authManager;

    public TokenInterceptor(Context context) {
        this.authManager = new AuthManager(context);
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();

        String token = authManager.getToken();

        if (token == null) {
            return chain.proceed(original);
        }

        Request newRequest = original.newBuilder()
                .addHeader("Authorization", "Bearer " + token)
                .build();

        return chain.proceed(newRequest);
    }
}

