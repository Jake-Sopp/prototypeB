package com.example.prototypea;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.Map;

public class grid_compleation extends AppCompatActivity {
    int size = 6;
    TextView view;
    String[] sizearray;
    String code, grid_name, grid_size, type, value;
    Button complate, friends;
    FirebaseFirestore db;
    SharedPreferences sp, sm, im;
    SharedPreferences.Editor spEditor;
    FirebaseStorage df;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_compleation);
        grid_name = "test";
        db = FirebaseFirestore.getInstance();
        df = FirebaseStorage.getInstance();
        sp = getSharedPreferences("Grid_assigments", Context.MODE_PRIVATE);
        sm = getSharedPreferences("assigned_values", Context.MODE_PRIVATE);
        im = getSharedPreferences("grid_image", Context.MODE_PRIVATE);
        view = findViewById(R.id.random_code);
        code = String.valueOf(string_gen());
        //spEditor.putString("code",code);
        //spEditor.commit();
        //create_database();
        complate = findViewById(R.id.complate_code);
        complate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                create_database();
                //Intent intent = new Intent(getApplicationContext(), home.class);
                //intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                //startActivity(intent);
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

    public void create_database() {
        int j = sizecreate("2x2");
        for (int i = 0; i < j; ) {
            String arrayvalue = sizearray[i];
            type = sp.getString(arrayvalue + "type", "test");
            if (type.equals("photo")) {
                Bitmap image_upload = StringToBitMap(sm.getString(arrayvalue + "assignment", "null"));
                value = upload_get_main.image_upload(image_upload, df);
            }
            if (type.equals("video")) {
                Uri video = Uri.parse(sm.getString(arrayvalue + "assignment", "null"));
                value = upload_get_main.video_upload(video, df);
            }
            if (type.equals("gps")) {
                value = sm.getString(arrayvalue + "assignment", "null");
            }
            /*Map<String, Object> docData = new HashMap<>();
            docData.put("method", sp.getString(arrayvalue + "type", "null"));
            docData.put("value", value);
            db.collection("/grids/" + grid_name + "/values+method/")
                    //.document(//sizearray[i]"temp")
                    //.set(docData)
                    /*.addOnSuccessListener(new OnSuccessListener() {
                        @Override
                        public void onSuccess(Object o) {
                            Toast.makeText(grid_compleation.this, "document has being uploaded", Toast.LENGTH_LONG).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(grid_compleation.this, "Error adding document", Toast.LENGTH_LONG).show();
                        }
                    })*/;
        }
    }

    public Bitmap StringToBitMap(String image) {
        try {
            byte[] encodeByte = Base64.decode(image, Base64.DEFAULT);

            InputStream inputStream = new ByteArrayInputStream(encodeByte);
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            return bitmap;
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }

    private int sizecreate(String grid_size){
        int j=0;
        if (grid_size.equals("2x2")) {
             sizearray = new String[]{"bg1x1", "bg1x2", "bg2x1", "bg2x2"};
            j = 4;
        }
        if (grid_size.equals("2x3")) {
            sizearray = new String[]{"bg1x1", "bg1x2", "bg1x3", "bg2x1", "bg2x2", "bg2x3"};
            j = 6;
        }
        if (grid_size.equals("3x3")) {
            sizearray = new String[]{"bg1x1", "bg1x2", "bg1x3", "bg2x1", "bg2x2", "bg2x3", "bg3x1", "bg3x2", "bg3x3"};
            j = 9;
        }
        return j;
    }
}
/*<!--TODO: use code to create database table with all items from sharedprefrence
        run through each grid square and add them
        get grid details
        merrory and map issue
        all complation and creating pages
        settings page*/