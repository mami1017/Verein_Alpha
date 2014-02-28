package de.juehvtech.verein.container;

import android.os.Bundle;

import com.thoughtworks.xstream.XStream;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class AttendanceSheetContainer extends Container {
    private int appointmentKey;
    private int trainingKey;
    private int trainerKey;
    private TrainingContentContainer trainingContent;

    private List<Person> attendants;

    public AttendanceSheetContainer(int appointmentKey, int trainingKey, int trainerKey,
                                    List<Person> attendants) {
        this.appointmentKey = appointmentKey;
        this.trainingKey = trainingKey;
        this.trainerKey = trainerKey;
        this.attendants = attendants;
        if (attendants == null) {
            this.attendants = new ArrayList<Person>();
        }
    }

    public AttendanceSheetContainer(int appointmentKey, int trainingKey, int trainerKey) {
        this(appointmentKey, trainingKey, trainerKey, new ArrayList<Person>());
    }

    //TODO change -1 to constant
    public AttendanceSheetContainer(int appointmentKey, int trainingKey) {
        this(appointmentKey, trainingKey, -1, new ArrayList<Person>());
    }

    public int getAppointmentKey() {
        return appointmentKey;
    }

    public void setAppointmentKey(int appointmentKey) {
        this.appointmentKey = appointmentKey;
    }

    public int getTrainingKey() {
        return trainingKey;
    }

    public void setTrainingKey(int trainingKey) {
        this.trainingKey = trainingKey;
    }

    public int getTrainerKey() {
        return trainerKey;
    }

    public void setTrainerKey(int trainerKey) {
        this.trainerKey = trainerKey;
    }

    public TrainingContentContainer getTrainingContent() {
        return trainingContent;
    }

    public void setTrainingContent(TrainingContentContainer trainingContent) {
        this.trainingContent = trainingContent;
    }

    public void addAttendant(Person attendant) {
        attendants.add(attendant);
    }

    public List<Person> getAttendants() {
        return attendants;
    }

    public ArrayList<String> getAttendantsAsNames() {
        ArrayList<String> names = new ArrayList<String>();
        for (Person item : attendants) {
            names.add(item.getName());
        }
        return names;
    }

    public String toXML() {
        XStream xml = new XStream();
        return xml.toXML(this);
    }

//    @Override
//    public ContainerType getContainerType() {
//        return ContainerType.ATTANDENCE_SHEET;
//    }
//
//    @Override
//    public String getToastString() {
//        // TODO Auto-generated method stub
//        return null;
//    }
//
//    @Override
//    public Bundle toBundle() {
//        Bundle retval = new Bundle();
//        retval.putString("key", this.getContainerType().toString());
//        retval.putSerializable("content", this);
//        return retval;
//    }


}
