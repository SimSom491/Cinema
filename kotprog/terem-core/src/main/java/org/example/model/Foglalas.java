package org.example.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Foglalas {
    IntegerProperty id = new SimpleIntegerProperty();
    StringProperty foglaloNev = new SimpleStringProperty();
    IntegerProperty vetitesId = new SimpleIntegerProperty();
    IntegerProperty szekId = new SimpleIntegerProperty();


    public Foglalas(int id, String foglaloNev, int vetitesId, int szekId) {
        setId(id);
        setFoglaloNev(foglaloNev);
        setVetitesId(vetitesId);
        setSzekId(szekId);
    }

    public Foglalas() {
    }

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getFoglaloNev() {
        return foglaloNev.get();
    }

    public StringProperty foglaloNevProperty() {
        return foglaloNev;
    }

    public void setFoglaloNev(String foglaloNev) {
        this.foglaloNev.set(foglaloNev);
    }

    public int getVetitesId() {
        return vetitesId.get();
    }

    public IntegerProperty vetitesIdProperty() {
        return vetitesId;
    }

    public void setVetitesId(int vetitesId) {
        this.vetitesId.set(vetitesId);
    }

    public int getSzekId() {
        return szekId.get();
    }

    public IntegerProperty szekIdProperty() {
        return szekId;
    }

    public void setSzekId(int szekId) {
        this.szekId.set(szekId);
    }
}


