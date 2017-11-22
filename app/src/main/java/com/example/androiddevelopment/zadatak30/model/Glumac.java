package com.example.androiddevelopment.zadatak30.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

import static com.example.androiddevelopment.zadatak30.model.Glumac.DATABASE_TABLE;

/**
 * Created by androiddevelopment on 22.11.17..
 */
@DatabaseTable(tableName = DATABASE_TABLE)
public class Glumac {

    public static final String DATABASE_TABLE="glumac";
    public static final String FIELDS_ID="id";
    public static final String FIELDS_IME="ime";
    public static final String FIELDS_BIOGRAFIJA="biografija";
    public static final String FIELDS_OCENA="ocena";
    public static final String FIELDS_DATUM="datum";
    public static final String FIELDS_FILM="film";

    @DatabaseField(columnName = FIELDS_ID, generatedId = true) // da se automatski generisu brojevi
    private int id;
    @DatabaseField(columnName = FIELDS_IME, canBeNull = false) //kao npr.
    private String ime;
    @DatabaseField(columnName = FIELDS_BIOGRAFIJA)
    private String biografija;
    @DatabaseField(columnName = FIELDS_OCENA)
    private double ocena;
    @DatabaseField(columnName = FIELDS_DATUM)
    private Date datum;
    @DatabaseField(columnName = FIELDS_FILM, foreign = true, foreignAutoCreate = true, foreignAutoRefresh = true)
    private Film film;

    public Glumac() {
    }

    public Glumac(int id, String ime, String biografija, double ocena, Date datum, Film film) {
        this.id = id;
        this.ime = ime;
        this.biografija = biografija;
        this.ocena = ocena;
        this.datum = datum;
        this.film = film;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getBiografija() {
        return biografija;
    }

    public void setBiografija(String biografija) {
        this.biografija = biografija;
    }

    public double getOcena() {
        return ocena;
    }

    public void setOcena(double ocena) {
        this.ocena = ocena;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    @Override
    public String toString() {
        return ime;
    }
}
