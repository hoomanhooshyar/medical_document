package com.patientinfo.hooman.patientinfo.Data;

import android.content.ContentValues;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;

public interface PatientDataSource {
    long insertPatient(Patient patient);
    Cursor searchPatient(CharSequence charSequence);
    Cursor searchPatientByDisease(CharSequence charSequence);
    Cursor searchPatientByIdnumber(CharSequence charSequence);
    Cursor searchPatientByCity(CharSequence charSequence);
    long updatePatientDesc(int id,String desc);
    long addDrug(String drugName);
    long addMedicalRecord(int patientId, String visitDate, int soldDrug);
    Cursor getDrug();
    boolean deletePatient(int id);
    Cursor getPatient(int id);
    long updatePatient(int id,Patient patient);
    Cursor getDisease();
    Cursor getCity();
    Cursor getNumberByCity(String city);
    Cursor getNumberByDisease(String disease);
    Single<String> sendMessage(ArrayList<String> numbers,String message);
}
