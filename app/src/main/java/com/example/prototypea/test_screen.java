package com.example.prototypea;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class test_screen extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        int temp = 0;
        super.onCreate(savedInstanceState);
        SharedPreferences sp = getSharedPreferences("testprefrence", Context.MODE_PRIVATE);
        setContentView(R.layout.activity_test_screen);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("Test notification", "my notification", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }
        Spinner spinner = findViewById(R.id.image_select_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.Image_select, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        Button notifcation = findViewById(R.id.notify_button);
        notifcation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notificationcreate("cake");
            }
        });
        Button notification2 = findViewById(R.id.notify_button2);
        notification2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notificationcreate("orange");
            }
        });
        Button save = findViewById(R.id.shared_prefrence_save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text;
                text = spinner.getSelectedItem().toString();
                SharedPreferences.Editor editor = sp.edit();
                int image=image_check(text);
                editor.putInt("id",image);
                editor.commit();

            }
        });
        Button next = findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(test_screen.this, test_screen_2.class);
                startActivity(intent);
            }
        });
    }

    protected void notificationcreate(String choice) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(test_screen.this, "Test notification");
        builder.setContentTitle("text should say " + choice);
        builder.setContentText(choice);
        builder.setSmallIcon(R.drawable.rowlet);
        builder.setAutoCancel(true);
        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(test_screen.this);
        managerCompat.notify(1, builder.build());
    }
    public int image_check(String choice){
        int tempa;
        tempa = 0;

        if (choice=="rowlet"){
            tempa=R.drawable.rowlet;
        }
        if (choice=="door"){
            tempa=R.drawable.door;
        }
        if (choice=="camera"){
            tempa=R.drawable.camera;
        }
        else{
            tempa=R.drawable.earth;
        }
        return tempa;
    }
}