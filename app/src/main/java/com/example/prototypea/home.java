package com.example.prototypea;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;

public class home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        requestPermission();
        Button Create_grid_button = (Button) findViewById(R.id.create_grids);
        Create_grid_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(home.this, create_grids_start.class);
                startActivity(intent);
            }
        });
        Button Account_button = (Button) findViewById(R.id.my_account);
        Account_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(home.this, My_account.class);
                startActivity(intent);
            }
        });
        Button most_recent = (Button) findViewById(R.id.recent_grids);
        most_recent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(home.this, grid_compleation.class);
                startActivity(intent);
            }
        });
        Button view_grids = (Button) findViewById(R.id.view_grids);
        view_grids.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(home.this, view_grids_page.class);
                startActivity(intent);
            }
        });
    }
    private void requestPermission() {
        ActivityCompat.requestPermissions(this, new String[]{ACCESS_FINE_LOCATION}, 1);
        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},1);
    }
}