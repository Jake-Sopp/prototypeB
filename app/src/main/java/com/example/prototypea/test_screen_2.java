package com.example.prototypea;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class test_screen_2 extends AppCompatActivity {
    Button button;
    TextView txt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences sp = getApplication().getSharedPreferences("grid_assignment", Context.MODE_PRIVATE);
        setContentView(R.layout.activity_test_screen2);
        txt=findViewById(R.id.textView);
        button=findViewById(R.id.shared_prefrence_test);
        button.setOnClickListener(v -> {
            String imageStr = sp.getString("id","dave");
            //txt.setText(imageStr);
            Bitmap test=StringToBitMap(imageStr);
            ImageView override = findViewById(R.id.imagespectest);
            override.setImageBitmap(test);
        });
        Button back=findViewById(R.id.done);
        back.setOnClickListener(v -> finish());
    }
    public Bitmap StringToBitMap(String encodedString){
        try {
            byte [] encodeByte= Base64.decode(encodedString,Base64.DEFAULT);
            Bitmap bitmap= BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            return bitmap;
        } catch(Exception e) {
            e.getMessage();
            return null;
        }
    }
}