package com.patientinfo.hooman.patientinfo.Data;

import java.util.List;

public class MedicalRecord {
    private List<String> drugs;
    private String date;
    private int id;

    public List<String> getDrugs() {
        return drugs;
    }

    public void setDrugs(List<String> drugs) {
        this.drugs = drugs;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
