package com.patientinfo.hooman.patientinfo.Data;

import android.content.ContentValues;
import android.database.Cursor;

import io.reactivex.Single;

public interface PatientDataSource {
    long insertPatient(Patient patient);
    Cursor searchPatient(CharSequence charSequence);
    Cursor searchPatientByDisease(CharSequence charSequence);
    Cursor searchPatientByIdnumber(CharSequence charSequence);
    Cursor searchPatientByCity(CharSequence charSequence);
    long updatePatientDesc(int id,String desc);
}
