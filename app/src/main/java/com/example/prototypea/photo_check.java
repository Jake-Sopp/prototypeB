package com.example.prototypea;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Base64;
import android.widget.Button;
import android.widget.ImageView;

public class photo_check extends AppCompatActivity {
    SharedPreferences sp,sm;
    String imagestr,player,object;
    ImageView complate_image;
    Button confirm,deny;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_check);
        complate_image=findViewById(R.id.complation_image);
        sp = getSharedPreferences("Grid_assigments", Context.MODE_PRIVATE);
        sm = getSharedPreferences("completion", Context.MODE_PRIVATE);
        imagestr=sm.getString(player+object+"answer","place");
        Drawable d = new BitmapDrawable(getResources(), (StringToBitMap(imagestr)));
        complate_image.setBackground(d);
    }
    public Bitmap StringToBitMap(String encodedString){
        try {
            byte [] encodeByte= Base64.decode(encodedString,Base64.DEFAULT);
            return BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
        } catch(Exception e) {
            e.getMessage();
            return null;
        }
    }
}