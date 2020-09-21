package com.example.capston.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.example.capston.DataModels.User;
import com.example.capston.Network.LoginManager;
import com.example.capston.R;

public class MyAccountActivity extends AppCompatActivity {

    EditText userNameField;
    EditText firstNAmeFIeld;
    EditText lastNameFIeld;
    EditText aadharField;
    EditText emailField;
    EditText monileField;
    EditText rfIField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account);
        userNameField = (EditText) this.findViewById(R.id.userNameField);
        firstNAmeFIeld = (EditText) findViewById(R.id.firstNameField);
        lastNameFIeld = (EditText) findViewById(R.id.lastNameField);
        emailField = (EditText) findViewById(R.id.emailField);
        monileField = (EditText) findViewById(R.id.mobileField);
        aadharField = (EditText) findViewById(R.id.aadharField);
        rfIField = (EditText) findViewById(R.id.rfIDField);

        System.out.println(userNameField);
        System.out.println(R.id.userNameField);


        User.UserData me = LoginManager.shared.getMe().getData();
        String userName = me.getUserName();
        String firstNAme = me.getFirstName();
        String lastName = me.getLastName();
        String email = me.getEmail();
        String monile = me.getMobile();
        String aadhar = me.getAadhar();
        String rfID = me.getRfID();

        userNameField.setText(userName);
        firstNAmeFIeld.setText(firstNAme);
        lastNameFIeld.setText(lastName);
        emailField.setText(email);
        monileField.setText(monile);
        aadharField.setText(aadhar);
        rfIField.setText(rfID);

        userNameField.setInputType(InputType.TYPE_NULL);
        firstNAmeFIeld.setInputType(InputType.TYPE_NULL);
        lastNameFIeld.setInputType(InputType.TYPE_NULL);
        emailField.setInputType(InputType.TYPE_NULL);
        monileField.setInputType(InputType.TYPE_NULL);
        aadharField.setInputType(InputType.TYPE_NULL);
        rfIField.setInputType(InputType.TYPE_NULL);
    }
}
