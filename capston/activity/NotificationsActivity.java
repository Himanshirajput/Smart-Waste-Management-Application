package com.example.capston.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.capston.DataModels.NotificationResponse;
import com.example.capston.Network.ApiInterface;
import com.example.capston.Network.HttpRequest;
import com.example.capston.Network.LoginManager;
import com.example.capston.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotificationsActivity extends AppCompatActivity {

    ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);
        apiInterface = HttpRequest.getClient().create(ApiInterface.class);

    }


}
