package com.example.prototypea;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.location.Location;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.ImageButton;

import java.io.ByteArrayOutputStream;

public class player_2x2_grid extends AppCompatActivity {
    SharedPreferences sp, sm;
    private FusedLocationProviderClient client;
    private static final int CAMERA_REQUEST = 1888;
    private static final int VIDEO_REQUEST = 1333;
    private static final int MAP_REQUEST = 1222;
    ImageButton bg1x1;
    ImageButton bg1x2;
    ImageButton bg2x1;
    ImageButton bg2x2;
    String object, type, player;
    SharedPreferences.Editor smEditor;
    String answer = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player2x2_grid);
        client = LocationServices.getFusedLocationProviderClient(this);

        player = "p1";
        sp = getSharedPreferences("Grid_assigments", Context.MODE_PRIVATE);
        sm = getSharedPreferences("completion", Context.MODE_PRIVATE);
        smEditor = sm.edit();
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
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            client.getLastLocation()
                    .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                        @Override
                        public void onSuccess(Location location) {
                            if (location != null) {
                                String locat = location.toString();
                                String lat = locat.substring(15,24);
                                String longi = locat.substring(25,34);
                                answer = lat+" "+longi;
                                smEditor.putString(player+object+"answer",answer);
                                smEditor.commit();
                            }
                        }
                    });

        }
        if (type.equals("video")) {
            Intent intent = new Intent(this, video_player.class);
            startActivityForResult(intent, VIDEO_REQUEST);
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == CAMERA_REQUEST) {
                answer=BitMapToString((Bitmap)(data.getExtras().get("data")));
            }
            if (requestCode== VIDEO_REQUEST){
                answer=data.getData().toString();
            }
            if(requestCode==MAP_REQUEST){

            }
        }
        smEditor.putString(player+object+"answer",answer);
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
//do you pledge obliagance to the bees