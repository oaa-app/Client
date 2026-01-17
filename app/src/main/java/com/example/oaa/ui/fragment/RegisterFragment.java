package com.example.oaa.ui.fragment;

import static org.chromium.base.ContextUtils.getApplicationContext;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.oaa.R;
import com.example.oaa.data.network.ApiService;
import com.example.oaa.data.network.RetrofitClient;
import com.example.oaa.data.repository.UserRepository;
import com.example.oaa.domain.usecase.RegisterUseCase;
import com.example.oaa.ui.viewmodel.RegisterViewModel;
import com.example.oaa.util.Resource;
import com.example.oaa.util.Result;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterFragment extends Fragment {

    private RegisterViewModel viewModel;
    private final ApiService apiService;

    public RegisterFragment() {
        apiService = RetrofitClient.getApiService();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_register, container, false);

        UserRepository repository = new UserRepository();
        RegisterUseCase useCase = new RegisterUseCase(repository);
        viewModel = new RegisterViewModel(useCase);

        TextInputEditText etUsername = root.findViewById(R.id.et_username);
        TextInputEditText etEmail = root.findViewById(R.id.et_email);
        TextInputEditText etCode = root.findViewById(R.id.et_code);
        TextInputEditText etPassword = root.findViewById(R.id.et_password);
        MaterialButton btnRegister = root.findViewById(R.id.btn_register);
        TextView tvLogin =root.findViewById(R.id.tv_login);

        MaterialButton btnSendCode = root.findViewById(R.id.btn_send_code);

        btnSendCode.setOnClickListener(v -> {
            String email = etEmail.getText().toString().trim();
            if (email.isEmpty()) {
                Toast.makeText(getContext(), "请输入邮箱", Toast.LENGTH_SHORT).show();
                return;
            }

            // 调用后端接口发送验证码
            apiService.sendCode(email).enqueue(new Callback<Result<String>>() {
                @Override
                public void onResponse(Call<Result<String>> call, Response<Result<String>> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        Result<String> result = response.body();
                        if (result.getCode() == 200) {
                            Toast.makeText(getContext(), "验证码已发送", Toast.LENGTH_SHORT).show();
                            startCountdown(btnSendCode, 60);
                        } else {
                            Toast.makeText(getContext(), "发送失败：" + result.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(getContext(), "服务器返回错误", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Result<String>> call, Throwable t) {
                    Toast.makeText(getContext(), "发送失败：" + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        });



        tvLogin.setOnClickListener(v -> {
            //跳转登录或直接登录
            FragmentTransaction ft = requireActivity().getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.fragment_container, new LoginFragment());
            ft.commit();
        });
        btnRegister.setOnClickListener(v -> {
            String username = etUsername.getText().toString();
            String email = etEmail.getText().toString();
            String code = etCode.getText().toString();
            String password = etPassword.getText().toString();

            viewModel.register(username, email, code , password)
                    .observe(getViewLifecycleOwner(), resource -> {
                        if(resource != null){
                            switch(resource.status){
                                case LOADING:
                                    Toast.makeText(getContext(), "注册中...", Toast.LENGTH_SHORT).show();
                                    break;
                                case SUCCESS:
                                    Toast.makeText(getContext(), "注册成功！用户名：" + resource.data.getUsername(), Toast.LENGTH_SHORT).show();
                                    //跳转登录或直接登录
                                    FragmentTransaction ft = requireActivity().getSupportFragmentManager().beginTransaction();
                                    ft.replace(R.id.fragment_container, new LoginFragment());
                                    ft.commit();
                                    break;
                                case ERROR:
                                    Toast.makeText(getContext(), "注册失败：" + resource.message, Toast.LENGTH_SHORT).show();
                                    break;
                            }
                        }
                    });
        });

        return root;
    }
    private void startCountdown(MaterialButton button, int seconds) {
        button.setEnabled(false);
        new CountDownTimer(seconds * 1000L, 1000) {
            int remaining = seconds;

            @Override
            public void onTick(long millisUntilFinished) {
                button.setText((remaining--) + "s");
            }

            @Override
            public void onFinish() {
                button.setEnabled(true);
                button.setText("发送");
            }
        }.start();
    }
}
