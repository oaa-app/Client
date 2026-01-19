package com.example.oaa.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.oaa.R;
import com.example.oaa.data.repository.UserRepository;
import com.example.oaa.domain.entity.MenuItem;
import com.example.oaa.domain.usecase.ProfileUseCase;
import com.example.oaa.ui.adapter.MenuAdapter;
import com.example.oaa.ui.viewmodel.ProfileViewModel;
import com.example.oaa.util.Resource;

import java.util.Arrays;
import java.util.List;

public class ProfileFragment extends Fragment {

    private ProfileViewModel viewModel;

    private TextView tvUsername;

    public ProfileFragment() {
    }

    public static ProfileFragment newInstance() {
        return new ProfileFragment();
    }

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState
    ) {
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tvUsername = view.findViewById(R.id.tv_username);

        // 初始化 Repository 和 UseCase
        UserRepository repository = new UserRepository(getContext());
        ProfileUseCase useCase = new ProfileUseCase(repository);

        RecyclerView recyclerFeature = view.findViewById(R.id.recycler_feature);
        RecyclerView recyclerSystem = view.findViewById(R.id.recycler_system);

        // 功能区菜单
        List<MenuItem> featureMenu = Arrays.asList(
                new MenuItem(R.drawable.ic_star, "收藏", () -> {
                    // 点击跳转收藏页面
                }),
                new MenuItem(R.drawable.ic_history, "历史", () -> {
                    // 点击跳转历史页面
                })
        );

        recyclerFeature.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerFeature.setAdapter(new MenuAdapter(featureMenu));

// 系统区菜单
        List<MenuItem> systemMenu = Arrays.asList(
                new MenuItem(R.drawable.ic_settings, "设置", () -> {
                    // 点击跳转设置
                }),
                new MenuItem(R.drawable.ic_help, "帮助", () -> {
                    // 点击跳转帮助
                })

        );

        recyclerSystem.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerSystem.setAdapter(new MenuAdapter(systemMenu));

        // 初始化 ViewModel
        viewModel = new ViewModelProvider(this,
                new ViewModelProvider.Factory() {
                    @NonNull
                    @Override
                    public <T extends androidx.lifecycle.ViewModel> T create(@NonNull Class<T> modelClass) {
                        return (T) new ProfileViewModel(useCase);
                    }
                }).get(ProfileViewModel.class);

        loadProfile();

    }

    private void loadProfile() {
        viewModel.loadProfile().observe(getViewLifecycleOwner(), resource -> {
            if (resource != null) {
                switch (resource.getStatus()) {
                    case LOADING:
                        break;
                    case SUCCESS:
                        if (resource.getData() != null) {
                            tvUsername.setText(resource.getData().getUserName());
                        }
                        break;
                    case ERROR:
                        Toast.makeText(getContext(), "加载失败: " + resource.getMessage(), Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
    }
}
