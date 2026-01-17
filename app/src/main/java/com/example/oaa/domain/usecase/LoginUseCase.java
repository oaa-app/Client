package com.example.oaa.domain.usecase;

import androidx.lifecycle.LiveData;

import com.example.oaa.data.model.LoginRequest;
import com.example.oaa.data.model.LoginResponse;
import com.example.oaa.data.model.RegisterRequest;
import com.example.oaa.data.model.RegisterResponse;
import com.example.oaa.util.Resource;
import com.example.oaa.data.repository.UserRepository;
import com.example.oaa.domain.entity.User;

public class LoginUseCase {

    private final UserRepository repository;

    public LoginUseCase(UserRepository repository) {
        this.repository = repository;
    }

    public LiveData<Resource<LoginResponse>> login(LoginRequest request) {
        return repository.login(request);
    }

    public LiveData<Resource<RegisterResponse>> register(RegisterRequest request) {
        return repository.register(request);
    }
}
