package com.ahmad.thelemoapp;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;

public class forgetpassdialog extends Dialog {
    public forgetpassdialog(@NonNull Context context) {
        super(context);
        setContentView(R.layout.forgetpassword);
        EditText emailforgetpass=findViewById(R.id.emailforgetpass);
        Button sendpass=findViewById(R.id.send_pass);
        emailforgetpass.setBackgroundColor(Color.parseColor("#FFDD3a"));
        sendpass.setBackgroundColor(Color.parseColor("#FFDD3a"));
        SharedPreferences getpass= getContext().getSharedPreferences("user",Context.MODE_PRIVATE);
        sendpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
    if (getpass.getString("password"+emailforgetpass.getText().toString(),null) !=null){
        Toast.makeText(context, "your pass is \n"+ getpass.getString("password"+emailforgetpass.getText().toString(),null) , Toast.LENGTH_SHORT).show();

    }else {
        Toast.makeText(context, "didnt found your email in data base", Toast.LENGTH_SHORT).show();
    }
                }
            
        });
    }
}
