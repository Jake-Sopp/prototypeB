package com.example.prototypea;


import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.VideoView;

public class video_assinment extends Activity {
    SharedPreferences sp, sm;
    SharedPreferences.Editor speditor, smeditor;
    String object;
    Button camera, gallery;
    int SELECT_PICTURE = 200;
    Uri Videouri;
    VideoView test;
    private static final int CAMERA_REQUEST = 1888;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_assinment);
        test=findViewById(R.id.videoView);
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        getWindow().setLayout((int) (width * .8), (int) (height * .6));
        object = getIntent().getStringExtra("key");
        sp = getSharedPreferences("Grid_assigments", Context.MODE_PRIVATE);
        sm = getSharedPreferences("assigned_values", Context.MODE_PRIVATE);
        speditor = sp.edit();
        smeditor = sm.edit();
        camera = findViewById(R.id.camera_video);
        gallery = findViewById(R.id.gallery_video);
        gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choosevideofromgllary();
            }
        });
        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent takevideo = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
                startActivityForResult(takevideo, CAMERA_REQUEST);
            }
        });
    }
    public void choosevideofromgllary() {
        Intent gallereryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Video.Media.EXTERNAL_CONTENT_URI);
        gallereryIntent.setType("video/*");
        startActivityForResult(gallereryIntent, SELECT_PICTURE);
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == SELECT_PICTURE) {
                Videouri = data.getData();
                if (null != Videouri) {
                    test.setVideoURI(Videouri);
                    test.start();
                    String uristring=Videouri.toString();
                    smeditor.putString(object+"assignment", uristring);
                    smeditor.commit();
                   }
             }
            if (requestCode == CAMERA_REQUEST) {
                Videouri = data.getData();
                test.setVideoURI(Videouri);
                test.start();
                String uristring=Videouri.toString();
                smeditor.putString(object+"assignment",uristring);
                smeditor.commit();
            }
        }
        setResult(RESULT_OK);
        //finish();
    }
}