package com.example.firstapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class NotificationExampleActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_example);
    }

    public void onBtNotifNowClick(View view) {
        NotificationUtils.createInstantNotification(this, "This is right now !");
    }

    public void onBtNotifIn15secClick(View view) {
        NotificationUtils.scheduleNotification(this, "This is later !", 1500);
    }
}
