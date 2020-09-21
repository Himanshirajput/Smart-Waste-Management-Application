package com.example.capston.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.ProgressBar;

import com.example.capston.R;

public class BaseActivity extends AppCompatActivity {

    ProgressDialog mProgressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("======================");
        setContentView(R.layout.activity_base);
        mProgressDialog = new ProgressDialog(this, R.style.AppCompatAlertDialogStyle);
    }

    public void startLoader(String message) {
        mProgressDialog.setMessage(message);
        mProgressDialog.show();
    }

    public void stopLoader() {
        mProgressDialog.hide();
    }
}
