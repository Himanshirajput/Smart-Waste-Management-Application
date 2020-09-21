package com.example.capston.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.capston.Network.LoginManager;
import com.example.capston.R;

public class YourDustbin extends AppCompatActivity {

    TextView locationLabel;
    TextView statusLabel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your_dustbin);

        locationLabel = (TextView) findViewById(R.id.locationLabel);
        statusLabel = (TextView) findViewById(R.id.statusLabel);

        locationLabel.setText(LoginManager.shared.getDustbinDetail().getList()[0].getLocation());

        int fillValue = LoginManager.shared.getDustbinDetail().getList()[0].getFillValue();
        String message;
        if (fillValue == 0) {
            message = "Empty";
        } else if (fillValue == 100) {
            message = "Full";
        } else {
            message = fillValue + "% Filled";
        }
        statusLabel.setText(message);
    }
}
