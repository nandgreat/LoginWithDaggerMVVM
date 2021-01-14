package com.example.loginwithdaggermvvm.model.db;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity
public class User {
    @SerializedName("image")
    private String image;
    @SerializedName("address")
    private String address;
    @SerializedName("city")
    private String city;
    @SerializedName("createdAt")
    private String createdAt;
    @SerializedName("emailVerifiedAt")
    private String emailVerifiedAt;
    @SerializedName("currentTeamId")
    private String currentTeamId;
    @SerializedName("otp")
    private String otp;
    @SerializedName("updatedAt")
    private String updatedAt;
    @SerializedName("phone")
    private String phone;
    @SerializedName("twoFactorRecoveryCodes")
    private String twoFactorRecoveryCodes;
    @SerializedName("name")
    private String name;

    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int id;
    private String state;
    private String twoFactorSecret;
    private String email;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getEmailVerifiedAt() {
        return emailVerifiedAt;
    }

    public void setEmailVerifiedAt(String emailVerifiedAt) {
        this.emailVerifiedAt = emailVerifiedAt;
    }

    public String getCurrentTeamId() {
        return currentTeamId;
    }

    public void setCurrentTeamId(String currentTeamId) {
        this.currentTeamId = currentTeamId;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getTwoFactorRecoveryCodes() {
        return twoFactorRecoveryCodes;
    }

    public void setTwoFactorRecoveryCodes(String twoFactorRecoveryCodes) {
        this.twoFactorRecoveryCodes = twoFactorRecoveryCodes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getTwoFactorSecret() {
        return twoFactorSecret;
    }

    public void setTwoFactorSecret(String twoFactorSecret) {
        this.twoFactorSecret = twoFactorSecret;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
