package com.example.childeducation;



import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Register extends AppCompatActivity {

    EditText emailid,password,username;
    Button signin;
    TextView already;
    FirebaseAuth authentic;
    ProgressBar p;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        authentic = FirebaseAuth.getInstance();
        emailid = findViewById(R.id.email);
        password =findViewById(R.id.pass);
        username =findViewById(R.id.username);
        signin = findViewById(R.id.signupbutton);
        already = findViewById(R.id.already);
       p = findViewById(R.id.pa);
     //  if(authentic.getCurrentUser() !=null){
          /// startActivity(new Intent(Register.this,Login.class));

      // }
        already.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Register.this,Login.class));
            }
        });
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             String email = emailid.getText().toString();
             String pas=  password.getText().toString();
             String user=username.getText().toString();
             if(TextUtils.isEmpty(email)){
                 emailid.setError("Enter Email");
                 emailid.requestFocus();
             }
             else if (pas.isEmpty()){
                 password.setError("Enter Password");
                 password.requestFocus();
                }
             else if (user.isEmpty()){
                 username.setError("Enter username");
                username.requestFocus();
             }

             else if (pas.length()<6){
                 password.setError("pass should be more than 6");
                 password.requestFocus();
             }
             else if (email.isEmpty() && pas.isEmpty()){
                 Toast.makeText(Register.this,"You need to enter email & Password",Toast.LENGTH_SHORT).show();
             }
             else if (user.isEmpty()){
                 Toast.makeText(Register.this,"You need to fill up all blank",Toast.LENGTH_SHORT).show();
             }

             else if(!(email.isEmpty() && pas.isEmpty())){
                 p.setVisibility(View.VISIBLE);
                 authentic.createUserWithEmailAndPassword(email,pas).addOnCompleteListener(Register.this, new OnCompleteListener<AuthResult>() {
                     @Override

                     public void onComplete(@NonNull @org.jetbrains.annotations.NotNull Task<AuthResult> task) {
                        if(!task.isSuccessful()){
                            Toast.makeText(Register.this,"SignUp Unsuccessful, Please try again",Toast.LENGTH_SHORT).show();
                        }
                        else{
                            startActivity(new Intent(Register.this,Login.class));
                         }
                     }
                 });
             }
             else{
                 Toast.makeText(Register.this,"Error occurred !",Toast.LENGTH_SHORT).show();
             }
            }
        });
    }
}