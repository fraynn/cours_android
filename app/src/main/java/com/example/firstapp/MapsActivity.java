package com.example.firstapp;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.squareup.otto.Subscribe;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Button btGetPosition;

    private LatLng currentLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        btGetPosition = findViewById(R.id.btGetPosition);

        btGetPosition.setEnabled(false);
        currentLocation = null;
        startService(new Intent(this, LocationService.class));
    }

    @Override
    protected void onStart() {
        super.onStart();
        MyApplication.getBus().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        MyApplication.getBus().unregister(this);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng toulouse = new LatLng(43.59999, 1.43333);
        mMap.addMarker(new MarkerOptions().position(toulouse).title("You are here !"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(toulouse, 12));
    }

    @Subscribe
    public void setCurrentLocation(Location location) {
        Double latitude = location.getLatitude();
        Double longitude = location.getLongitude();
        currentLocation = new LatLng(latitude, longitude);
        btGetPosition.setEnabled(true);
    }


    public void onBtGetPositionClick(View view) {
        mMap.clear();
        mMap.addMarker(new MarkerOptions().position(currentLocation).title("Real here"));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLocation, 13));
    }
}
