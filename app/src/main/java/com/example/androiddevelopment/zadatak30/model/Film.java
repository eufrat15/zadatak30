package com.example.androiddevelopment.zadatak30.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import static com.example.androiddevelopment.zadatak30.model.Film.DATABASE_TABLE2;

/**
 * Created by androiddevelopment on 22.11.17..
 */

@DatabaseTable(tableName = DATABASE_TABLE2)
public class Film {

    public static final String DATABASE_TABLE2="film";
    public static final String FIELDS_ID2="id";
    public static final String FIELDS_NAZIV="naziv";
    public static final String FIELDS_ZANR="zanr";
    public static final String FIELDS_GODINA="godina";

    @DatabaseField(columnName = FIELDS_ID2, generatedId = true)
    private int id;
    @DatabaseField(columnName = FIELDS_NAZIV)
    private String naziv;
    @DatabaseField(columnName = FIELDS_ZANR)
    private String zanr;
    @DatabaseField(columnName = FIELDS_GODINA)
    private int godina;

    public Film() {
    }

    public Film(int id, String naziv, String zanr, int godina) {
        this.id = id;
        this.naziv = naziv;
        this.zanr = zanr;
        this.godina = godina;
    }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getZanr() {
        return zanr;
    }

    public void setZanr(String zanr) {
        this.zanr = zanr;
    }

    public int getGodina() {
        return godina;
    }

    public void setGodina(int godina) {
        this.godina = godina;
    }

    @Override
    public String toString() {
        return naziv;
    }
}


