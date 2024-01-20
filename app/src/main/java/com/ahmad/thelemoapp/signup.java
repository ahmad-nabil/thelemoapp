package com.ahmad.thelemoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class signup extends AppCompatActivity implements View.OnClickListener {
EditText fullname_signup,email_signup,phone_signup,password_signup;
SharedPreferences savedata;
Button backbtn_signup,signupbtn;
    private RadioButton CashRadioBtn_signup,VisaRadioBtn_signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        //Initialize UI component
        fullname_signup=findViewById(R.id.fullname_signup);
        email_signup=findViewById(R.id.email_signup);
        phone_signup=findViewById(R.id.phone_signup);
        password_signup=findViewById(R.id.password_signup);
        backbtn_signup=findViewById(R.id.backbtn_signup);
        signupbtn=findViewById(R.id.signupbtn);
        CashRadioBtn_signup=findViewById(R.id.CashRadioBtn_signup);
        VisaRadioBtn_signup=findViewById(R.id.VisaRadioBtn_signup);
        backbtn_signup.setOnClickListener(this);
        signupbtn.setOnClickListener(this);
        //give white background for input fields
        fullname_signup.setBackgroundColor(Color.WHITE);
        email_signup.setBackgroundColor(Color.WHITE);
        phone_signup.setBackgroundColor(Color.WHITE);
        password_signup.setBackgroundColor(Color.WHITE);
        //shared preference
        savedata=getSharedPreferences("user",MODE_PRIVATE);

    }

    @Override
    public void onClick(View view) {
        if (view.getId()==R.id.backbtn_signup){
            finish();
        }else if (view.getId()==R.id.signupbtn){
            if (TextUtils.isEmpty(fullname_signup.getText())||
                    TextUtils.isEmpty(email_signup.getText())||
                    TextUtils.isEmpty(phone_signup.getText())||
                    TextUtils.isEmpty(password_signup.getText()))
            {
                Toast.makeText(this, "fill all information please ", Toast.LENGTH_SHORT).show();
            }else {
                if (password_signup.getText().toString().length()>6){
                    SharedPreferences.Editor save=savedata.edit();
                    save.putString("FullName",fullname_signup.getText().toString()).commit();
                    save.putString("Email",email_signup.getText().toString()).commit();
                    save.putString("password"+email_signup.getText().toString(),password_signup.getText().toString()).commit();
                    save.putString("phone",phone_signup.getText().toString()).commit();
                    if (CashRadioBtn_signup.isChecked()){
                        save.putBoolean("cash",true).commit();
                        startActivity(new Intent(this, MainActivity.class));
                    }else if (VisaRadioBtn_signup.isChecked()){
                        save.putBoolean("cash",true).commit();
                        startActivity(new Intent(this, MainActivity.class));
                    }

                }else{
                    Toast.makeText(this,"enter password more than 6 characters",Toast.LENGTH_SHORT).show();
                }
            }

        }
    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onBackPressed() {
startActivity(new Intent(this,MainActivity.class));    }
}