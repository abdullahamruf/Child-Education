package com.example.childeducation;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    MediaPlayer music;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

     Thread td= new Thread(){

         public void run(){

             try{
                 music=MediaPlayer.create(MainActivity.this,R.raw.wl);
                 music.start();
                sleep(3000);

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