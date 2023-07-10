package com.example.rasporedaktivnosti2;

import static com.example.rasporedaktivnosti2.KalendarUtils.daysInWeekArray;
import static com.example.rasporedaktivnosti2.KalendarUtils.monthYearFromDate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.time.LocalDate;
import java.util.ArrayList;

public class PregledTjedna extends AppCompatActivity implements AdapterKalendara.OnItemListener
{
    private TextView monthYearText;
    private RecyclerView calendarRecyclerView;
    private ListView eventListView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pregled_tjedna);
        initWidgets();
        setWeekView();
    }

    private void initWidgets()
    {
        calendarRecyclerView = findViewById(R.id.calendarRecyclerView);
        monthYearText = findViewById(R.id.mjesecGodinaID);
        eventListView = findViewById(R.id.eventListView);
    }

    private void setWeekView()
    {
        monthYearText.setText(monthYearFromDate(KalendarUtils.selectedDate));
        ArrayList<LocalDate> days = daysInWeekArray(KalendarUtils.selectedDate);

        AdapterKalendara AdapterKalendara = new AdapterKalendara(days, this);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 7);
        calendarRecyclerView.setLayoutManager(layoutManager);
        calendarRecyclerView.setAdapter(AdapterKalendara);
        setEventAdpater();
    }


    public void prosliTjedan(View view)
    {
        KalendarUtils.selectedDate = KalendarUtils.selectedDate.minusWeeks(1);
        setWeekView();
    }

    public void iduciTjedan(View view)
    {
        KalendarUtils.selectedDate = KalendarUtils.selectedDate.plusWeeks(1);
        setWeekView();
    }

    @Override
    public void onItemClick(int position, LocalDate date)
    {
        KalendarUtils.selectedDate = date;
        setWeekView();
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        setEventAdpater();
    }

    private void setEventAdpater()
    {
        ArrayList<Dogadaj> dailyEvents = Dogadaj.eventsForDate(KalendarUtils.selectedDate);
        AdapterDogadaja eventAdapter = new AdapterDogadaja(getApplicationContext(), dailyEvents);
        eventListView.setAdapter(eventAdapter);
    }

    public void newEventAction(View view)
    {
        startActivity(new Intent(this, UrediDogadaj.class));
    }

    public void DailyAction(View view)
    {
        startActivity(new Intent(this, DnevniPregled.class));
    }
}