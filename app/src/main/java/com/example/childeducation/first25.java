package com.example.childeducation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ScrollView;

public class first25 extends AppCompatActivity {
    ScrollView view1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first25);
        view1=findViewById(R.id.scrollview);
        view1.setVisibility(View.VISIBLE);
    }
}