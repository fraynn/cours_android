package com.example.firstapp;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.squareup.otto.Subscribe;

public class ServiceExActivity extends AppCompatActivity implements View.OnClickListener {

    private Button buttonStart;
    private Button buttonStop;
    private TextView tvService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_ex);
        buttonStart = findViewById(R.id.buttonStart);
        buttonStop = findViewById(R.id.buttonStop);
        tvService = findViewById(R.id.tvService);
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

    @Subscribe
    public void showLocation(Location location) {
        Double latitude = location.getLatitude();
        Double longitude = location.getLongitude();
        String toDisplay = latitude.toString() + "\n" + longitude.toString();
        tvService.setText(toDisplay);
    }

    @Override
    public void onClick(View v) {
        startService(new Intent(this, LocationService.class));
    }

    public void onBtStopClick(View v) {
        stopService(new Intent(this, LocationService.class));
    }
}
