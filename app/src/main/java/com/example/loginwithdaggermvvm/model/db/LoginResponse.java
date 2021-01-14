package com.example.loginwithdaggermvvm.model.db;

public class LoginResponse {
    private String message;
    private User user;
    private int cart;
    private String status;
    private String token;

    public String getMessage() {
        return message;
    }

    public User getUser() {
        return user;
    }

    public int getCart() {
        return cart;
    }

    public String getStatus() {
        return status;
    }

    public String getToken() {
        return token;
    }
}
