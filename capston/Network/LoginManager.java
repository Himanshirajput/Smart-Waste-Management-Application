package com.example.capston.Network;

import android.app.Notification;

import com.example.capston.DataModels.DustbinInfoResponse;
import com.example.capston.DataModels.NotificationResponse;
import com.example.capston.DataModels.User;
import com.example.capston.DataModels.UserListResponse;

public class LoginManager {

    public static LoginManager shared = new LoginManager();
    User me;
    NotificationResponse notifications;
    DustbinInfoResponse dustbinDetail;
    UserListResponse userList;
      private LoginManager() {

      }

    public NotificationResponse getNotifications() {
        return notifications;
    }

    public void setNotifications(NotificationResponse notifications) {
        this.notifications = notifications;
    }

    public void setDustbinDetail(DustbinInfoResponse dustbinDetail) {
        this.dustbinDetail = dustbinDetail;
    }

    public DustbinInfoResponse getDustbinDetail() {
        return dustbinDetail;
    }

    public static LoginManager getShared() {
        return shared;
    }

    public User getMe() {
        return me;
    }

    public void setUserList(UserListResponse userList) {
        this.userList = userList;
    }

    public UserListResponse getUserList() {
        return userList;
    }

    public void setMe(User me) {
        this.me = me;
    }
}
