package de.juehvtech.verein.container;

import android.content.Context;

import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;

import de.juehvtech.verein.R;

public class SimpleTrainingAppointment implements Serializable {
    private Day day;
    private int time; // in minutes
    private int duration; // in minutes

    public SimpleTrainingAppointment(Day day, int time, int duration) {
        this.day = day;
        this.time = time;
        this.duration = duration;
    }

    public Day getDay() {
        return day;
    }

    public void setDay(Day day) {
        this.day = day;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}
