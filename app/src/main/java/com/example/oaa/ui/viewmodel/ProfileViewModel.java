package com.example.oaa.ui.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.oaa.data.model.ProfileResponse;
import com.example.oaa.domain.usecase.ProfileUseCase;
import com.example.oaa.util.Resource;

public class ProfileViewModel extends ViewModel {

    private final ProfileUseCase profileUseCase;

    public ProfileViewModel(ProfileUseCase profileUseCase) {
        this.profileUseCase = profileUseCase;
    }

    /**
     * 加载当前登录用户的个人信息
     */
    public LiveData<Resource<ProfileResponse>> loadProfile() {
        return profileUseCase.execute();
    }
}
