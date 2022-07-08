package com.example.task1_vpn;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    EditText login_email,login_password;
    Button login_btn;
    TextView tvsignup;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();


        TextView forgotpass = (TextView) findViewById(R.id.forgotpassword);
        forgotpass.setPaintFlags(forgotpass.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        login_email=findViewById(R.id.username_email);
        login_password=findViewById(R.id.password_login);
        login_btn=findViewById(R.id.login_btn);

       tvsignup= findViewById(R.id.tvsignup);
       tvsignup.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intent=new Intent(Login.this,signup.class);
               startActivity(intent);
           }
       });

       forgotpass.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intent=new Intent(Login.this,forgotpassword.class);
               startActivity(intent);
           }
       });
       login_btn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               loginUser();


           }
       });





    }

    private boolean loginUser() {
        String emaillogin=login_email.getText().toString();
        String passwordlogin=login_password.getText().toString();

        if (emaillogin.isEmpty()) {
          login_email.setError("your email is required");
            login_email.requestFocus();
            return false;
        }

        else if (passwordlogin.isEmpty())
        {
            login_password.setError("your password is required!");
            login_password.requestFocus();
            return false;
        }
        else   if (!Patterns.EMAIL_ADDRESS.matcher(emaillogin).matches()) {
          login_email.setError("please enter the valid email");
          login_email.requestFocus();
            return false;

        }
        else   if (passwordlogin.length() < 6) {
             login_password.setError("minimum password length should be 6 characters ");
             login_password.requestFocus();
            return false;
        }
     else
        {
           mAuth.signInWithEmailAndPassword(emaillogin,passwordlogin).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
               @Override
               public void onComplete(@NonNull Task<AuthResult> task) {
                   if (task.isSuccessful()){
                       Toast.makeText(Login.this, "logged in  successfully", Toast.LENGTH_SHORT).show();
                       Intent intent = new Intent(Login.this, forgot_request_profile.class);
                       startActivity(intent);
                   } else {
                       // If sign in fails, display a message to the user.
                       Toast.makeText(Login.this, "can not login!", Toast.LENGTH_SHORT).show();
                   }
               }
           });
        }
        return true;
    }
}