package org.example.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Terem {
    private final IntegerProperty id = new SimpleIntegerProperty(this, "id");
    private final IntegerProperty sorok = new SimpleIntegerProperty(this, "sorok");
    private final IntegerProperty oszlopok = new SimpleIntegerProperty(this, "oszlopok");

    public int getId() {
        return id.get();
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public int getSorok() {
        return sorok.get();
    }

    public void setSorok(int sorok) {
        this.sorok.set(sorok);
    }

    public IntegerProperty sorokProperty() {
        return sorok;
    }

    public int getOszlopok() {
        return oszlopok.get();
    }

    public void setOszlopok(int oszlopok) {
        this.oszlopok.set(oszlopok);
    }

    public IntegerProperty oszlopokProperty() {
        return oszlopok;
    }

    @Override
    public String toString() {
        return id.getValue().toString();
    }
}
