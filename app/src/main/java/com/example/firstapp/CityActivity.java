package com.example.firstapp;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class CityActivity extends AppCompatActivity {

    private List<CityBean> cityList;
    private CityAdapter adapter;
    private CityAsyncTask cityAsyncTask;

    private EditText etCodePostal;
    private Button btCodePostal;
    private RecyclerView rvCity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city);

        etCodePostal = findViewById(R.id.etCodePostal);
        btCodePostal = findViewById(R.id.btCodePostal);
        rvCity = findViewById(R.id.rvCity);

        cityList = new ArrayList<>();
        adapter = new CityAdapter(cityList);
        rvCity.setAdapter(adapter);
        rvCity.setLayoutManager(new LinearLayoutManager(this));
    }

    public void onBtSearchCPClick(View view) {
        if (cityAsyncTask == null || cityAsyncTask.getStatus() == AsyncTask.Status.FINISHED) {
            cityAsyncTask = new CityAsyncTask();
            cityAsyncTask.execute();
        }
    }

    public class CityAsyncTask extends AsyncTask {

        private List<CityBean> result;
        private Exception exception;

        @Override
        protected Object doInBackground(Object[] objects) {
            try {
                result = WSUtils.getCities(etCodePostal.getText().toString());
            } catch (Exception e) {
                e.printStackTrace();
                exception = e;
            }
            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            if (exception != null) {
                Toast.makeText(CityActivity.this, "Erreur : " + exception.getMessage(), Toast.LENGTH_SHORT).show();
            } else {
                cityList.clear();
                cityList.addAll(result);
                adapter.notifyDataSetChanged();
            }
        }
    }
}
