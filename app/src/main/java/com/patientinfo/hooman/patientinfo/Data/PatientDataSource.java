package com.patientinfo.hooman.patientinfo.Data;

import android.content.ContentValues;

import io.reactivex.Single;

public interface PatientDataSource {
    long insertPatient(Patient patient);
    Patient searchPatient(String charSequence);
}
