package com.example.prototypea;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;

public class custom_icon extends Activity {
    Button photo,gallery,confirm;
    ImageView imageview;
    String object;
    int gal,camimg;
    SharedPreferences sp;
    SharedPreferences.Editor speditor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_icon);
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height=dm.heightPixels;
        getWindow().setLayout((int)(width*.8),(int)(height*.6));
        Bundle bundle = getIntent().getExtras();
        object=bundle.getString("key");
        //object= getIntent().getStringExtra("key");
        sp=getSharedPreferences("grid_image", Context.MODE_PRIVATE);
        gal=1;
        camimg=2;
        speditor=sp.edit();
        imageview=findViewById(R.id.image);
        confirm=findViewById(R.id.icon_selection_confirm);
        photo=findViewById(R.id.icon_selection_camera);
        gallery=findViewById(R.id.icon_selection_galleary);
        gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gallereryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                gallereryIntent.setType("image/*");
                startActivityForResult(gallereryIntent, gal);
            }
        });
        photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(takePictureIntent, camimg);
            }
        });
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == camimg) {
                Bitmap image = (Bitmap) data.getExtras().get("data");
                imageview.setImageBitmap(image);
                String convered_image = BitMapToString(image);
                speditor.putString(object+"image",convered_image);
                speditor.commit();
            }
        }
    }
    public String BitMapToString(@NonNull Bitmap bitmap){
        ByteArrayOutputStream baos=new  ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100, baos);
        byte [] b=baos.toByteArray();
        String temp= Base64.encodeToString(b, Base64.DEFAULT);
        return temp;
    }
}