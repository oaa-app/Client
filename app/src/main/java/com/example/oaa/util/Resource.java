package com.example.oaa.util;

public class Resource<T> {

    public enum Status { SUCCESS, ERROR, LOADING }

    public final Status status;

    public final String message;
    public final T data;

    private Resource(Status status, String message,T data) {
        this.status = status;
        this.data = data;
        this.message = message;
    }

    public static <T> Resource<T> success(T data) {
        return new Resource<>(Status.SUCCESS, null, data);
    }

    public static <T> Resource<T> error(String msg, T data) {
        return new Resource<>(Status.ERROR, msg, data);
    }

    public static <T> Resource<T> loading(T data) {
        return new Resource<>(Status.LOADING, null, data);
    }
}