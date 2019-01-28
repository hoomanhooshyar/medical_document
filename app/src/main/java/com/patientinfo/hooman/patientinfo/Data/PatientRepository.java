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

}
