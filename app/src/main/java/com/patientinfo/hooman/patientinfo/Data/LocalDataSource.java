package com.patientinfo.hooman.patientinfo.Data;

import android.content.ContentValues;

import com.patientinfo.hooman.patientinfo.Classes.G;

public class LocalDataSource implements PatientDataSource {
    private PatientDatabase patientDatabase = new PatientDatabase(G.context);

    @Override
    public long insertPatient(Patient patient) {
        String name = patient.getName();
        String family = patient.getFamily();
        String birht_day = patient.getBirth_day();
        String mobile = patient.getMobile();
        String phone = patient.getPhone();
        String address = patient.getAddress();
        String city = patient.getCity();
        String id_number = patient.getId_number();
        String disease = patient.getDisease();
        String description = patient.getDescription();
        return patientDatabase.addPatient(name, family, birht_day, mobile, phone, id_number, address, city, disease, description);
    }
}
