package de.juehvtech.verein.container;

import android.util.Pair;

import java.util.HashMap;
import java.util.Map;

public class Event {
    private String name;
    private int key;
    private Trainer trainer;
    private SimpleTrainingAppointment appointment;
    private Person attendants;

    public Event(String name, int key, Trainer trainer, SimpleTrainingAppointment appointment, Person attendants) {
        this.name = name;
        this.key = key;
        this.trainer = trainer;
        this.appointment = appointment;
        this.attendants = attendants;
    }

    public Event(String name, int key) {
        this.name = name;
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public Trainer getTrainer() {
        return trainer;
    }

    public void setTrainer(Trainer trainer) {
        this.trainer = trainer;
    }

    public SimpleTrainingAppointment getAppointment() {
        return appointment;
    }

    public void setAppointment(SimpleTrainingAppointment appointment) {
        this.appointment = appointment;
    }

    public Person getAttendants() {
        return attendants;
    }

    public void setAttendants(Person attendants) {
        this.attendants = attendants;
    }
}
