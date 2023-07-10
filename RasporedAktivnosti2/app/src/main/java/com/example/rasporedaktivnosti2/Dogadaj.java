package com.example.rasporedaktivnosti2;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class Dogadaj
{
    public static ArrayList<Dogadaj> eventsList = new ArrayList<>();

    public static ArrayList<Dogadaj> eventsForDate(LocalDate date)
    {
        ArrayList<Dogadaj> events = new ArrayList<>();

        for(Dogadaj event : eventsList)
        {
            if(event.getDate().equals(date))
                events.add(event);
        }

        return events;
    }
    public static ArrayList<Dogadaj> eventsForDateAndTime(LocalDate date, LocalTime time)
    {
        ArrayList<Dogadaj> events = new ArrayList<>();

        for(Dogadaj event : eventsList)
        {
            int dogadajSat = event.time.getHour();
            int celijaSat = time.getHour();
            if(event.getDate().equals(date) && dogadajSat == celijaSat)
                events.add(event);
        }

        return events;
    }


    private String name;
    private LocalDate date;
    private LocalTime time;

    public Dogadaj(String name, LocalDate date, LocalTime time)
    {
        this.name = name;
        this.date = date;
        this.time = time;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public LocalDate getDate()
    {
        return date;
    }

    public void setDate(LocalDate date)
    {
        this.date = date;
    }

    public LocalTime getTime()
    {
        return time;
    }

    public void setTime(LocalTime time)
    {
        this.time = time;
    }
}