package com.example.rasporedaktivnosti2;

import java.time.LocalTime;
import java.util.ArrayList;

public class SatiDogadaj {
    LocalTime time;
    ArrayList<Dogadaj> events;

    public SatiDogadaj(LocalTime time, ArrayList<Dogadaj> events) {
        this.time = time;
        this.events = events;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public ArrayList<Dogadaj> getEvents() {
        return events;
    }

    public void setEvents(ArrayList<Dogadaj> events) {
        this.events = events;
    }
}
