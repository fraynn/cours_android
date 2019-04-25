package com.example.firstapp;

import android.Manifest;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.content.ContextCompat;
import android.util.Log;

public class LocationService extends Service implements LocationListener {

    private LocationManager locationMgr;

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.w("TAG_SERVICE", "I'm alive !");

        //Si on a pas la permission on ne s’abonne pas
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            stopSelf();
            return;
        }

        locationMgr = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (locationMgr.getAllProviders().contains(LocationManager.NETWORK_PROVIDER)) {
            locationMgr.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 5000, 0, this);
        }
        if (locationMgr.getAllProviders().contains(LocationManager.GPS_PROVIDER)) {
            locationMgr.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 0, this);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        locationMgr.removeUpdates(this);
        Log.w("TAG_SERVICE", "I'm dead !");

    }

    @Override
    public void onLocationChanged(Location location) {
//        Double latitude = location.getLatitude();
//        Double longitude = location.getLongitude();

//        Toast.makeText(this, latitude.toString() + "\n" + longitude.toString(), Toast.LENGTH_SHORT).show();
        MyApplication.getBus().post(location);
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
