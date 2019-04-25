package com.example.firstapp;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class WebExActivity extends AppCompatActivity {

    private Button button;
    private EditText editTextWeb;
    private WebView webView;
    private TextView tvWeb;
    private WebAsyncTask webAsyncTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_ex);
        button = findViewById(R.id.button);
        editTextWeb = findViewById(R.id.editTextWeb);
        webView = findViewById(R.id.webView);
        tvWeb = findViewById(R.id.tvWeb);

        webView.setWebViewClient(new WebViewClient());
        WebSettings webViewSettings = webView.getSettings();
        webViewSettings.setJavaScriptEnabled(true);
    }

    public void onBtWebClick(View view) {
        Toast.makeText(this, "Url button clicked !", Toast.LENGTH_SHORT).show();
        webView.loadUrl(editTextWeb.getText().toString());

        if (webAsyncTask == null || webAsyncTask.getStatus() == AsyncTask.Status.FINISHED) {
            webAsyncTask = new WebAsyncTask();
            webAsyncTask.execute();
        }
    }

    public class WebAsyncTask extends AsyncTask {

        private String result;
        private Exception exception;

        @Override
        protected Object doInBackground(Object[] objects) {
            try {
                result = OkHttpUtils.sendGetOkHttpRequest(editTextWeb.getText().toString()).string();
            } catch (Exception e) {
                e.printStackTrace();
                exception = e;
            }
            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            if (exception != null) {
                Toast.makeText(WebExActivity.this, "Erreur : " + exception.getMessage(), Toast.LENGTH_SHORT).show();
            } else {
                Log.w("TAG_Async", result);
                tvWeb.setText(result);
            }
        }
    }
}
