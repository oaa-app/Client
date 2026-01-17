package com.example.oaa.util;

public class Result<T> {
    private int code;        // 0 成功，非 0 失败
    private String message;  // 提示信息
    private T data;          // 实际数据

    private Result(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    // 成功（无数据）
    public static <T> Result<T> success() {
        return new Result<>(200, "success", null);
    }

    // 成功（有数据）
    public static <T> Result<T> success(T data) {
        return new Result<>(200, "success", data);
    }

    // 失败
    public static <T> Result<T> error(String message) {
        return new Result<>(408, message, null);
    }

    // getters
    public int getCode() { return code; }
    public String getMessage() { return message; }
    public T getData() { return data; }
}


