package com.example.rasporedaktivnosti2;

import static com.example.rasporedaktivnosti2.KalendarUtils.selectedDate;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.time.LocalTime;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class DnevniPregled extends AppCompatActivity
{
    private TextView mjesecDanText;
    private TextView dayOfWeekTV;
    private ListView satiListView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dnevni_pregled);
        initwidgets();
    }

    private void initwidgets()
    {
        mjesecDanText = findViewById(R.id.mjesecDanText);
        dayOfWeekTV = findViewById(R.id.dayOfWeekTV);
        satiListView = findViewById(R.id.satiListView);
    }

    @Override
    protected void onResume() {
        super.onResume();
        setDnevniPregled();
    }

    private void setDnevniPregled()
    {
        mjesecDanText.setText(KalendarUtils.monthDayFromDate(selectedDate));
        String dayOfWeek = selectedDate.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.getDefault());
        dayOfWeekTV.setText(dayOfWeek);
        setAdapterSati();
    }

    private void setAdapterSati()
    {
        AdapterSati adapterSati = new AdapterSati(getApplicationContext(), satiDogadajList());
        satiListView.setAdapter(adapterSati);
    }

    private ArrayList<SatiDogadaj> satiDogadajList()
    {
        ArrayList<SatiDogadaj> list = new ArrayList<>();
        for (int hour = 0; hour < 24; hour++)
        {
            LocalTime time = LocalTime.of(hour, 0);
            ArrayList<Dogadaj> dogadajs = Dogadaj.eventsForDateAndTime(selectedDate, time);
            SatiDogadaj satiDogadaj = new SatiDogadaj(time, dogadajs);
            list.add(satiDogadaj);

        }
        return list;
    }

    public void PreviousDan(View view)
    {
        KalendarUtils.selectedDate = KalendarUtils.selectedDate.minusDays(1);
        setDnevniPregled();
    }

    public void NextDan(View view)
    {
        KalendarUtils.selectedDate = KalendarUtils.selectedDate.plusDays(1);
        setDnevniPregled();
    }

    public void newEventAction(View view)
    {
        startActivity(new Intent(this, UrediDogadaj.class));
    }
}