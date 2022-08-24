package com.example.prototypea;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class grid_compleation extends AppCompatActivity {
    int size = 6;
    TextView view;
    String code;
    Button complate,friends;
    SharedPreferences sp;
    SharedPreferences.Editor spEditor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_compleation);
        sp = getSharedPreferences("Grid_assigments", Context.MODE_PRIVATE);
        view = findViewById(R.id.random_code);
        code=String.valueOf(string_gen());
        spEditor.putString("code",code);
        spEditor.commit();
        complate=findViewById(R.id.complate_code);
        complate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), home.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }

    public StringBuilder string_gen() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++) {
            sb.append((char) (((int) 'A') + Math.random() * 25));
        }
        view.setText(sb);
        return sb;
    }
}
/*<!-- TODO: use code to create database table with all items from sharedprefrence
        sp = getSharedPreferences("Grid_assigments", Context.MODE_PRIVATE);
        sm = getSharedPreferences("assigned_values", Context.MODE_PRIVATE);
        im=getSharedPreferences("grid_image",Context.MODE_PRIVATE);*/