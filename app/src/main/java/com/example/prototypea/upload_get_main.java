package com.example.prototypea;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.Executor;

public class upload_get_main {
    public static String video_upload(Uri videouri/*, String name, String location, String store, FirebaseFirestore db*/, FirebaseStorage df) {
        //auto uploads to storage
        String path = UUID.randomUUID() + ".mp4";
        StorageReference upref = df.getReference(path);
        UploadTask uploadTask = upref.putFile(videouri);
        //uploadTask.addOnCompleteListener(this, new OnCompleteListener<UploadTask.TaskSnapshot>() {
        // @Override
        //public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
        //DocumentReference docRef = db.collection(location).document(name);
        //docRef.update(store, path);
        //Toast.makeText(MainActivity.this,"success",Toast.LENGTH_SHORT).show();
        //}
        //});
        return path;
    }

    public static String image_upload(Bitmap image/*,String name,String location,String store, FirebaseFirestore db*/, FirebaseStorage df) {
        //auto uploads to storage
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.PNG,100,outputStream);
        byte[] image_data = outputStream.toByteArray();
        String path = UUID.randomUUID() + ".png";
        StorageReference upref = df.getReference(path);
        UploadTask uploadTask = upref.putBytes(image_data);
        //uploadTask.addOnCompleteListener(MainActivity.this, new OnCompleteListener<UploadTask.TaskSnapshot>() {
        //  @Override
        //public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
        //DocumentReference docRef = db.collection(location).document(name);
        //docRef.update(store, path);
        //  Toast.makeText(MainActivity.this, "success", Toast.LENGTH_SHORT).show();
        return path;
    }
    //});
    public static void storage_image_get(DocumentSnapshot document, ImageView image,FirebaseStorage df,String path) {
        StorageReference ref = df.getReference(document.getString(path));
        try {
            File tempfile = File.createTempFile("temp", ".png");
            ref.getFile(tempfile).addOnCompleteListener(new OnCompleteListener<FileDownloadTask.TaskSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<FileDownloadTask.TaskSnapshot> task) {
                    Bitmap map = BitmapFactory.decodeFile(tempfile.getAbsolutePath());
                    image.setImageBitmap(map);
                }
            }).addOnFailureListener(e -> {

            });
        } catch (IOException e) {
            e.printStackTrace();

        }
    }
    public static void storage_video_get(DocumentSnapshot document, VideoView videoView,FirebaseStorage df,String path) {
        StorageReference ref = df.getReference(document.getString(path));
        try {
            File tempfile = File.createTempFile("temp", ".mp4");
            ref.getFile(tempfile).addOnCompleteListener(new OnCompleteListener<FileDownloadTask.TaskSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<FileDownloadTask.TaskSnapshot> task) {
                    Uri video = Uri.parse(tempfile.getAbsolutePath());
                    videoView.setVideoURI(video);
                    videoView.start();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
