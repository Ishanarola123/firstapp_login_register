package com.example.task1_vpn;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.StyleSpan;
import android.text.style.UnderlineSpan;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.auth.User;
import com.google.type.Color;

import java.util.regex.Pattern;

public class signup extends AppCompatActivity {

    Button sign_up;
    EditText username,email,password,mobileno;
    CheckBox checkBox_remember;
    TextView tvsignin;
    ProgressBar progressBar;
    TextView signupheading;
    //for firebase private variable
    private FirebaseAuth mAuth;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        setContentView(R.layout.activity_signup);


        sign_up = findViewById(R.id.sign_up_btn);
        tvsignin = findViewById(R.id.tvsignin);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        email = findViewById(R.id.email);
        mobileno = findViewById(R.id.mobileno);
        checkBox_remember = findViewById(R.id.checkBox);

        signupheading=findViewById(R.id.textViewheading_signup);

        //take strings from strings.xml file
        SpannableString spannableString = new SpannableString(getString(R.string.signuptext_checkbox));
        SpannableString spannableString1 = new SpannableString(getString(R.string.signup_headding));

        ForegroundColorSpan foregroundColorSpanorange = new ForegroundColorSpan(getColor(R.color.orange));
        ForegroundColorSpan foregroundColorSpanorange1 = new ForegroundColorSpan(getColor(R.color.orange));
        ForegroundColorSpan foregroundColorSpanblack = new ForegroundColorSpan(getColor(R.color.black));
//        ForegroundColorSpan foregroundColorSpanGreen = new ForegroundColorSpan(Color.GREEN);
        StyleSpan boldSpan = new StyleSpan(Typeface.BOLD);
        StyleSpan italicSpan = new StyleSpan(Typeface.ITALIC);
        UnderlineSpan underlineSpan = new UnderlineSpan();
        StrikethroughSpan strikethroughSpan = new StrikethroughSpan();

        spannableString.setSpan(foregroundColorSpanorange, 11, 17, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(boldSpan, 11, 17, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);


        spannableString.setSpan(foregroundColorSpanorange1, 22, 39, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(boldSpan, 22, 39, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        spannableString1.setSpan(foregroundColorSpanblack,7,12,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString1.setSpan(boldSpan,7,12,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        checkBox_remember.setText(spannableString);
        signupheading.setText(spannableString1);

        tvsignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(signup.this, Login.class);
                startActivity(intent);
            }
        });
        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registeruser();
            }
        });


    }

    private void registeruser() {

        String user_name = username.getText().toString();
        String email_id = email.getText().toString();
        String mobile_no = mobileno.getText().toString();
        String password_text = password.getText().toString();

        if (user_name.isEmpty()) {
            username.setError("your name is required");
            username.requestFocus();
            return;
        }

        else if (email_id.isEmpty()) {
            email.setError("your email is required");
            email.requestFocus();
            return;
        }
     else   if (mobile_no.isEmpty()) {
            mobileno.setError("your mobile no is required");
            mobileno.requestFocus();
            return;
        }
     else   if (password_text.isEmpty()) {
            password.setError("your password is required");
            password.requestFocus();
            return;
        }

        //for email validation'
     else   if (!Patterns.EMAIL_ADDRESS.matcher(email_id).matches()) {
            email.setError("please enter the valid email");
            email.requestFocus();
            return;

        }
    else    if (password_text.length() < 6) {
            password.setError("minimum password length should be 6 characters ");
            password.requestFocus();
            return;

        }
    else
        {
            mAuth.createUserWithEmailAndPassword(email_id,password_text).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(signup.this, "Registerd successfully", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(signup.this, Login.class);
                        startActivity(intent);

                    } else {
                        Toast.makeText(signup.this, "can not Registerd !", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    }
