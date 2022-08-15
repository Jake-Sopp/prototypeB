package com.example.prototypea;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Account_deletion extends AppCompatActivity {
    Button yes,no;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_deletion);
        yes=findViewById(R.id.delete_yes);
        no=findViewById(R.id.delete_no);
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO add user deletion command
                Intent i = new Intent(Account_deletion.this, account_deleteion_sucess.class);
                startActivity(i);
            }
        });
    }
}