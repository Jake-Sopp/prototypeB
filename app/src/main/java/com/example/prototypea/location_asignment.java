package com.example.prototypea;

import android.Manifest;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import com.example.prototypea.databinding.ActivityGetLocationBinding;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;

public class location_asignment extends FragmentActivity implements OnMapReadyCallback {
    SharedPreferences sm;
    SharedPreferences.Editor smeditor;
    private GoogleMap mMap;
    private ActivityGetLocationBinding binding;
    LatLng location_transfer;
    String object;
    Button confirm;
    LatLng start;
    Location location;
    double lat_use, longi_use;
    private FusedLocationProviderClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        client = LocationServices.getFusedLocationProviderClient(this);

        binding = ActivityGetLocationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        confirm = findViewById(R.id.map_select);
        sm = getSharedPreferences("assigned_values", Context.MODE_PRIVATE);
        smeditor = sm.edit();
        object = getIntent().getStringExtra("key");
        // Add a marker in Sydney and move the camera
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
        client.getLastLocation()
                .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {

                        if (location != null) {
                            lat_use=location.getLatitude();
                            longi_use=location.getLongitude();
                            start=new LatLng(lat_use,longi_use);
                            smeditor.putString(object+"assignment", String.valueOf(lat_use)+","+String.valueOf(longi_use));
                            mMap.addMarker(new MarkerOptions().position(start).title("this is your location"));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(start,15));
                        }
                   }
                });
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(@NonNull LatLng latLng) {
                location_transfer=latLng;
                mMap.clear();
                mMap.addMarker(new MarkerOptions().position(latLng));
                String longa= String.valueOf(location_transfer.longitude);
                String lata=String.valueOf(location_transfer.latitude);
                smeditor.putString(object+"assignment",lata+","+longa);
            }
        });
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                smeditor.commit();
                setResult(RESULT_OK);
                finish();
            }
        });
    }
}