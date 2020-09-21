package com.example.capston.DataModels;

import com.google.gson.annotations.SerializedName;

public class RegisterResponseModel {
    @SerializedName("message")
    String message;

    @SerializedName("status")
    int status;

    public String getMessage() {
        return message;
    }

    public int getStatus() {
        return status;
    }

    public RegisterResponseModel(String message, int status) {
        this.message = message;
        this.status = status;
    }
}
