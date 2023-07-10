package com.example.rasporedaktivnosti2;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.time.ZoneId;

import androidx.appcompat.app.AppCompatActivity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import android.annotation.SuppressLint;

import android.widget.EditText;
import android.widget.TextView;

import android.app.TimePickerDialog;

public class UrediDogadaj extends AppCompatActivity {
    private EditText nazivDogadajaID;
    private TextView datumDogadajaID, vrijemeDogadajaID;

    private LocalTime time;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uredi_dogadaj);
        initWidgets();

        time = LocalTime.now();
        datumDogadajaID.setText("Date : " + KalendarUtils.formattedDate(KalendarUtils.selectedDate));
        vrijemeDogadajaID.setText("Time: " + KalendarUtils.formattedTime(time));
    }

    private void initWidgets() {
        datumDogadajaID = findViewById(R.id.datumDogadajaID);
        vrijemeDogadajaID = findViewById(R.id.vrijemeDogadajaID);
    }

    public void spremiDogadaj(View view) {
        EditText nazivDogadajaID = findViewById(R.id.nazivDogadajaID);
        String eventName = nazivDogadajaID.getText().toString();

        Dogadaj newEvent = new Dogadaj(eventName, KalendarUtils.selectedDate, time);
        Dogadaj.eventsList.add(newEvent);
        finish();

    }

    public void selectVrijeme(View view) {
        @SuppressLint("SetTextI18n") TimePickerDialog.OnTimeSetListener timeSetListener = (timePickerView, hourOfDay, minute) -> {
            time = LocalTime.of(hourOfDay, minute);
            vrijemeDogadajaID.setText("Time: " + KalendarUtils.formattedTime(time));
        };

        TimePickerDialog timePickerDialog = new TimePickerDialog(
                UrediDogadaj.this,
                timeSetListener,
                time.getHour(),
                time.getMinute(),
                true
        );
        timePickerDialog.show();
    }
}
