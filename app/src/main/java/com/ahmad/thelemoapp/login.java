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
import android.widget.Toast;

public class login extends AppCompatActivity implements View.OnClickListener {
Button forgetpassBtn,loginbtn,signupBtn;
EditText email_login,password_login;
SharedPreferences getLoginData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //intilize Ui Components
        forgetpassBtn=findViewById(R.id.forgetpassBtn);
        loginbtn=findViewById(R.id.loginbtn);
        signupBtn=findViewById(R.id.signupBTN);
        forgetpassBtn.setOnClickListener(this);
        signupBtn.setOnClickListener(this);
        loginbtn.setOnClickListener(this);
        email_login=findViewById(R.id.email_login);
        password_login=findViewById(R.id.password_login);
        email_login.setBackgroundColor(Color.parseColor("#FFDD3a"));
        password_login.setBackgroundColor(Color.parseColor("#FFDD3a"));
        // shared preference
getLoginData=getSharedPreferences("user",MODE_PRIVATE);
    }

    @Override
    public void onClick(View view) {
        if (view.getId()==R.id.forgetpassBtn){
            forgetpassdialog forgetpass=new forgetpassdialog(login.this);
            forgetpass.show();

        }  else  if (view.getId()==R.id.signupBTN){
            startActivity(new Intent(this, signup.class));
        }else  if (view.getId()==R.id.loginbtn){
            //check if fileds empty or not
if (TextUtils.isEmpty(email_login.getText().toString())||TextUtils.isEmpty(password_login.getText().toString())){
    Toast.makeText(this, "fill all information", Toast.LENGTH_SHORT).show();
}else if (!getLoginData.getString("password"+email_login.getText().toString(),"").isEmpty()){
    //verfied pass if correct or not
    if (password_login.getText().toString().equals(getLoginData.getString("password"+email_login.getText().toString(),""))){
        Toast.makeText(this, "logged now", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this,MainActivity.class));

    }else{
        Toast.makeText(this, "your email or  password not correct", Toast.LENGTH_SHORT).show();    }
}
        }
    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onBackPressed() {
        startActivity(new Intent(this,MainActivity.class));
    }
}