package com.example.rasporedaktivnosti2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;


public class AdapterSati extends ArrayAdapter<SatiDogadaj> {
    public AdapterSati(@NonNull Context context, List<SatiDogadaj> SatiDogadajList) {
        super(context, 0, SatiDogadajList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        SatiDogadaj satiDogadaj = getItem(position);

        if (convertView == null)
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.celija_sati, parent, false);

        setHour(convertView, satiDogadaj.getTime());
        setEvents(convertView, satiDogadaj.getEvents());

        return convertView;
    }


    private void setHour(View convertView, LocalTime time) {
        TextView TimeTxtView = convertView.findViewById(R.id.TimeTxtView);
        TimeTxtView.setText(KalendarUtils.formattedShortTime(time));
    }

    private void setEvents(View convertView, ArrayList<Dogadaj> dogadajs) {
        TextView event1 = convertView.findViewById(R.id.event1);
        TextView event2 = convertView.findViewById(R.id.event2);
        TextView event3 = convertView.findViewById(R.id.event3);

        if (dogadajs.size() == 0) {
            hideEvent(event1);
            hideEvent(event2);
            hideEvent(event3);
        }
        else if (dogadajs.size() == 1)
        {
            setEvent(event1, dogadajs.get(0));
            hideEvent(event2);
            hideEvent(event3);

        }
        else if (dogadajs.size() == 2)
        {
            setEvent(event1, dogadajs.get(0));
            setEvent(event2, dogadajs.get(1));
            hideEvent(event3);
        }
        else if (dogadajs.size() == 3)
        {
            setEvent(event1, dogadajs.get(0));
            setEvent(event2, dogadajs.get(1));
            setEvent(event3, dogadajs.get(2));
        }
        else
        {
            setEvent(event1, dogadajs.get(0));
            setEvent(event2, dogadajs.get(1));
            event3.setVisibility(View.VISIBLE);
            String eventsNotShown = String.valueOf(dogadajs.size()-2);
            eventsNotShown += "More Events";
            event3.setText(eventsNotShown);
        }

    }

    private void setEvent(TextView textView, Dogadaj dogadaj)
    {
        textView.setText(dogadaj.getName());
        textView.setVisibility(View.VISIBLE);
    }

    private void hideEvent(TextView tv)
    {
        tv.setVisibility(View.INVISIBLE);
    }


}
