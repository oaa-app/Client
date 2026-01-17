package com.example.oaa.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.oaa.R;
import com.example.oaa.data.repository.UserRepository;
import com.example.oaa.ui.activity.MainActivity;
import com.example.oaa.util.Resource;
import com.example.oaa.domain.usecase.LoginUseCase;
import com.example.oaa.ui.viewmodel.LoginViewModel;

public class LoginFragment extends Fragment {

    private LoginViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_login, container, false);

        // 初始化 ViewModel（简化，没有用 ViewModelProvider 工厂）
        UserRepository repository = new UserRepository();
        LoginUseCase useCase = new LoginUseCase(repository);
        viewModel = new LoginViewModel(useCase);

        EditText etUsername = root.findViewById(R.id.et_username);
        EditText etPassword = root.findViewById(R.id.et_password);
        Button btnLogin = root.findViewById(R.id.btn_login);
        TextView tvRegister =root.findViewById(R.id.tv_register);

        tvRegister.setOnClickListener(v -> {
            //跳转登录或直接登录
            FragmentTransaction ft = requireActivity().getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.fragment_container, new RegisterFragment());
            ft.addToBackStack(null); // 允许用户按返回键回到登录页
            ft.commit();
        });

        btnLogin.setOnClickListener(v -> {
            String username = etUsername.getText().toString();
            String password = etPassword.getText().toString();

            viewModel.login(username, password)
                    .observe(getViewLifecycleOwner(), resource -> {
                        if (resource != null) {
                            switch (resource.status) {
                                case LOADING:
                                    Toast.makeText(getContext(), "登录中...", Toast.LENGTH_SHORT).show();
                                    break;
                                case SUCCESS:
                                    Toast.makeText(getContext(), "登录成功，用户名：" + resource.data.getUsername(), Toast.LENGTH_SHORT).show();
                                    // TODO: 跳转 MainActivity 或 HomeFragment
                                    Intent intent = new Intent(getContext(), MainActivity.class);
                                    startActivity(intent);
                                    requireActivity().finish(); // 结束登录页，防止返回

                                    break;
                                case ERROR:
                                    Toast.makeText(getContext(), "登录失败：" + resource.message, Toast.LENGTH_SHORT).show();
                                    break;
                            }
                        }
                    });
        });

        return root;
    }
}
