package com.example.prototypea;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageButton;

public class player_2x2_grid extends AppCompatActivity {
    SharedPreferences sp;
    ImageButton bg1x1;
    ImageButton bg1x2;
    ImageButton bg2x1;
    ImageButton bg2x2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player2x2_grid);
        sp = getSharedPreferences("Grid_assigments", Context.MODE_PRIVATE);
        bg1x1 = findViewById(R.id.complate_1x1);
        bg1x2 = findViewById(R.id.complate_1x2);
        bg2x1 = findViewById(R.id.complate_2x1);
        bg2x2 = findViewById(R.id.complate_2x2);
        setimage();
    }
    private void setimage(){
        bg1x1.setBackgroundResource(sp.getInt("bg1x1assignment", 0));
        bg2x1.setBackgroundResource(sp.getInt("bg2x1assignment", 0));
        bg1x2.setBackgroundResource(sp.getInt("bg1x2assignment", 0));
        bg2x2.setBackgroundResource(sp.getInt("bg2x2assignment", 0));
    }
}