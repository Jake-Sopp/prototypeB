package com.example.prototypea;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;

public class player_2x2_grid extends AppCompatActivity {
    SharedPreferences sp, sm;
    private static final int CAMERA_REQUEST = 1888;
    private static final int VIDEO_REQUSET = 1333;
    private static final int MAP_REQUEST=1222;
    ImageButton bg1x1;
    ImageButton bg1x2;
    ImageButton bg2x1;
    ImageButton bg2x2;
    String object;
    String type;
    SharedPreferences.Editor smEditor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player2x2_grid);
        sp = getSharedPreferences("Grid_assigments", Context.MODE_PRIVATE);
        sm = getSharedPreferences("completion", Context.MODE_PRIVATE);
        smEditor=sm.edit();
        bg1x1 = findViewById(R.id.complate_1x1);
        bg1x2 = findViewById(R.id.complate_1x2);
        bg2x1 = findViewById(R.id.complate_2x1);
        bg2x2 = findViewById(R.id.complate_2x2);
        setimage();
        bg1x1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                object = "bg1x1";
                complete();
            }
        });
        bg2x1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                object = "bg2x1";
                complete();
            }
        });
        bg1x2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                object = "bg1x2";
                complete();
            }
        });
        bg2x2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                object = "bg2x2";
                complete();
            }
        });
    }

    private void setimage() {
        bg1x1.setBackgroundResource(sp.getInt("bg1x1", 0));
        bg2x1.setBackgroundResource(sp.getInt("bg2x1", 0));
        bg1x2.setBackgroundResource(sp.getInt("bg1x2", 0));
        bg2x2.setBackgroundResource(sp.getInt("bg2x2", 0));
    }

    private void complete() {
        String object_type = object + "type";
        type = sp.getString(object_type, "test");
        if (type.equals("photo")) {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent, CAMERA_REQUEST);
        }
        if (type.equals("gps")) {
            Intent intent = new Intent(this, get_location.class);
            startActivityForResult(intent, MAP_REQUEST);
        }
        if (type.equals("video")) {
            Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
            startActivityForResult(intent, VIDEO_REQUSET);
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String answer="0";
        if (resultCode == RESULT_OK) {
            if (requestCode == CAMERA_REQUEST) {
                //String imageStr = sp.getString("photo_change", "");
                //Bitmap image = StringToBitMap(imageStr);
                //BitmapDrawable ob = new BitmapDrawable(getResources(), image);
                //object="bg1x1";
                answer=BitMapToString((Bitmap)(data.getExtras().get("data")));

            }
            if (requestCode==VIDEO_REQUSET){
                answer=data.getData().toString();
            }
            if(requestCode==MAP_REQUEST){

            }
        }
        smEditor.putString(object+"answer",answer);
        smEditor.commit();
    }
    public String BitMapToString(@NonNull Bitmap bitmap){
        ByteArrayOutputStream baos=new  ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100, baos);
        byte [] b=baos.toByteArray();
        String temp= Base64.encodeToString(b, Base64.DEFAULT);
        return temp;
    }
}