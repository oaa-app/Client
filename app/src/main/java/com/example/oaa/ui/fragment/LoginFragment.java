package com.example.oaa.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

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

        viewModel = new ViewModelProvider(this)
                .get(LoginViewModel.class);

        EditText etUsername = root.findViewById(R.id.et_username);
        EditText etPassword = root.findViewById(R.id.et_password);
        Button btnLogin = root.findViewById(R.id.btn_login);

        btnLogin.setOnClickListener(v -> {
            viewModel.login(
                    etUsername.getText().toString(),
                    etPassword.getText().toString()
            ).observe(getViewLifecycleOwner(), resource -> {
                if (resource == null) return;

                switch (resource.status) {
                    case LOADING:
                        Toast.makeText(getContext(), "登录中...", Toast.LENGTH_SHORT).show();
                        break;
                    case SUCCESS:
                        startActivity(new Intent(getContext(), MainActivity.class));
                        requireActivity().finish();
                        break;
                    case ERROR:
                        Toast.makeText(getContext(), resource.message, Toast.LENGTH_SHORT).show();
                        Log.v("oaa",resource.message);
                        break;
                }
            });
        });

        return root;
    }

}
