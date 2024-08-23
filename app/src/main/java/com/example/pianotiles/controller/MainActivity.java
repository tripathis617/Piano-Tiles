package com.example.pianotiles.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.pianotiles.dbscripts.Authorization;
import com.example.pianotiles.page.HomePage;
import com.example.pianotiles.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);

        Button buttonLogin = findViewById(R.id.buttonLogin);
        buttonLogin.setOnClickListener(this::onClickLogin);
    }
 
    private void onClickLogin(View v) {
        EditText editTextUserName =  findViewById(R.id.userName);
        EditText editTextUserPassword =  findViewById(R.id.userPassword);
        String userName = editTextUserName.getText().toString();
        String password = editTextUserPassword.getText().toString();
        boolean authenticate = Authorization.userLogIn(userName, password);
        authenticate = true;
        if (authenticate) {
            Intent intent = new Intent(MainActivity.this, HomePage.class);
            startActivity(intent);
        }
    }


}