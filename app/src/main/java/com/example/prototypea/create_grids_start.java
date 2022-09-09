package com.example.prototypea;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class create_grids_start extends AppCompatActivity {
    SharedPreferences sp;
    SharedPreferences.Editor speditor;
    EditText description,tags,name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_grids_start);
        name=findViewById(R.id.grid_name_text);
        description=findViewById(R.id.descripton_of_grid);
        tags=findViewById(R.id.grid_tags);
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.Grid_sizes, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        sp=getSharedPreferences("grid_lable", Context.MODE_PRIVATE);
        speditor=sp.edit();
        Button button_back = findViewById(R.id.create_back);
        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        Button Continue = (Button) findViewById(R.id.grid_continue);
        Continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speditor.putString("name",name.toString());
                speditor.putString("size",spinner.toString());
                speditor.putString("description",description.toString());
                speditor.putString("lables",tags.toString());
                speditor.commit();
                //if(spinner.toString().equals("2x2")) {
                    Intent intent = new Intent(create_grids_start.this, newgrid_2x2.class);
                    startActivity(intent);
                /*}
                if(spinner.toString().equals("2x3")) {
                    Intent intent = new Intent(create_grids_start.this, newgrid_2x2.class);
                    startActivity(intent);
                }
                if(spinner.toString().equals("3x3")) {
                    Intent intent = new Intent(create_grids_start.this, newgrid_2x2.class);
                    startActivity(intent);
                }*/
            }
        });
    }
}//add the other types in correct space