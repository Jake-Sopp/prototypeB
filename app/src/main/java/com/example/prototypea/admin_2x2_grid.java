package com.example.prototypea;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class admin_2x2_grid extends Activity {
    ImageButton admin1x1,admin2x2,admin1x2,admin2x1;
    Button playerlist;
    SharedPreferences sp;
    String value;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sp = getSharedPreferences("Grid_assigments", Context.MODE_PRIVATE);
        setContentView(R.layout.activity_admin2x2_grid);
        admin1x1=findViewById(R.id.admingrid_1x1);
        admin2x1=findViewById(R.id.admingrid_2x1);
        admin1x2=findViewById(R.id.admingrid_1x2);
        admin2x2=findViewById(R.id.admingrid_2x2);
        update();
        playerlist = findViewById(R.id.playerlist_1);
        playerlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(admin_2x2_grid.this, grid_player_select.class);
                i.putExtra("key",value);
                startActivityForResult(i,1);
            }
        });
        }
    protected void update (){
        admin1x1.setImageResource(sp.getInt("bg1x1",0));
        admin2x1.setImageResource(sp.getInt("bg2x1",0));
        admin1x2.setImageResource(sp.getInt("bg1x2",0));
        admin2x2.setImageResource(sp.getInt("bg2x2",0));
    }
}