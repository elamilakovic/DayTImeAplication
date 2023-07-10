package com.example.rasporedaktivnosti2;

import static com.example.rasporedaktivnosti2.KalendarUtils.daysInMonthArray;
import static com.example.rasporedaktivnosti2.KalendarUtils.monthYearFromDate;

import android.app.PendingIntent;
import android.content.Intent;

import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.time.LocalDate;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterKalendara.OnItemListener {
    private TextView monthYearText;
    private RecyclerView calendarRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initWidgets();
        KalendarUtils.selectedDate = LocalDate.now();
        setMonthView();

    }

    private void initWidgets() {
        calendarRecyclerView = findViewById(R.id.calendarRecyclerView);
        monthYearText = findViewById(R.id.mjesecGodinaID);
    }

    private void setMonthView() {
        monthYearText.setText(monthYearFromDate(KalendarUtils.selectedDate));
        ArrayList<LocalDate> daysInMonth = daysInMonthArray();

        AdapterKalendara calendarAdapter = new AdapterKalendara(daysInMonth, this);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 7);
        calendarRecyclerView.setLayoutManager(layoutManager);
        calendarRecyclerView.setAdapter(calendarAdapter);
    }

    public void PreviousMjesec(View view) {
        KalendarUtils.selectedDate = KalendarUtils.selectedDate.minusMonths(1);
        setMonthView();
    }

    public void NextMjesec(View view) {
        KalendarUtils.selectedDate = KalendarUtils.selectedDate.plusMonths(1);
        setMonthView();
    }

    @Override
    public void onItemClick(int position, LocalDate date) {
        if (date != null) {
            KalendarUtils.selectedDate = date;
            setMonthView();
        }
    }

    public void weeklyAction(View view) {
        startActivity(new Intent(this, PregledTjedna.class));
    }
    
}







