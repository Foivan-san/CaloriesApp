package com.example.firstandroidapp;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    ImageView error_img;
    TextView error_msg;
    EditText username, password;
    Button signin;
    DBAdapter DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //---------------------------------------SIGN IN--------------------------------------------

        username = findViewById(R.id.Username1);
        password = findViewById(R.id.password1);
        signin = findViewById(R.id.btnSignIn1);
        error_img = findViewById(R.id.ErrorImg1);
        error_msg = findViewById(R.id.ErrorMsg1);
        DB = new DBAdapter(this);

        error_img.setVisibility(View.GONE);
        error_msg.setVisibility(View.GONE);

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = username.getText().toString();
                String pass = password.getText().toString();
                String error_message;

                if(user.equals("")||pass.equals("")){
                    error_message="Please enter all fields!";
                    error_msg.setText(error_message);
                    error_img.setVisibility(View.VISIBLE);
                    error_msg.setVisibility(View.VISIBLE);
                }
                else if(pass.length()<4){
                    error_message="Password has to be at least 4 digits!";
                    error_msg.setText(error_message);
                    error_img.setVisibility(View.VISIBLE);
                    error_msg.setVisibility(View.VISIBLE);
                }
                else{
                    error_img.setVisibility(View.GONE);
                    error_msg.setVisibility(View.GONE);
                    Boolean checkuserpass = DB.checkUsernamePassword(user, pass);
                    if(checkuserpass==true){
                        Toast.makeText(LoginActivity.this,"Sign in successfull!", Toast.LENGTH_LONG).show();
                        Intent i = new Intent(getApplicationContext(),WelcomeActivity.class);
                        startActivity(i);
                    }
                    else{
                        error_message="Invalid credentials!";
                        error_msg.setText(error_message);
                        error_img.setVisibility(View.VISIBLE);
                        error_msg.setVisibility(View.VISIBLE);
                    }
                }
            }
        });
        //---------------------------------------SIGN IN--------------------------------------------
    }
}