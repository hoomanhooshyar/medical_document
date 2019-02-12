package com.patientinfo.hooman.patientinfo.Data;

import android.database.Cursor;
import android.database.DatabaseUtils;
import android.util.Log;

public class PatientRepository implements PatientDataSource {
    private LocalDataSource localDataSource = new LocalDataSource();
    @Override
    public long insertPatient(Patient patient) {
        return localDataSource.insertPatient(patient);
    }

    @Override
    public Cursor searchPatient(CharSequence charSequence) {
        return localDataSource.searchPatient(charSequence);
    }

    @Override
    public Cursor searchPatientByDisease(CharSequence charSequence) {
        return localDataSource.searchPatientByDisease(charSequence);
    }

    @Override
    public Cursor searchPatientByIdnumber(CharSequence charSequence) {
        return localDataSource.searchPatientByIdnumber(charSequence);
    }

    @Override
    public Cursor searchPatientByCity(CharSequence charSequence) {
        return localDataSource.searchPatientByCity(charSequence);
    }

    @Override
    public long updatePatientDesc(int id, String desc) {
        return localDataSource.updatePatientDesc(id,desc);
    }

    @Override
    public long addDrug(String drugName) {
        return localDataSource.addDrug(drugName);
    }

    @Override
    public long addMedicalRecord(int patientId, String visitDate, int soldDrug) {
        return localDataSource.addMedicalRecord(patientId,visitDate,soldDrug);
    }

    @Override
    public Cursor getDrug() {
        return localDataSource.getDrug();
    }

    @Override
    public boolean deletePatient(int id) {
        return localDataSource.deletePatient(id);
    }

    @Override
    public Cursor getPatient(int id) {
        return localDataSource.getPatient(id);
    }

    @Override
    public long updatePatient(int id, Patient patient) {
        return localDataSource.updatePatient(id,patient);
    }

}
