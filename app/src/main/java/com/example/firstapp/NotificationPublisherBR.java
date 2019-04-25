package com.example.firstapp;

import android.app.Notification;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;

public class NotificationPublisherBR extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        if ("android.intent.action.BOOT_COMPLETED".equals(intent.getAction())) {
            Log.w("TAG_notif", "After boot tag");
            NotificationUtils.createInstantNotification(context, "After boot notif !");

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                context.startForegroundService(new Intent(context, NotificationService.class));
            } else {
                context.startService(new Intent(context, NotificationService.class));
            }

        } else {
            Notification notification = intent.getParcelableExtra("example");

            Log.w("TAG_notif", "Received by the broadcaster !");

            NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context);
            notificationManagerCompat.notify(29, notification);
        }


    }
}
