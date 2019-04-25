package com.example.firstapp;

import android.util.Log;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class OkHttpUtils {

    public static ResponseBody sendGetOkHttpRequest(String url) throws Exception {
        Log.w("TAG_web", "URL : " + url);
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder().url(url).build();
        Response response = client.newCall(request).execute();

        if (response.code() < 200 || response.code() >= 300) {
            throw new Exception("Réponse du serveur incorrect : " + response.code());
        } else {
            //Résultat de la requete.
            // ATTENTION .string() ne peut être appelée qu’une seule fois.
            return response.body();
        }
    }
}
