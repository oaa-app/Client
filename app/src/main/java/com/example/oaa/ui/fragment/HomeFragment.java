package com.example.oaa.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class HomeFragment extends Fragment {

    private static final String ARG_TEXT = "arg_text";

    public static HomeFragment newInstance(String text) {
        HomeFragment fragment = new HomeFragment();
        Bundle bundle = new Bundle();
        bundle.putString(ARG_TEXT, text);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        TextView textView = new TextView(getContext());
        String text = "Hello " + (getArguments() != null ? getArguments().getString(ARG_TEXT) : "");
        textView.setText(text);
        textView.setTextSize(24);
        textView.setPadding(50, 50, 50, 50);
        return textView;
    }
}
