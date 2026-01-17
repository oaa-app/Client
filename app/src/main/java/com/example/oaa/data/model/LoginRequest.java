package com.example.oaa.data.model;

public class LoginRequest {
    private String username;
    private String password;

    public LoginRequest(String u, String p) {
        username = u;
        password = p;
    }
}