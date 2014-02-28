package de.juehvtech.verein.container;

/**
 * Created by Jens on 17.08.13.
 */
public class Trainer {
    private int id;
    private String name;

    public Trainer(int id, String name) {
        this.id = id;
        this.name = name;
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
}
