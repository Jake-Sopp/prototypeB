package com.example.prototypea;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import org.w3c.dom.Text;

public class grid_2x2_asignments extends AppCompatActivity {
    TextView test;
    String object;
    ImageButton asignment1x1;
    ImageButton asignment2x1;
    ImageButton asignment1x2;
    ImageButton asignment2x2;
    String type;
    SharedPreferences sp,sm,im;
    SharedPreferences.Editor smeditor,imageeditor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid2x2_asignments);
        sp = getSharedPreferences("Grid_assigments", Context.MODE_PRIVATE);
        sm = getSharedPreferences("assigned_values", Context.MODE_PRIVATE);
        im=getSharedPreferences("grid_image",Context.MODE_PRIVATE);
        smeditor=sm.edit();
        imageeditor=im.edit();
        asignment1x1 = findViewById(R.id.asigment1x1);
        asignment2x1 = findViewById(R.id.asigment2x1);
        asignment1x2 = findViewById(R.id.asginment1x2);
        asignment2x2 = findViewById(R.id.asigment2x2);
        test=findViewById(R.id.select_values);
        imagestart();
        asignment1x1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                object="bg1x1";
                assigments();
            }
        });
        asignment2x1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                object="bg2x1";
                assigments();
            }
        });
        asignment1x2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                object="bg1x2";
                assigments();
            }
        });
        asignment2x2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                object="bg2x2";
                assigments();
            }
        });
    }


    protected void imagestart() {
        asignment1x1.setBackgroundResource(sp.getInt("bg1x1", 0));
        asignment2x1.setBackgroundResource(sp.getInt("bg2x1", 0));
        asignment1x2.setBackgroundResource(sp.getInt("bg1x2", 0));
        asignment2x2.setBackgroundResource(sp.getInt("bg2x2", 0));
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode==1) {
                String value = sp.getString("photo_change", "");
                Bitmap image = StringToBitMap(value);
                BitmapDrawable ob = new BitmapDrawable(getResources(), image);
                //object="bg1x1";
                if (object == "bg1x1") {
                    asignment1x1.setBackground(ob);
                }
                if (object == "bg2x1") {
                    asignment2x1.setBackground(ob);
                }
                if (object == "bg1x2") {
                    asignment1x2.setBackground(ob);
                }
                if (object == "bg2x2") {
                    asignment2x2.setBackground(ob);
                }
                smeditor.putString(object+"item",value);
                imageeditor.putString(object+"image",value);
            }
            if (requestCode==2){

            }
            if(requestCode==3){
                test.setText("video");
            }
            smeditor.commit();
            imageeditor.commit();
        }
    }
    private void assigments() {
        String object_type = object+"type";
        TextView test=findViewById(R.id.select_values);
        type=sp.getString(object_type,"test");
        smeditor.putString(object_type,type);
        if (type.equals("photo")){
            Intent i = new Intent(this, assigment_photo.class);
            i.putExtra("key", object);
            startActivityForResult(i, 1);
        }
        if (type.equals("gps")){
            Intent i = new Intent(this, get_location.class);
            i.putExtra("key", object);
            startActivityForResult(i, 2);
        }
        if (type.equals("video")){
            Intent i = new Intent(this, video_assinment.class);
            i.putExtra("key", object);
            startActivityForResult(i, 3);
        }
    }
    public Bitmap StringToBitMap(String encodedString) {
        try {
            byte[] decodedString = Base64.decode(encodedString, Base64.DEFAULT);
            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
            return decodedByte;
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }
}