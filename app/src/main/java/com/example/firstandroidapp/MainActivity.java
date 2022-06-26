package com.example.firstandroidapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.safetynet.SafetyNet;
import com.google.android.gms.safetynet.SafetyNetApi;

public class MainActivity extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks {

    //Initialize recaptcha variables
    CheckBox checkBox;
    GoogleApiClient googleApiClient;

    //Recaptcha SiteKey
    String SiteKey = "6Lcx7jYgAAAAAGxbyF9hXGEGqpQd5a46-CvCWZ3J";

    //Sign up-Sign in variables
    ImageView error_img;
    TextView error_msg;
    EditText username, password, repassword;
    Button signUp, signIn;
    DBAdapter DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Database
        DBAdapter db = new DBAdapter(this);
        db.open();

        //Count rows in table food
        int foodTableRows = db.countTableRows("food");

        if(foodTableRows < 1)
        {
            Toast.makeText(this, "Loading setup...", Toast.LENGTH_SHORT).show();
            DBSetupInsert setupInsert = new DBSetupInsert(this);
            setupInsert.insertAllFood();
            Toast.makeText(this, "Setup completed!", Toast.LENGTH_SHORT).show();
        }
        db.close();

        //-------------------------------------SIGN UP- SIGN IN-----------------------------------------
        error_img = findViewById(R.id.ErrorImg);
        error_msg = findViewById(R.id.ErrorMsg);
        username = findViewById(R.id.Username);
        password = findViewById(R.id.password);
        repassword = findViewById(R.id.rePassword);
        signUp = findViewById(R.id.btnSignUp);
        signIn = findViewById(R.id.btnSignIn);
        DB = new DBAdapter(this);

        error_img.setVisibility(View.GONE);
        error_msg.setVisibility(View.GONE);

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = username.getText().toString();
                String pass = password.getText().toString();
                String repass = repassword.getText().toString();
                String error_message;

                if (user.equals("") || pass.equals("") || repass.equals("")) {
                    error_message = "Please enter all fields!";
                    error_msg.setText(error_message);
                    error_img.setVisibility(View.VISIBLE);
                    error_msg.setVisibility(View.VISIBLE);
                } else if (pass.length() < 4) {
                    error_message = "Password has to be at least 4 digits!";
                    error_msg.setText(error_message);
                    error_img.setVisibility(View.VISIBLE);
                    error_msg.setVisibility(View.VISIBLE);
                } else if (!checkBox.isChecked()) {
                    error_message = "Please do the recaptcha verification!";
                    error_msg.setText(error_message);
                    error_img.setVisibility(View.VISIBLE);
                    error_msg.setVisibility(View.VISIBLE);
                } else {
                    error_img.setVisibility(View.GONE);
                    error_msg.setVisibility(View.GONE);
                    if (pass.equals(repass)) {
                        Boolean checkuser = DB.checkUsername(user);
                        if (checkuser == false) {
                            Boolean insert = DB.insertData(user, pass);
                            if (insert == true) {
                                Toast.makeText(MainActivity.this, "Registered successfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), WelcomeActivity.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(MainActivity.this, "Registration failed", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(MainActivity.this, "User already exists! Please sign in", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(MainActivity.this, "Passwords not matching", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });

        //-------------------------------------SIGN UP- SIGN IN-----------------------------------------

        //---------------------------------------RECAPTCHA----------------------------------------------
        //Assign variable
        checkBox = findViewById(R.id.checkbox);

        //Create Google Api Client
        googleApiClient = new GoogleApiClient.Builder(this)
                .addApi(SafetyNet.API)
                .addConnectionCallbacks(MainActivity.this)
                .build();
        googleApiClient.connect();

        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkBox.isChecked()){
                    SafetyNet.SafetyNetApi.verifyWithRecaptcha(googleApiClient,SiteKey)
                            .setResultCallback(new ResultCallback<SafetyNetApi.RecaptchaTokenResult>() {
                                @Override
                                public void onResult(@NonNull SafetyNetApi.RecaptchaTokenResult recaptchaTokenResult) {
                                    Status status  = recaptchaTokenResult.getStatus();
                                    if((status != null) && status.isSuccess()){
                                        Toast.makeText(getApplicationContext(),
                                                "Successfully verified!",
                                                Toast.LENGTH_LONG).show();
                                        checkBox.setTextColor(Color.GREEN);
                                    }
                                }
                            });
                }
                else{
                    checkBox.setTextColor(Color.RED);
                }
            }
        });

    }


    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }
}