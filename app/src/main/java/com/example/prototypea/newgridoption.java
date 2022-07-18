package com.example.prototypea;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;

public class newgridoption extends Activity {
    int value;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        Bundle extras = getIntent().getExtras();
        if (extras != null) value = extras.getInt("key");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newgridselection);
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height=dm.heightPixels;
        getWindow().setLayout((int)(width*.8),(int)(height*.6));



        Button photo =findViewById(R.id.photo_config);
        photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                changebutton("photo");
            }
        });
        Button video = findViewById(R.id.video_config);
        video.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                changebutton("video");
            }
        });
        Button gps = findViewById(R.id.gps_config);
        gps.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                changebutton("gps");
            }
        });
    }
    public void changebutton(String option){
        Intent returnIntent = new Intent();
        if (option=="photo"){
            returnIntent.putExtra("result", R.drawable.camera);
            returnIntent.putExtra("type", "photo");
        }
        else if (option=="video"){
            returnIntent.putExtra("result", R.drawable.video_camera);
            returnIntent.putExtra("type", "video");
        }
        else if (option=="gps"){
            returnIntent.putExtra("result", R.drawable.earth);
            returnIntent.putExtra("type", "gps");
        }
        setResult(RESULT_OK,returnIntent);
        finish();

    }
}
