package com.example.prototypea;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class grid_compleation extends AppCompatActivity {
    int size = 6;
    TextView view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_compleation);
        view = findViewById(R.id.complate_code);
        string_gen();
    }

    public void string_gen() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++) {
            sb.append((char) (((int) 'A') + Math.random() * 25));
        }
        view.setText(sb);
    }
}