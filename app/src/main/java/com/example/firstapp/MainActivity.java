package com.example.firstapp;

import android.Manifest;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, TimePickerDialog.OnTimeSetListener, DatePickerDialog.OnDateSetListener {
    static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
    private static final int ITEM_ID_ALERT_DIALOG = 10;
    private static final int ITEM_ID_TIME_PICKER = 11;
    private static final int ITEM_ID_DATE_PICKER = 12;
    private static final int ITEM_ID_SERVICE_EXAMPLE = 13;
    private static final int ITEM_ID_NOTIFICATION_EXAMPLE = 14;
    private static final int ITEM_ID_RECYCLE_VIEW = 15;
    private static final int ITEM_ID_WEB_VIEW = 16;
    private static final int ITEM_ID_CITY_VIEW = 17;
    private static final int ITEM_ID_MAPS = 18;
    private static final int REQUEST_CODE_PERMISSION_LOCATION = 25;
    // Data
    Calendar calendar;
    private Button btValidate, btCancel;
    private EditText editText;
    private RadioGroup rgLikeDislike;
    private RadioButton rbLike, rbDislike;
    private ImageView imageView;
    private Drawable doneDrawable, deleteForeverDrawable;
    private TimePickerDialog timePickerDialog;
    private DatePickerDialog datePickerDialog;
    private AlertDialog.Builder alertDialogBuilder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.w("TAG_", "Here is your tag !");

        calendar = Calendar.getInstance();
        timePickerDialog = new TimePickerDialog(this, this, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true);
        datePickerDialog = new DatePickerDialog(this, this, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Welcome !")
                .setIcon(R.mipmap.ic_launcher_round)
                .setMessage("Click on the beautiful button and see what happens !")
                .setPositiveButton("OK !", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, "You did it !", Toast.LENGTH_SHORT).show();
                    }
                });

        btValidate = findViewById(R.id.btValidate);
        btCancel = findViewById(R.id.btCancel);
        editText = findViewById(R.id.editText);
        rgLikeDislike = findViewById(R.id.rgLikeDislike);
        rbLike = findViewById(R.id.rbLike);
        rbDislike = findViewById(R.id.rbDislike);
        imageView = findViewById(R.id.imageView);

        Resources res = getResources();
        doneDrawable = ResourcesCompat.getDrawable(res, R.drawable.done_web, null);
        deleteForeverDrawable = ResourcesCompat.getDrawable(res, R.drawable.delete_forever_web, null);

        btValidate.setOnClickListener(this);

        Intent intent = getIntent();
        String passedText = intent.getStringExtra("back");
        editText.setText(passedText);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, ITEM_ID_ALERT_DIALOG, 0, "Alert Dialog");
        menu.add(0, ITEM_ID_TIME_PICKER, 0, "Time Picker").setIcon(R.mipmap.time_picker).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        menu.add(0, ITEM_ID_DATE_PICKER, 0, "Date Picker").setIcon(R.mipmap.date_picker).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        menu.add(0, ITEM_ID_SERVICE_EXAMPLE, 0, "Service Example");
        menu.add(0, ITEM_ID_NOTIFICATION_EXAMPLE, 0, "Notification Example");
        menu.add(0, ITEM_ID_RECYCLE_VIEW, 0, "Eleve List");
        menu.add(0, ITEM_ID_WEB_VIEW, 0, "Web View");
        menu.add(0, ITEM_ID_CITY_VIEW, 0, "City View");
        menu.add(0, ITEM_ID_MAPS, 0, "Maps");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case ITEM_ID_ALERT_DIALOG:
                alertDialogBuilder.show();
                break;
            case ITEM_ID_TIME_PICKER:
                timePickerDialog.show();
                break;
            case ITEM_ID_DATE_PICKER:
                datePickerDialog.show();
                break;
            case ITEM_ID_SERVICE_EXAMPLE:
                accessServiceActivity();
                break;
            case ITEM_ID_NOTIFICATION_EXAMPLE:
                goToNotificationActivity();
                break;
            case ITEM_ID_RECYCLE_VIEW:
                gotToRecyclerViewEx();
                break;
            case ITEM_ID_WEB_VIEW:
                goToWebViewEx();
                break;
            case ITEM_ID_CITY_VIEW:
                goToCityView();
                break;
            case ITEM_ID_MAPS:
                goToMap();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == REQUEST_CODE_PERMISSION_LOCATION) {
            if (checkLocationPermission()) {
                goToServiceExActivity();
            } else {
                Toast.makeText(this, "Location permission is required to access this part of the app.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public boolean checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            return true;
        }
        return false;
    }

    public void accessServiceActivity() {
        if (checkLocationPermission()) {
            goToServiceExActivity();
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE_PERMISSION_LOCATION);
        }
    }

    public void goToServiceExActivity() {
        Intent intent = new Intent(this, ServiceExActivity.class);
        startActivity(intent);
    }

    private void goToNotificationActivity() {
        Intent intent = new Intent(this, NotificationExampleActivity.class);
        startActivity(intent);
    }

    private void gotToRecyclerViewEx() {
        Intent intent = new Intent(this, RecyclerViewExActivity.class);
        startActivity(intent);
    }

    private void goToWebViewEx() {
        Intent intent = new Intent(this, WebExActivity.class);
        startActivity(intent);
    }

    private void goToCityView() {
        Intent intent = new Intent(this, CityActivity.class);
        startActivity(intent);
    }

    private void goToMap() {
        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
        calendar.set(Calendar.MINUTE, minute);
        String time = simpleDateFormat.format(calendar.getTime());
        String toShow = "L'heure sélectionnée est " + time;
        Toast.makeText(this, toShow, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        String date = simpleDateFormat.format(calendar.getTime());
        String dateToShow = "La date sélectionnée est " + date;

        Toast.makeText(this, dateToShow, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        if (rbLike.isChecked()) {
            editText.setText(rbLike.getText());
        } else if (rbDislike.isChecked()) {
            editText.setText(rbDislike.getText());
        }
        imageView.setImageDrawable(doneDrawable);
        imageView.setColorFilter(Color.GREEN);
    }

    public void onBtCancelClick(View view) {
        editText.setText("");
        rgLikeDislike.clearCheck();
        imageView.setImageDrawable(deleteForeverDrawable);
        imageView.setColorFilter(Color.RED);
    }

    public void onBtNextClick(View view) {
        Intent intent = new Intent(this, SecondActivity.class);
        intent.putExtra("liked", editText.getText().toString());
        startActivity(intent);
        finish();
    }

}
