package com.example.manhospitapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void filesLayout(View view) {
        setContentView(R.layout.files_layout);
    }
    public void loginLayout(View view) {
        setContentView(R.layout.login_layout);
    }
    public void logoutLayout(View view) {
        setContentView(R.layout.logout_layout);
    }
    public void messagesLayout(View view) {
        setContentView(R.layout.messages_layout);
    }
    public void signInLayout(View view) {
        setContentView(R.layout.sign_in_layout);
    }
    public void userLayout(View view) {
        setContentView(R.layout.user_layout);
    }
    public void backToMainLayout(View view) {
        setContentView(R.layout.activity_main);
    }
}