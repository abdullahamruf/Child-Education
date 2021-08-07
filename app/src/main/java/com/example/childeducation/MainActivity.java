package com.example.childeducation;

import android.media.MediaPlayer;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

     Thread td= new Thread(){

         public void run(){

             try{
                sleep(5000);
             }catch (Exception e){
                e.printStackTrace();
             }
             finally {
              Intent intent=new Intent(MainActivity.this,Register.class);
              startActivity(intent);
              finish();
             }

         }



     };td.start();

    }
}