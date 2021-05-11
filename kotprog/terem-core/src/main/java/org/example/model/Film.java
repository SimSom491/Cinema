package org.example.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Film {
    IntegerProperty id = new SimpleIntegerProperty(this, "id");
    StringProperty cim = new SimpleStringProperty(this, "cim");
    IntegerProperty hossz = new SimpleIntegerProperty(this, "hossz");
    IntegerProperty korhatar = new SimpleIntegerProperty(this, "korhatar");
    StringProperty rendezo = new SimpleStringProperty(this, "rendezo");
    StringProperty szereplok = new SimpleStringProperty(this, "szereplok");
    StringProperty leiras = new SimpleStringProperty(this, "leiras");
    StringProperty kep = new SimpleStringProperty(this, "kep");
    StringProperty klip = new SimpleStringProperty(this, "klip");

    public Film(IntegerProperty id, StringProperty cim, IntegerProperty hossz, IntegerProperty korhatar, StringProperty rendezo, StringProperty szereplok, StringProperty leiras, StringProperty kep, StringProperty klip) {
        this.id = id;
        this.cim = cim;
        this.hossz = hossz;
        this.korhatar = korhatar;
        this.rendezo = rendezo;
        this.szereplok = szereplok;
        this.leiras = leiras;
        this.kep = kep;
        this.klip = klip;
    }

    public Film() {
    }

    public Film(IntegerProperty id, IntegerProperty hossz, IntegerProperty korhatar, StringProperty rendezo, StringProperty szereplok, StringProperty leiras, StringProperty kep, StringProperty klip) {
        this.id = id;
        this.hossz = hossz;
        this.korhatar = korhatar;
        this.rendezo = rendezo;
        this.szereplok = szereplok;
        this.leiras = leiras;
        this.kep = kep;
        this.klip = klip;
    }

    public String getCim() {
        return cim.get();
    }

    public void setCim(String cim) {
        this.cim.set(cim);
    }

    public StringProperty cimProperty() {
        return cim;
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

    public int getHossz() {
        return hossz.get();
    }

    public void setHossz(int hossz) {
        this.hossz.set(hossz);
    }

    public IntegerProperty hosszProperty() {
        return hossz;
    }

    public int getKorhatar() {
        return korhatar.get();
    }

    public void setKorhatar(int korhatar) {
        this.korhatar.set(korhatar);
    }

    public IntegerProperty korhatarProperty() {
        return korhatar;
    }

    public String getRendezo() {
        return rendezo.get();
    }

    public void setRendezo(String rendezo) {
        this.rendezo.set(rendezo);
    }

    public StringProperty rendezoProperty() {
        return rendezo;
    }

    public String getSzereplok() {
        return szereplok.get();
    }

    public void setSzereplok(String szereplok) {
        this.szereplok.set(szereplok);
    }

    public StringProperty szereplokProperty() {
        return szereplok;
    }

    public String getLeiras() {
        return leiras.get();
    }

    public void setLeiras(String leiras) {
        this.leiras.set(leiras);
    }

    public StringProperty leirasProperty() {
        return leiras;
    }

    public String getKep() {
        return kep.get();
    }

    public void setKep(String kep) {
        this.kep.set(kep);
    }

    public StringProperty kepProperty() {
        return kep;
    }

    public String getKlip() {
        return klip.get();
    }

    public void setKlip(String klip) {
        this.klip.set(klip);
    }

    public StringProperty klipProperty() {
        return klip;
    }

    @Override
    public String toString() {
        return cim.getValue();
    }
}
