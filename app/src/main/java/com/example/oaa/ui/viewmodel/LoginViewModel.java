package com.example.oaa.ui.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.oaa.data.model.LoginRequest;
import com.example.oaa.data.model.LoginResponse;
import com.example.oaa.data.model.RegisterRequest;
import com.example.oaa.util.Resource;
import com.example.oaa.domain.entity.User;
import com.example.oaa.domain.usecase.LoginUseCase;

public class LoginViewModel extends ViewModel {

    private final LoginUseCase loginUseCase;

    public LoginViewModel(LoginUseCase useCase) {
        this.loginUseCase = useCase;
    }

    // 返回 LiveData<Resource<User>>，UI 可以根据状态显示加载/错误
    public LiveData<Resource<LoginResponse>> login(String username, String password) {
        LoginRequest request = new LoginRequest(username, password);
        return loginUseCase.login(request);
    }

}
