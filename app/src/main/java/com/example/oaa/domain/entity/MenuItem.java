package com.example.oaa.domain.entity;

public class MenuItem {
    public int iconRes;
    public String title;
    public Runnable action; // 点击事件

    public MenuItem(int iconRes, String title, Runnable action) {
        this.iconRes = iconRes;
        this.title = title;
        this.action = action;
    }
}
