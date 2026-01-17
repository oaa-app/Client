package com.example.oaa.data.network;

import com.example.oaa.data.network.ApiService;
import com.example.oaa.util.Constants;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static final String BASE_URL = Constants.BASE_URL;

    public static ApiService getApiService() {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiService.class);
    }
}
