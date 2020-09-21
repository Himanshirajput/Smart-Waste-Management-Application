package com.example.capston.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.capston.DataModels.LoginInputModel;
import com.example.capston.DataModels.RegisterInputModel;
import com.example.capston.DataModels.RegisterResponseModel;
import com.example.capston.DataModels.User;
import com.example.capston.Network.ApiInterface;
import com.example.capston.Network.HttpRequest;
import com.example.capston.Network.LoginManager;
import com.example.capston.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterScreen extends BaseActivity {

    EditText userNameField;
    EditText firstNAmeFIeld;
    EditText lastNameFIeld;
    EditText emailField;
    EditText monileField;
    EditText aadharField;
    EditText passwordField;
    EditText rfIField;
    Button registerBtn;

    ApiInterface apiInterface;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_screen);
        userNameField = (EditText) findViewById(R.id.userNameField);
        firstNAmeFIeld = (EditText) findViewById(R.id.firstNameField);
        lastNameFIeld = (EditText) findViewById(R.id.lastNameField);
        emailField = (EditText) findViewById(R.id.emailField);
        monileField = (EditText) findViewById(R.id.mobileField);
        aadharField = (EditText) findViewById(R.id.aadharField);
        passwordField = (EditText) findViewById(R.id.passwordField);
        rfIField = (EditText) findViewById(R.id.rfIDField);
        registerBtn = (Button) findViewById(R.id.registerBtn);

        apiInterface = HttpRequest.getClient().create(ApiInterface .class);




    }

    public void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    public void registerClick(View view) {

        if (userNameField.getText().length() <= 6) {
            this.showError("User Name must be minimum of 6 charachters long");
        } else if(firstNAmeFIeld.getText().length() <= 3) {
            this.showError("First Name must be minimum of 3 charachters long");
        } else if(lastNameFIeld.getText().length() <= 3) {
            this.showError("Last Name must be 3 charachters long");
        } else if(emailField.getText().length() <= 3) {
            this.showError("Please enter a valid Email");
        } else if(monileField.getText().length() != 10) {
            this.showError("Mobile number  must be 10 charachters long");
        } else if(aadharField.getText().length() != 12) {
            this.showError("Aadhar must be 12 charachters long");
        } else if(rfIField.getText().length() <= 4) {
            this.showError("RF ID must be minimum of 4 charachters long");
        } else if(passwordField.getText().length() <= 3) {
            this.showError("Password must be minimum of 6 charachters long");
        } else {
            this.registerUser();
        }

    }

    void registerUser() {

        String userName = userNameField.getText().toString();
        String firstNAme = firstNAmeFIeld.getText().toString();
        String lastName = lastNameFIeld.getText().toString();
        String email = emailField.getText().toString();
        String monile = monileField.getText().toString();
        String aadhar = aadharField.getText().toString();
        String password = passwordField.getText().toString();
        String rfID = rfIField.getText().toString();

        RegisterInputModel body = new RegisterInputModel(userName,firstNAme,lastName,monile,email,password, aadhar, rfID);
        retrofit2.Call<RegisterResponseModel> call = apiInterface.registerUser(body);
        this.startLoader("Registring Your User");
        call.enqueue(new Callback<RegisterResponseModel>() {
            @Override
            public void onResponse(Call<RegisterResponseModel> call, Response<RegisterResponseModel> response) {
                RegisterScreen.this.stopLoader();
                if (response.body().getStatus() != 200) {
                    showError(response.body().getMessage());
                } else {
                    showError("User Succesfully Registered");
                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            RegisterScreen.this.finish();
                        }
                    }, 1000);


                }
            }

            @Override
            public void onFailure(Call<RegisterResponseModel> call, Throwable t) {
                RegisterScreen.this.stopLoader();
                System.out.print("=======Failure==========");
                System.out.println(t);
                System.out.println(t.getMessage());

                showError(t.getMessage());
            }
        });
    }


}
