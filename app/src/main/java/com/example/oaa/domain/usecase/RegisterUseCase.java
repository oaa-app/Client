package com.example.oaa.domain.usecase;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.oaa.data.model.RegisterRequest;
import com.example.oaa.data.model.RegisterResponse;
import com.example.oaa.data.repository.UserRepository;
import com.example.oaa.util.Resource;
import com.example.oaa.util.ResultCallback;

public class RegisterUseCase {

    private final UserRepository repository;

    public RegisterUseCase(UserRepository repository) {
        this.repository = repository;
    }

    public LiveData<Resource<RegisterResponse>> register(RegisterRequest request) {
        MutableLiveData<Resource<RegisterResponse>> liveData = new MutableLiveData<>();
        liveData.setValue(Resource.loading(null));

        repository.register(request, new ResultCallback<RegisterResponse>() {
            @Override
            public void onSuccess(RegisterResponse data) {
                liveData.setValue(Resource.success(data));
            }

            @Override
            public void onError(String message) {
                liveData.setValue(Resource.error(message, null));
            }
        });

        return liveData;
    }

    public LiveData<Resource<String>> sendCode(String email) {
        MutableLiveData<Resource<String>> liveData = new MutableLiveData<>();
        liveData.setValue(Resource.loading(null));

        repository.sendCode(email, new ResultCallback<String>() {
            @Override
            public void onSuccess(String data) {
                liveData.setValue(Resource.success(data));
            }

            @Override
            public void onError(String message) {
                liveData.setValue(Resource.error(message, null));
            }
        });

        return liveData;
    }
}
