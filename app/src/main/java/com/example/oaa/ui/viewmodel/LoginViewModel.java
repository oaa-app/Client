package com.example.oaa.ui.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.oaa.data.model.LoginRequest;
import com.example.oaa.data.model.LoginResponse;
import com.example.oaa.data.repository.UserRepository;
import com.example.oaa.domain.usecase.LoginUseCase;
import com.example.oaa.util.AuthManager;
import com.example.oaa.util.Resource;

public class LoginViewModel extends AndroidViewModel {

    private final LoginUseCase loginUseCase;
    private final AuthManager authManager;

    // ⚠️ 注意：只有这一个构造函数
    public LoginViewModel(@NonNull Application application) {
        super(application);

        // 在 ViewModel 内部完成依赖组装
        UserRepository repository = new UserRepository(application);
        loginUseCase = new LoginUseCase(repository);
        authManager = new AuthManager(application);
    }

    // 登录
    public LiveData<Resource<LoginResponse>> login(String username, String password) {
        LoginRequest request = new LoginRequest(username, password);
        LiveData<Resource<LoginResponse>> result = loginUseCase.login(request);

        // 登录成功后保存 token
        result.observeForever(resource -> {
            if (resource != null
                    && resource.getStatus() == Resource.Status.SUCCESS
                    && resource.getData() != null) {
                authManager.saveToken(resource.getData().getToken());
            }
        });

        return result;
    }

    // 是否已登录
    public boolean isLogin() {
        return authManager.isLogin();
    }
}
