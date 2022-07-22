package com.example.prototypea;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

public class photo_check extends AppCompatActivity {
    SharedPreferences sp,sm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_check);
        sp = getSharedPreferences("Grid_assigments", Context.MODE_PRIVATE);
        sm = getSharedPreferences("completion", Context.MODE_PRIVATE);
    }
}