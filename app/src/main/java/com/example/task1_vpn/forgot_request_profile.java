package com.example.task1_vpn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class forgot_request_profile extends AppCompatActivity {
    Button changepassword;
    Button logout;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_forgot_request_profile);
        mAuth = FirebaseAuth.getInstance();


        changepassword=findViewById(R.id.changepassword);
        logout=findViewById(R.id.logout);

        changepassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(forgot_request_profile.this, com.example.task1_vpn.changepassword.class);
                startActivity(intent);

            }
        });
       logout.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               mAuth.signOut();
               Toast.makeText(forgot_request_profile.this, "logout successfully!", Toast.LENGTH_SHORT).show();
               Intent intent=new Intent(forgot_request_profile.this,MainActivity.class);
               startActivity(intent);
           }
       });


    }
}