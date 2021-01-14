package com.example.loginwithdaggermvvm.model.requests;

public class LoginRequest {
    private String password;
    private String email;

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
