package com.patientinfo.hooman.patientinfo.PatientMedicalRecord;

import android.database.Cursor;

import com.patientinfo.hooman.patientinfo.Data.Patient;
import com.patientinfo.hooman.patientinfo.Data.PatientDataSource;
import com.patientinfo.hooman.patientinfo.Data.PatientDatabase;

public class MedicalRecordPresenter implements MedicalRecordContract.Presenter {
    private MedicalRecordContract.View view;
    private PatientDataSource patientDataSource;
    private PatientDatabase patientDatabase;
    private Patient patient;
    @Override
    public Cursor getMedicalRecord() {
        return null;
    }

    @Override
    public void attachView(MedicalRecordContract.View view) {

    }

    @Override
    public void detachView() {

    }
}
