package org.example.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.time.LocalDate;

public class Vetites {
    IntegerProperty id = new SimpleIntegerProperty(this, "id");
    ObjectProperty<Terem> terem = new SimpleObjectProperty<>(this, "terem");
    ObjectProperty<Film> film = new SimpleObjectProperty<>(this, "film");
    ObjectProperty<LocalDate> idopont = new SimpleObjectProperty<>(this, "idopont");
    IntegerProperty ora = new SimpleIntegerProperty(this, "ora");

    public Vetites() {

    }

    public Vetites(IntegerProperty id, ObjectProperty<Terem> terem, ObjectProperty<Film> film, ObjectProperty<LocalDate> idopont) {
        this.id = id;
        this.terem = terem;
        this.film = film;
        this.idopont = idopont;
    }

    public int getOra() {
        return ora.get();
    }

    public void setOra(int ora) {
        this.ora.set(ora);
    }

    public IntegerProperty oraProperty() {
        return ora;
    }

    public int getId() {
        return id.get();
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public Terem getTerem() {
        return terem.get();
    }

    public void setTerem(Terem terem) {
        this.terem.set(terem);
    }

    public ObjectProperty<Terem> teremProperty() {
        return terem;
    }

    public Film getFilm() {
        return film.get();
    }

    public void setFilm(Film film) {
        this.film.set(film);
    }

    public ObjectProperty<Film> filmProperty() {
        return film;
    }

    public LocalDate getIdopont() {
        return idopont.get();
    }

    public void setIdopont(LocalDate idopont) {
        this.idopont.set(idopont);
    }

    public ObjectProperty<LocalDate> idopontProperty() {
        return idopont;
    }
}
