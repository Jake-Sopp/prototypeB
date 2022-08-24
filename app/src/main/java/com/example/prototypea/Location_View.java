package com.example.prototypea;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.prototypea.databinding.ActivityLocationViewBinding;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.protobuf.StringValue;

public class Location_View extends FragmentActivity implements OnMapReadyCallback {
    private FusedLocationProviderClient client;
    private GoogleMap mMap;
    private ActivityLocationViewBinding binding;
    String answer, player;
    SharedPreferences sm, ans, lable;
    SharedPreferences.Editor smEditor, ansEditor;
    String object;
    //Location location;
    double longitude = 0;
    double latitude = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLocationViewBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        client = LocationServices.getFusedLocationProviderClient(Location_View.this);
        mMap = googleMap;
        lable = getSharedPreferences("grid_lable", Context.MODE_PRIVATE);
        sm = getSharedPreferences("assigned_values", Context.MODE_PRIVATE);
        ans = getSharedPreferences(player + "answer", Context.MODE_PRIVATE);
        ansEditor = ans.edit();
        Bundle bundle = getIntent().getExtras();
        object = bundle.getString("key");
        Button setloc = findViewById(R.id.location_answer);
        String value = sm.getString(object + "assignment", "empt");
        if (value != "empt") {
            String[] latlong = value.split(",");
            latitude=Double.parseDouble(latlong[0]);
            longitude=Double.parseDouble(latlong[1]);

        } else {

        }
        location_trigger();
        Bitmap location_icon = StringToBitMap(lable.getString(object + "image", null));
        LatLng assigned_location = new LatLng(latitude, longitude);
        mMap.addMarker(new MarkerOptions().position(assigned_location).title(lable.getString(object + "lable_View", "example location"))/*.icon(BitmapDescriptorFactory.fromBitmap(location_icon))*/);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(assigned_location,15));
        Button refresh = findViewById(R.id.location_answer_refresh);
        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                location_trigger();
            }
        });
        setloc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ActivityCompat.checkSelfPermission(Location_View.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(Location_View.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                client.getLastLocation().addOnSuccessListener(Location_View.this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        if (location != null) {
                            Double lat = location.getLatitude();
                            Double longi = location.getLongitude();
                            answer = String.valueOf(lat) + String.valueOf(longi);
                            ansEditor.putString(player + "answer", answer);
                            ansEditor.commit();
                            finish();
                        }
                    }
                });
            }
        });
    }

    public Bitmap StringToBitMap(String encodedString) {
        try {
            byte[] encodeByte = Base64.decode(encodedString, Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            return bitmap;
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }

    private void location_trigger() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        client.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null) {
                    double lat_use = location.getLatitude();
                    double long_use = location.getLongitude();
                    LatLng start = new LatLng(lat_use, long_use);
                    mMap.addMarker(new MarkerOptions().position(start).title("your location"));
                }
            }
        });
    }
}
//TODO add text view to see description/transfer get inital location code and show original marker/add refresh button/modify complation button
