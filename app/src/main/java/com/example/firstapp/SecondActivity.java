package com.example.firstapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tvSecond;
    private Button btBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        tvSecond = findViewById(R.id.tvSecond);
        btBack = findViewById(R.id.btBack);

        Intent intent = getIntent();
        String passedText = intent.getStringExtra("liked");
        tvSecond.setText(passedText);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("back", "Back from second page");
        startActivity(intent);
        finish();
    }
}
