package com.example.firstapp;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class NotificationService extends Service {
    @Override
    public void onCreate() {
        super.onCreate();

        NotificationUtils.createInstantNotification(this, "After boot notification !");
//        startForeground(1, NotificationUtils.createInstantNotification(this, "Hello"));
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
