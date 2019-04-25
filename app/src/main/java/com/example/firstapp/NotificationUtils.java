package com.example.firstapp;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.SystemClock;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;

public class NotificationUtils {

    private static final String CHANNEL_ID = "NotificationChannel";
    private static final CharSequence CHANNEL_NAME = "Commandes";

    /**
     * Création du channel
     */
    private static void initChannel(Context c) {
        // Uniquement à partir d’Oreo
        if (Build.VERSION.SDK_INT < 26) {
            return;
        }

        NotificationManager nm = (NotificationManager) c.getSystemService(Context.NOTIFICATION_SERVICE);
        // Réglage du channel
        NotificationChannel channel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH);
        channel.setDescription("Commandes");
        channel.enableLights(true); // Lumière
        channel.enableVibration(true); // Vibration
        channel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
        // Création du channel
        nm.createNotificationChannel(channel);
    }

    public static void createInstantNotification(Context c, String message) {
        initChannel(c);

        // When click on notification
        Intent intent = new Intent(c, NotificationExampleActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(c, 0, intent, PendingIntent.FLAG_ONE_SHOT);

        // Notification
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(c, CHANNEL_ID);
        notificationBuilder.setSmallIcon(R.mipmap.done)
                .setContentTitle("Hello !")
                .setContentText(message)
                .setContentIntent(pendingIntent)
                .setPriority(NotificationManager.IMPORTANCE_HIGH)
                .setDefaults(Notification.DEFAULT_ALL);

        // Send it
        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(c);
        notificationManagerCompat.notify(29, notificationBuilder.build());
    }

    public static void scheduleNotification(Context c, String message, long delay) {
        initChannel(c);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(c, CHANNEL_ID);
        builder.setContentTitle("I'm late !")
                .setContentText(message)
                .setSmallIcon(R.mipmap.done);

        // Redirection towards broadcast
        Intent intent = new Intent(c, NotificationPublisherBR.class);
        intent.putExtra("example", builder.build());
        PendingIntent pendingIntent = PendingIntent.getBroadcast(c, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        // In the future
        long futureInMs = SystemClock.elapsedRealtime() + delay;
        AlarmManager alarmManager = (AlarmManager) c.getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, futureInMs, pendingIntent);
    }


}
