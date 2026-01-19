package com.example.oaa.ui.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.oaa.data.model.RegisterRequest;
import com.example.oaa.data.model.RegisterResponse;
import com.example.oaa.domain.entity.User;
import com.example.oaa.domain.usecase.RegisterUseCase;
import com.example.oaa.util.Resource;

public class RegisterViewModel extends ViewModel {

    private final RegisterUseCase useCase;

    public RegisterViewModel(RegisterUseCase useCase) {
        this.useCase = useCase;
    }

    public LiveData<Resource<RegisterResponse>> register(String username, String email, String code, String password) {
        RegisterRequest request = new RegisterRequest(username, email,code, password);
        return useCase.register(request);
    }
    public LiveData<Resource<String>> sendCode(String email) {
        return useCase.sendCode(email);
    }

}
