package org.example.model;

public class Szek {
    int id;
    private int sorSzam;
    private int oszlopSzam;

    public Szek() {
    }

    public Szek(int sorSzam, int oszlopSzam) {
        this.sorSzam = sorSzam;
        this.oszlopSzam = oszlopSzam;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSorSzam() {
        return sorSzam;
    }

    public void setSorSzam(int sorSzam) {
        this.sorSzam = sorSzam;
    }

    public int getOszlopSzam() {
        return oszlopSzam;
    }

    public void setOszlopSzam(int oszlopSzam) {
        this.oszlopSzam = oszlopSzam;
    }


}
