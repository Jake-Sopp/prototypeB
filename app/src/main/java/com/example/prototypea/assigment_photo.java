package com.example.prototypea;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class assigment_photo extends Activity {
    private static final int CAMERA_REQUEST=1888;
    ImageView photo;
    SharedPreferences sp,sm;
    int SELECT_PICTURE=100;
    Button photo_get;
    Button gallery_get;
    Uri imageUri;
    String object;
    SharedPreferences.Editor speditor,smeditor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assigment_photo);
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height=dm.heightPixels;
        getWindow().setLayout((int)(width*.8),(int)(height*.6));
        object = getIntent().getStringExtra("key");
        sp = getSharedPreferences("Grid_assigments", Context.MODE_PRIVATE);
        sm = getSharedPreferences("assigned_values", Context.MODE_PRIVATE);
        speditor = sp.edit();
        smeditor=sm.edit();
        photo_get=findViewById(R.id.camara_photo);
        gallery_get=findViewById(R.id.gallary_photo);
        photo=findViewById(R.id.photo_assigment_View);
        photo_get.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(takePictureIntent, CAMERA_REQUEST);
            }
        });
        gallery_get.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int gallery = 1;
                Intent gallereryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                gallereryIntent.setType("image/*");
                gallereryIntent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(gallereryIntent, SELECT_PICTURE);
            }
        });
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //SharedPreferences sp = getSharedPreferences("grid_assignment", Context.MODE_PRIVATE);
        //SharedPreferences.Editor editor = sp.edit();
        if (resultCode == Activity.RESULT_OK) {
            if(requestCode==SELECT_PICTURE){
                imageUri=data.getData();
                Bitmap image = null;
                try {
                    image = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                String convered_image=BitMapToString(image);
                speditor.putString("photo_change", convered_image);
                speditor.commit();
                smeditor.putString(object+"assignment",convered_image);
                smeditor.commit();
                if(null!=imageUri){
                    photo.setImageBitmap(image);
                }
            }
            if (requestCode == CAMERA_REQUEST)
            {
                Bitmap image = (Bitmap) data.getExtras().get("data");
                photo.setImageBitmap(image);
                String convered_image = BitMapToString(image);
                speditor.putString("photo_change",convered_image);
                smeditor.putString(object+"assignment",convered_image);
                smeditor.commit();
                speditor.commit();
            }
        }
        setResult(RESULT_OK);
        finish();
    }
    public String BitMapToString(@NonNull Bitmap bitmap){
        ByteArrayOutputStream baos=new  ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100, baos);
        byte [] b=baos.toByteArray();
        String temp= Base64.encodeToString(b, Base64.DEFAULT);
        return temp;
    }
}