package com.example.firstapp;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewExActivity extends AppCompatActivity implements OnEleveClickListener {

    private List<EleveBean> eleveList;
    private Button btAdd;
    private RecyclerView rvEleve;
    private EleveAdapter adapter;
    private ProgressBar progressBar;
    private LoadEleveAsyncTask loadEleveAsyncTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_ex);
        btAdd = findViewById(R.id.btAdd);
        rvEleve = findViewById(R.id.rvEleve);
        progressBar = findViewById(R.id.progressBar);

        eleveList = new ArrayList<>();
        adapter = new EleveAdapter(eleveList);
        adapter.setEleveClickListener(this);
        rvEleve.setAdapter(adapter);
        rvEleve.setLayoutManager(new LinearLayoutManager(this));
    }

    public void onBtAddClick(View view) {
        eleveList.add(0, new EleveBean("Number_" + eleveList.size(), "Toto"));
        adapter.notifyItemInserted(0);
    }

    public void onBtLoadClick(View view) {
        if (loadEleveAsyncTask == null || loadEleveAsyncTask.getStatus() == AsyncTask.Status.FINISHED) {
            loadEleveAsyncTask = new LoadEleveAsyncTask();
            loadEleveAsyncTask.execute();
        }
    }

    @Override
    public void onEleveClick(EleveBean eleve, int elevePosition) {
        eleveList.remove(eleve);
        eleveList.add(0, eleve);
        adapter.notifyItemMoved(elevePosition, 0);
    }

    @Override
    public void onLongEleveClick(EleveBean eleve, int elevePosition) {
        eleveList.remove(eleve);
        adapter.notifyItemRemoved(elevePosition);
    }

    public class LoadEleveAsyncTask extends AsyncTask {

        private EleveBean result;
        private Exception exception;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected Object doInBackground(Object[] objects) {
            try {
                result = WSUtils.loadEleveFromWeb();
            } catch (Exception e) {
                e.printStackTrace();
                exception = e;
            }
            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            if (exception != null) {
                Toast.makeText(RecyclerViewExActivity.this, "Erreur : " + exception.getMessage(), Toast.LENGTH_SHORT).show();
            } else {
                eleveList.add(0, result);
                adapter.notifyItemInserted(0);
                progressBar.setVisibility(View.GONE);
            }
        }
    }
}
