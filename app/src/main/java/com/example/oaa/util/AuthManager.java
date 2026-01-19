package com.example.oaa.util;

import android.content.Context;
import android.content.SharedPreferences;

public class AuthManager {

    private static final String SP_NAME = "app_prefs";
    private static final String KEY_TOKEN = "token";

    private final SharedPreferences sp;

    public AuthManager(Context context) {
        sp = context.getApplicationContext()
                .getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
    }

    public void saveToken(String token) {
        sp.edit().putString(KEY_TOKEN, token).apply();
    }

    public String getToken() {
        return sp.getString(KEY_TOKEN, null);
    }

    public boolean isLogin() {
        return getToken() != null;
    }

    public void logout() {
        sp.edit().clear().apply();
    }
}

