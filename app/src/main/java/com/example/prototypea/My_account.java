package com.example.prototypea;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class My_account extends AppCompatActivity {
    Button back,delete_Account,password_change_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account);
        back=findViewById(R.id.account_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        delete_Account=findViewById(R.id.delete_Account);
        delete_Account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(My_account.this,Account_deletion.class);
                startActivity(i);
            }
        });
        password_change_button=findViewById(R.id.change_password);
        password_change_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(My_account.this,Password_change.class);
                startActivity(i);
            }
        });
    }
}