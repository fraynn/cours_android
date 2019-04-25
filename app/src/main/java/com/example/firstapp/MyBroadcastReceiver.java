package com.example.firstapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import java.util.Locale;

public class MyBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String localeCountry = Locale.getDefault().getDisplayCountry();
        String localeLanguage = Locale.getDefault().getDisplayLanguage();
        String textToShow = "Locale changed to : " + localeLanguage + " (" + localeCountry + ")";
        Log.w("TAG_LOCALE", textToShow);
        Toast.makeText(context, textToShow, Toast.LENGTH_SHORT).show();
    }
}
