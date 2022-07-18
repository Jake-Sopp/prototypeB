package com.example.prototypea;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class newgrid_2x2 extends AppCompatActivity {
    int result;
    ImageButton con;
    ImageButton bg1x1;
    ImageButton bg2x1;
    ImageButton bg1x2;
    ImageButton bg2x2;
    String type;
    SharedPreferences sp;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sp = getSharedPreferences("Grid_assigments", Context.MODE_PRIVATE);
        editor = sp.edit();
        editor.clear();
        editor.apply();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newgrid2x2);
        bg1x1 =findViewById(R.id.g1x1);
        bg1x1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changebutton(bg1x1);
            }
        });
        bg2x1 =findViewById(R.id.g2x1);
        bg2x1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                changebutton(bg2x1);
            }
        });
        bg1x2 =findViewById(R.id.g1x2);
        bg1x2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                changebutton(bg1x2);
            }
        });
        bg2x2 =findViewById(R.id.g2x2);
        bg2x2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                changebutton(bg2x2);
            }
        });
        Button admin=findViewById(R.id.admin_grid);
        admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(newgrid_2x2.this, grid_2x2_asignments.class);
                startActivity(intent);
            }
        });
    }
    public void changebutton(@NonNull ImageButton change_button){
        con=change_button;
        int value=con.getId();
        Intent i = new Intent(newgrid_2x2.this, newgridoption.class);
        i.putExtra("key",value);
        startActivityForResult(i,1);
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String txt = null;
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                int result = data.getIntExtra("result",0);
                String type;
                type = data.getStringExtra("type");
                con.setImageResource(result);
                if (con==bg1x1){
                    txt="bg1x1";
                }
                if (con==bg2x1){
                    txt="bg2x1";
                }
                if (con==bg1x2){
                    txt="bg1x2";
                }
                if (con==bg2x2){
                    txt="bg2x2";
                }
                String txttype=txt+"type";

                editor.putInt(txt,result);
                editor.putString(txttype,type);
                editor.commit();
            }
            if (resultCode == RESULT_CANCELED) {
            }
        }
    }
}
