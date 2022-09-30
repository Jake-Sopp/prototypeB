package com.example.prototypea;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Password_change extends AppCompatActivity {
    FirebaseUser user=FirebaseAuth.getInstance().getCurrentUser();
    Button password_Set,back;
    EditText old_pass,new_pass,confirm_pass;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_change);
        old_pass=findViewById(R.id.password_old);
        new_pass=findViewById(R.id.new_password);
        confirm_pass=findViewById(R.id.password_confirm);
        password_Set=findViewById(R.id.password_set);
        password_Set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(confirm_pass.toString().equals(new_pass.toString()));
                user.updatePassword(new_pass.toString()).addOnCompleteListener(task -> Toast.makeText(Password_change.this,"password changed", Toast.LENGTH_LONG).show());
            }
        });
        back=findViewById(R.id.password_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
