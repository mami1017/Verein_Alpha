package de.juehvtech.verein.database;

import com.thoughtworks.xstream.XStream;

import java.util.Date;

import de.juehvtech.verein.container.Container;
import de.juehvtech.verein.container.ContainerType;

@Deprecated
public class DatabaseEntry {
    private Date date;
    private ContainerType type;
    private Container content;

    public DatabaseEntry(Date date, ContainerType type, Container content) {
        this.date = date;
        this.type = type;
        this.content = content;
    }

    public String toXML() {
        XStream xml = new XStream();
        return xml.toXML(this);
    }

    public Container getContent() {
        return content;
    }

    public void setContent(Container content) {
        this.content = content;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public ContainerType getType() {
        return type;
    }

    public void setType(ContainerType type) {
        this.type = type;
    }
}
