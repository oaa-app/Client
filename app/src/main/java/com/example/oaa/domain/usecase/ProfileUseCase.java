package com.example.oaa.domain.usecase;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.oaa.data.model.ProfileResponse;
import com.example.oaa.data.repository.UserRepository;
import com.example.oaa.util.Resource;
import com.example.oaa.util.ResultCallback;

public class ProfileUseCase {

    private final UserRepository repository;

    public ProfileUseCase(UserRepository repository) {
        this.repository = repository;
    }

    public LiveData<Resource<ProfileResponse>> execute() {
        MutableLiveData<Resource<ProfileResponse>> liveData = new MutableLiveData<>();
        liveData.setValue(Resource.loading(null));

        repository.profile(new ResultCallback<ProfileResponse>() {
            @Override
            public void onSuccess(ProfileResponse data) {
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
