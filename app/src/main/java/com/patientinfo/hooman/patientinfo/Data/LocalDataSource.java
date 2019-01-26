package com.patientinfo.hooman.patientinfo.Data;

import android.content.ContentValues;
import android.database.Cursor;

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

    @Override
    public Cursor searchPatient(CharSequence charSequence) {
        return patientDatabase.getPatientbyName(charSequence);
    }

    @Override
    public Cursor searchPatientByDisease(CharSequence charSequence) {
        return patientDatabase.getPatientbyDisease(charSequence);
    }

    @Override
    public Cursor searchPatientByIdnumber(CharSequence charSequence) {
        return patientDatabase.getPatientbyIdNumber(charSequence);
    }

    @Override
    public Cursor searchPatientByCity(CharSequence charSequence) {
        return patientDatabase.getPatientbyCity(charSequence);
    }

    @Override
    public long updatePatientDesc(int id, String desc) {
        return patientDatabase.updatePatientDesc(id,desc);
    }


}
