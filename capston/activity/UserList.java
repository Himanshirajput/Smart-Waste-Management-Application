package com.example.capston.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.capston.Network.ApiInterface;
import com.example.capston.Network.HttpRequest;
import com.example.capston.R;

public class UserList extends BaseActivity {

    ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);
        apiInterface = HttpRequest.getClient().create(ApiInterface.class);

    }
}
