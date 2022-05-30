package com.example.firstandroidapp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Calendar;

public class SignUp  extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);

        //DAY SPINNER
        ArrayList<String> days = new ArrayList<>();
        for(int x=1; x<=31; x++)
        {
            days.add(Integer.toString(x));
        }
        Spinner spinnerDay = (Spinner) findViewById(R.id.spinnerDays);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, days);
        spinnerDay.setAdapter(adapter1);

        //YEAR SPINNER
        ArrayList<String> years = new ArrayList<>();
        int thisYear = Calendar.getInstance().get(Calendar.YEAR);
        for (int i = 1900; i <= thisYear; i++) {
            years.add(Integer.toString(i));
        }
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, years);

        Spinner spinYear = (Spinner)findViewById(R.id.spinnerYears);
        spinYear.setAdapter(adapter2);

        //Listener of sign up button
        Button buttonSignUp = (Button)findViewById(R.id.buttonSignUp);
        buttonSignUp.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                signUpSubmit();
            }
        });

        //Hide error icon and message
        ImageView imageViewError = (ImageView)findViewById(R.id.imageViewError);
        TextView textViewErrorMessage = (TextView)findViewById(R.id.textViewErrorMessage);
        imageViewError.setVisibility(View.GONE);
        textViewErrorMessage.setVisibility(View.GONE);
    }

    //Sign up submit
    public void signUpSubmit()
    {
        //Error
        TextView textViewErrorMessage = (TextView)findViewById(R.id.textViewErrorMessage);
        ImageView imageViewError = (ImageView)findViewById(R.id.imageViewError);
        String errorMessage="";

        //Username
        TextView textViewUserName = (TextView)findViewById(R.id.textViewUserName);
        EditText editTextUserName = (EditText)findViewById(R.id.editTextUserName);
        String stringUsername = editTextUserName.getText().toString();
        if(stringUsername.isEmpty()){
            errorMessage = "Please fill in a username!";
        }

        //Email
        TextView textViewEmail = (TextView)findViewById(R.id.textViewEmail);
        EditText editTextEmailAddress = (EditText)findViewById(R.id.editTextEmailAddress);
        String stringEmail = editTextEmailAddress.getText().toString();
        if(stringEmail.isEmpty() || stringEmail.startsWith(" ")){
            errorMessage = "Please fill in an email address!";
        }

        //Date of birth DAY
        Spinner spinnerDays = (Spinner)findViewById(R.id.spinnerDays);
        String stringDOBDay = spinnerDays.getSelectedItem().toString();
        int intDOBDay = 0;
        try{
            intDOBDay = Integer.parseInt(stringDOBDay);

            if(intDOBDay < 10){
                stringDOBDay = "0" + stringDOBDay;
            }
        }
        catch(NumberFormatException nfe){
            System.out.println("Could not parse " + nfe);
            errorMessage = "Please select a day for your birthday!";
        }

        //Date of birth MONTH
        Spinner spinnerMonths = (Spinner)findViewById(R.id.spinnerMonths);
        String stringDOBMonth = spinnerMonths.getSelectedItem().toString();
        int intDOBMonth = 0;
        try{
            intDOBMonth = Integer.parseInt(stringDOBMonth);
        }
        catch(NumberFormatException nfe){
            System.out.println("Could not parse " + nfe);
            errorMessage = "Please select a month for your birthday!";
        }

        //Date of birth YEAR
        Spinner spinnerYears = (Spinner)findViewById(R.id.spinnerYears);
        String stringDOBYear = spinnerYears.getSelectedItem().toString();
        int intDOBYear = 0;
        try{
            intDOBYear = Integer.parseInt(stringDOBYear);
        }
        catch (NumberFormatException nfe){
            System.out.println("Could not parse " + nfe );
            errorMessage = "Please select a year for your birthday!";
        }

        //Completed birthdate
        String stringBirthDate = stringDOBDay + "-" + stringDOBMonth + "-" + stringDOBYear;

        //Gender
        RadioGroup radioGroupGender = (RadioGroup)findViewById(R.id.radioGroupGender);
        int radioButtonId = radioGroupGender.getCheckedRadioButtonId();
        View radioButtonGender = radioGroupGender.findViewById(radioButtonId);
        int position = radioGroupGender.indexOfChild(radioButtonGender);

        String stringGender = "";
        if(position==0){
            stringGender = "Male";
        }
        else{
            stringGender = "Female";
        }

        //Height
        EditText editTextNumberHeight = (EditText)findViewById(R.id.editTextNumberHeight);
        String stringHeight = editTextNumberHeight.getText().toString();

        int height = 0;

        try{
            height = Integer.parseInt(stringHeight);
        }
        catch (NumberFormatException nfe){
            System.out.println("Height has to be a number!");
        }

        //Weight
        EditText editTextNumberWeight = (EditText)findViewById(R.id.editTextNumberWeight);
        String stringWeight = editTextNumberWeight.getText().toString();

        double weight = 0;
        try{
            weight = Double.parseDouble(stringWeight);
        }
        catch (NumberFormatException nfe){
            System.out.println("Weight has to be a number!");
        }

        //Target Weight
        EditText editTextNumberTargetWeight = (EditText)findViewById(R.id.editTextNumberTargetWeight);
        String stringTargetWeight = editTextNumberTargetWeight.getText().toString();

        double target_weight = 0;
        try{
            target_weight = Double.parseDouble(stringTargetWeight);
        }
        catch(NumberFormatException nfe){
            System.out.println("Target weight has to be a number!");
        }

        //Error handling
        if(errorMessage.isEmpty())
        {

            //Put data into database
            textViewErrorMessage.setVisibility(View.GONE);
            imageViewError.setVisibility(View.GONE);

            //Insert into database
            DBAdapter db = new DBAdapter(this);
            db.open();

            //Quote smart
            String stringUsernameSQL = db.quoteSmart(stringUsername);
            String stringEmailSQL = db.quoteSmart(stringEmail);
            String stringBirthDateSQL = db.quoteSmart(stringBirthDate);
            String stringGenderSQL = db.quoteSmart(stringGender);
            int heightSQL = db.quoteSmart(height);
            double weightSQL = db.quoteSmart(weight);
            double target_weightSQL = db.quoteSmart(target_weight);

            String stringInput = "NULL, " + stringUsernameSQL + "," + stringEmailSQL + "," + stringBirthDateSQL +
                    "," + stringGenderSQL + "," + heightSQL + "," + weightSQL + "," + target_weightSQL;

            db.insert("users", "user_id, user_nickname, user_email, user_dob, user_gender," +
                    "user_height, user_weight, user_target_weight", stringInput);

            db.close();

            Intent i = new Intent(SignUp.this,MainActivity.class);
            startActivity(i);
        }
        else {
            textViewErrorMessage.setText(errorMessage);
            textViewErrorMessage.setVisibility(View.VISIBLE);
            imageViewError.setVisibility(View.VISIBLE);
        }
    }
}