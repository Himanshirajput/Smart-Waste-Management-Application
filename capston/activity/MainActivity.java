package com.example.capston.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.capston.DataModels.LoginInputModel;
import com.example.capston.DataModels.User;
import com.example.capston.Network.ApiInterface;
import com.example.capston.Network.HttpRequest;
import com.example.capston.Network.LoginManager;
import com.example.capston.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends BaseActivity {

    Button loginBtn;
    EditText userNameField;
    EditText passwordField;
    ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        apiInterface = HttpRequest.getClient().create(ApiInterface.class);

        loginBtn = (Button) findViewById(R.id.loginBtn);

        userNameField = (EditText) findViewById(R.id.userNameField);
        passwordField = (EditText) findViewById(R.id.passwordField);

    }


    public void loginClicked(View view)  {

        if (userNameField.getText().length() <= 6) {
            this.showError("User Name must be minimum of 6 charachters long");
        } else if(passwordField.getText().length() <= 6) {
            this.showError("Password must be minimum of 3 charachters long");
        } else {
            loginAtServer();
        }





    }


    void loginAtServer() {
        String userName = userNameField.getText().toString();
        String password = passwordField.getText().toString();
        LoginInputModel body = new LoginInputModel(userName, password);
        retrofit2.Call<User> call = apiInterface.loginUser(body);
        this.startLoader("Authenticating");
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                MainActivity.this.stopLoader();
                LoginManager.shared.setMe(response.body());
                if (response.body().getStatus() != 200) {
                    showError(response.body().getMessage());
                } else {
                    moveToHomeScreen();

                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                MainActivity.this.stopLoader();
                System.out.print("=======Failure==========");
                System.out.println(t);
                System.out.println(t.getMessage());

                showError(t.getMessage());
            }
        });
    }

    public void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
    public void moveToHomeScreen() {
        Intent i = new Intent(this, HomeScreen.class);
        startActivity(i);
    }

}
