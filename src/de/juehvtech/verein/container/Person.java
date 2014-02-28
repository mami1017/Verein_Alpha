package de.juehvtech.verein.container;

import android.os.Bundle;

@SuppressWarnings("serial")
public class Person extends Container {

    private int id;
    private String name;
    private boolean isVisitor;

    public Person(int id, String name, boolean visitor) {
        this.id = id;
        this.name = name;
        isVisitor = visitor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isVisitor() {
        return isVisitor;
    }

    public void setVisitor(boolean visitor) {
        isVisitor = visitor;
    }
}
