package com.poc.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    Button getOtpBtn;
    LinearLayout mobileInputLay, otpInputLay;
    EditText mobileInputTxt;
    TextView otpSentMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        init();
    }

    private void init() {
        getOtpBtn = findViewById(R.id.getotp);
        mobileInputLay = findViewById(R.id.mobileInputLay);
        otpInputLay = findViewById(R.id.otpInputLay);
        mobileInputTxt = findViewById(R.id.mobileInputTxt);
        otpSentMessage = findViewById(R.id.otpSentMessage);
        clicklistener();
    }

    private void clicklistener() {
        getOtpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*if (!mobileInputTxt.getText().toString().equals("") && mobileInputTxt.getText().toString().length() == 10) {
                    otpSentMessage.setText(String.format("OTP has sent to +91XXXXXXX%s",mobileInputTxt.getText().toString().substring(7)));
                    mobileInputLay.setVisibility(View.GONE);
                    otpInputLay.setVisibility(View.VISIBLE);
                } else {
                    Toast.makeText(getApplicationContext(), getString(R.string.enter_mobile), Toast.LENGTH_SHORT).show();
                }*/

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
