package com.example.prototypea;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.net.URI;

public class grid_2x2_asignments extends AppCompatActivity {
    TextView test;
    String object,type;
    ImageButton asignment1x1,asignment2x1,asignment1x2,asignment2x2;
    SharedPreferences sp,sm,im;
    SharedPreferences.Editor smeditor,imageeditor;
    Button next;
    Switch lable_switch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid2x2_asignments);
        test=findViewById(R.id.select_values);
        sp = getSharedPreferences("Grid_assigments", Context.MODE_PRIVATE);
        sm = getSharedPreferences("assigned_values", Context.MODE_PRIVATE);
        im=getSharedPreferences("grid_image",Context.MODE_PRIVATE);
        smeditor=sm.edit();
        imageeditor=im.edit();
        asignment1x1 = findViewById(R.id.asigment1x1);
        asignment2x1 = findViewById(R.id.asigment2x1);
        asignment1x2 = findViewById(R.id.asginment1x2);
        asignment2x2 = findViewById(R.id.asigment2x2);
        next=findViewById(R.id.assigment_finish);
        lable_switch=findViewById(R.id.icon_switch);
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
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(grid_2x2_asignments.this,grid_compleation.class);
                startActivity(i);
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
            if (requestCode==2 || requestCode==3){
                Intent j = new Intent(grid_2x2_asignments.this,custom_icon.class);
                j.putExtra("key",object);
                startActivityForResult(j,10);
            }
            smeditor.commit();
            imageeditor.commit();
        }
    }
    private void assigments() {
        String object_type = object+"type";
        //TextView test=findViewById(R.id.select_values);
        type=sp.getString(object_type,"test");
        smeditor.putString(object_type,type);
        if (lable_switch.isChecked()==true){
            Intent j = new Intent(this,custom_icon.class);
            j.putExtra("key",object);
            startActivityForResult(j,10);
        }
        else {
            if (type.equals("photo")) {
                Intent i = new Intent(this, assigment_photo.class);
                i.putExtra("key", object);
                startActivityForResult(i, 1);
            }
            if (type.equals("gps")) {
                Intent i = new Intent(this, location_asignment.class);
                i.putExtra("key", object);
                startActivityForResult(i, 2);
            }
            if (type.equals("video")) {
                Intent i = new Intent(this, video_assinment.class);
                i.putExtra("key", object);
                startActivityForResult(i, 3);
            }
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
