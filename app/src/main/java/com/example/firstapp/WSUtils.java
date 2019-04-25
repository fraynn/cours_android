package com.example.firstapp;

import android.os.SystemClock;

import com.google.gson.Gson;

import java.io.InputStreamReader;
import java.util.List;

import okhttp3.ResponseBody;

public class WSUtils {
    private final static String BASE_CITY_URL = "http://www.citysearch-api.com/fr/city";
    private final static String API_LOGIN = "login=webserviceexemple";
    private final static String API_KEY = "apikey=sof940dd26cf107eabf8bf6827f87c3ca8e8d82546";
    private static Gson gson = new Gson();


    public static EleveBean loadEleveFromWeb() throws Exception {
        SystemClock.sleep(3000);
        return new EleveBean("Toto", "Aze");
    }

    public static List<CityBean> getCities(String cp) throws Exception {

        ResponseBody responseBody = OkHttpUtils.sendGetOkHttpRequest(BASE_CITY_URL + "?" + API_LOGIN + "&" + API_KEY + "&cp=" + cp);
        InputStreamReader isr = new InputStreamReader(responseBody.byteStream());
        CPResultBean result = gson.fromJson(isr, CPResultBean.class);

        if (result.getResults() == null) {
            throw new Exception(result.getError().getMessage());
        }
        return result.getResults();
    }

}
