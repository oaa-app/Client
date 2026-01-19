package com.example.oaa.domain.entity;

public class User {
    private String userid;
    private String username;
    private String email = "";
    private String avatar = "";
    private String token;

    public User(String userid, String username, String email, String avatarUrl, String token) {
        this.userid = userid;
        this.username = username;
        this.email = email;
        this.avatar = avatarUrl;
        this.token = token;
    }

    // getter & setter


    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public boolean isLogin() {
        return token != null && !token.isEmpty();
    }
}
