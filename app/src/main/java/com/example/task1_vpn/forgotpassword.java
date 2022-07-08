package com.example.task1_vpn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class forgotpassword extends AppCompatActivity {
    Button requestbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotpassword);

       requestbtn=findViewById(R.id.requestbtn);

       requestbtn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intent=new Intent(forgotpassword.this,forgot_request_profile.class);
               startActivity(intent);

           }
       });

    }
}