package com.example.rasporedaktivnosti2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class AdapterDogadaja extends ArrayAdapter<Dogadaj>
{
    public AdapterDogadaja(@NonNull Context context, List<Dogadaj> events)
    {
        super(context, 0, events);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {
        Dogadaj event = getItem(position);

        if (convertView == null)
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.celija_dogadaja, parent, false);

        TextView celijaDogadajaTV = convertView.findViewById(R.id.celijaDogadajaTV);

        String eventTitle = event.getName() +" "+ KalendarUtils.formattedTime(event.getTime());
        celijaDogadajaTV.setText(eventTitle);
        return convertView;
    }
}