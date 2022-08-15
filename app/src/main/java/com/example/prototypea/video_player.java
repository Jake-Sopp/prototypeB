package com.example.prototypea;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.VideoView;

public class video_player extends Activity {
    VideoView video;
    SharedPreferences sm,ans,sp;
    SharedPreferences.Editor smEditor;
    String player,object,description;
    Button start,stop,back,complate;
    int SELECT_PICTURE = 200;
    Uri videoUri,newuri;
    TextView video_desc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_player);
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height=dm.heightPixels;
        getWindow().setLayout((int)(width*.9),(int)(height*.8));
        Bundle bundle = getIntent().getExtras();
        object=bundle.getString("key");
        sm = getSharedPreferences("assigned_values", Context.MODE_PRIVATE);
        sp=getSharedPreferences("grid_lable",Context.MODE_PRIVATE);
        video_desc=findViewById(R.id.videoplayer_description);
        video=findViewById(R.id.video_player);
        start=findViewById(R.id.video_start);
        stop=findViewById(R.id.video_restart);
        back=findViewById(R.id.video_back);
        complate=findViewById(R.id.complate_video);
        description=sp.getString(object+"lable_View","empt");
        video_desc.setText(description);
        String stringuri=sm.getString(object+"assignment","empt");
        newuri=Uri.parse(stringuri);
        video.setVideoURI(newuri);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                video.start();
            }
        });
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                video.pause();
            }
        });
        complate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
                startActivity(i);
            }
        });
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == SELECT_PICTURE) {
                videoUri = data.getData();
                if (null != videoUri) {
                    smEditor.putString(player+"answer", String.valueOf(videoUri));
                    smEditor.commit();
                    finish();
                }
            }
        }
    }
}
//TODO FIX VIDEO not playing
