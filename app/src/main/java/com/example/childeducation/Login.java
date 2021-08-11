package com.example.childeducation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.jetbrains.annotations.NotNull;

public class Login extends AppCompatActivity {


TextView t1;

    EditText emailid,password;
    Button signin;
    TextView signup;
    FirebaseAuth authentic;

    private FirebaseAuth.AuthStateListener fire;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        t1=findViewById(R.id.need);
        t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this,Register.class));

        authentic = FirebaseAuth.getInstance();
        emailid = findViewById(R.id.email);
        password =findViewById(R.id.pass);
        signup =findViewById(R.id.alt);
        signin = findViewById(R.id.loginbutton);

        fire = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull @NotNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = authentic.getCurrentUser();
            if(user!=null){
                Toast.makeText(Login.this, "Login Succesfull", Toast.LENGTH_SHORT).show();
                Intent i= new Intent(Login.this,Study.class);
                startActivity(i);
            }
            else{
                Toast.makeText(Login.this, "Please Login first", Toast.LENGTH_SHORT).show();
            }
            }
        };
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailid.getText().toString();
                String pas = password.getText().toString();
                if (email.isEmpty()) {
                    emailid.setError("Enter Email");
                    emailid.requestFocus();
                } else if (pas.isEmpty()) {
                    password.setError("Enter Password");
                    password.requestFocus();
                } else if (email.isEmpty() && pas.isEmpty()) {
                    Toast.makeText(Login.this, "You need to enter email & Password", Toast.LENGTH_SHORT).show();
                } else if (!(email.isEmpty() && pas.isEmpty())) {
                    authentic.signInWithEmailAndPassword(email,pas).addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull @NotNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(Login.this,"User & Password doesn't match, Try again",Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Intent study = new Intent(Login.this,Study.class);
                            startActivity(study);
                        }
                        }
                    });
                } else {
                    Toast.makeText(Login.this, "Error Ocurred !", Toast.LENGTH_SHORT).show();
                }

            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sign = new Intent(Login.this,Register.class);
                startActivity(sign);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        authentic.addAuthStateListener(fire);
    }
}
    }
}
