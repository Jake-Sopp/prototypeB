package com.example.prototypea;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.io.ByteArrayOutputStream;

public class image_viewer extends Activity {
    SharedPreferences sp,ans,sm;
    SharedPreferences.Editor speditor,anseditor;
    ImageView image_view;
    Button complete;
    final int SELECT_PICTURE = 10;
    String player,object;
    TextView photo_des;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_viewer);
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        getWindow().setLayout((int) (width * .8), (int) (height * .6));
        Bundle bundle = getIntent().getExtras();
        object=bundle.getString("key");
        image_view=findViewById(R.id.imageView);
        sm = getSharedPreferences("grid_lable", Context.MODE_PRIVATE);
        sp = getSharedPreferences("assigned_values", Context.MODE_PRIVATE);
        player="p1";
        photo_des=findViewById(R.id.image_description);
        ans = getSharedPreferences(player+"answer",Context.MODE_PRIVATE);
        String description=sm.getString(object+"lable_View","empt");
        photo_des.setText(description);
        String imagestr=sm.getString(object+"image","empt");
        if (imagestr=="empt"){
            imagestr=sp.getString(object+"assignment","empt");
        }
        Bitmap image=StringToBitMap(imagestr);
        speditor=sp.edit();
        anseditor=ans.edit();
        image_view.setImageBitmap(image);
        complete = findViewById(R.id.complate_photo);
        complete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(i,SELECT_PICTURE);
            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == SELECT_PICTURE) {
                Bitmap image = (Bitmap) data.getExtras().get("data");
                String convered_image = BitMapToString(image);
                anseditor.putString(player+"answer",convered_image);
                anseditor.commit();
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
    public Bitmap StringToBitMap(String encodedString){
        try{
            byte [] encodeByte = Base64.decode(encodedString,Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            return bitmap;
        }
        catch(Exception e){
            e.getMessage();
            return null;
        }
    }
}