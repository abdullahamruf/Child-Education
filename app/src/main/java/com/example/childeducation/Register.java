package com.example.childeducation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Register extends AppCompatActivity {

    EditText emailid,password;
    Button signin;
    TextView sign;
    FirebaseAuth authentic;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        authentic = FirebaseAuth.getInstance();
        emailid = findViewById(R.id.email);
        password =findViewById(R.id.pass);
        signin = findViewById(R.id.signupbutton);
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             String email = emailid.getText().toString();
             String pas=  password.getText().toString();
             if(email.isEmpty()){
                 emailid.setError("Enter Email");
                 emailid.requestFocus();
             }
             else if (pas.isEmpty()){
                 password.setError("Enter Password");
                 password.requestFocus();
                }
             else if (email.isEmpty() && pas.isEmpty()){
                 Toast.makeText(Register.this,"You need to enter email & Password",Toast.LENGTH_SHORT).show();
             }
             else if(!(email.isEmpty() && pas.isEmpty())){
                 authentic.createUserWithEmailAndPassword(email,pas).addOnCompleteListener(Register.this, new OnCompleteListener<AuthResult>() {
                     @Override
                     public void onComplete(@NonNull @org.jetbrains.annotations.NotNull Task<AuthResult> task) {
                        if(!task.isSuccessful()){
                            Toast.makeText(Register.this,"SignUp Unsuccessful, Please tryagain",Toast.LENGTH_SHORT).show();
                        }
                        else{
                            startActivity(new Intent(Register.this,Login.class));
                         }
                     }
                 });
             }
             else{
                 Toast.makeText(Register.this,"Error Ocurred !",Toast.LENGTH_SHORT).show();
             }
            }
        });
    }
}