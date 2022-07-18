package com.example.prototypea;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class admin_2x2_grid extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences sp = getSharedPreferences("grid_update", Context.MODE_PRIVATE);
        setContentView(R.layout.activity_admin2x2_grid);
        ImageButton admin1x1=findViewById(R.id.admingrid_1x1);
        ImageButton admin2x1=findViewById(R.id.admingrid_2x1);
        ImageButton admin1x2=findViewById(R.id.admingrid_1x2);
        ImageButton admin2x2=findViewById(R.id.admingrid_2x2);
        test(sp,admin1x1,admin2x1,admin1x2,admin2x2);
        Button playerlist1 = findViewById(R.id.playerlist_1);
        String value="test";
        playerlist1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(admin_2x2_grid.this, grid_player_select.class);
                i.putExtra("key",value);
                startActivityForResult(i,1);
            }
        });
        }
    protected void test(SharedPreferences sp,ImageButton admin1x1,ImageButton admin1x2,ImageButton admin2x1,ImageButton admin2x2){
        int image1x1=sp.getInt("1x1",0);
        int image2x1=sp.getInt("2x1",0);
        int image1x2=sp.getInt("1x2",0);
        int image2x2=sp.getInt("2x2",0);
        admin1x1.setImageResource(image1x1);
        admin2x1.setImageResource(image2x1);
        admin1x2.setImageResource(image1x2);
        admin2x2.setImageResource(image2x2);
    }
}