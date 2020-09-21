package com.example.capston.DataModels;

import com.google.gson.annotations.SerializedName;

public class UserListResponse {

    @SerializedName("data")
    User.UserData list[];

    public UserListResponse(User.UserData[] list) {
        this.list = list;
    }

    public User.UserData[] getList() {
        return list;
    }
}
