package com.patientinfo.hooman.patientinfo.PatientEdit;

import android.database.Cursor;

import com.patientinfo.hooman.patientinfo.Base.BasePresenter;
import com.patientinfo.hooman.patientinfo.Classes.G;
import com.patientinfo.hooman.patientinfo.Data.Patient;
import com.patientinfo.hooman.patientinfo.Data.PatientDataSource;
import com.patientinfo.hooman.patientinfo.Data.PatientDatabase;

public class EditPresenter implements EditContract.Presenter {
    private EditContract.View view;
    private PatientDatabase patientDatabase;
    private PatientDataSource patientDataSource;
    private Patient patient;
    public EditPresenter(PatientDataSource patientDataSource){
        this.patientDataSource = patientDataSource;
        patientDatabase = new PatientDatabase(G.context);
        patient = new Patient();
    }
    @Override
    public void getPatient(int id) {
        Cursor result = patientDatabase.getPatient(id);
        for(result.moveToFirst();!result.isAfterLast();result.moveToNext()){
            patient.setId(result.getInt(0));
            patient.setName(result.getString(1));
            patient.setFamily(result.getString(2));
            patient.setBirth_day(result.getString(3));
            patient.setMobile(result.getString(4));
            patient.setPhone(result.getString(5));
            patient.setId_number(result.getString(6));
            patient.setAddress(result.getString(7));
            patient.setCity(result.getString(8));
            patient.setDisease(result.getString(9));
            patient.setDescription(result.getString(10));
            view.showPatient(patient);

        }
    }

    @Override
    public void updatePationt(int id, Patient patient) {
        long result = patientDatabase.updatePatinet(id,patient);
        view.checkUpdatePatient(result);
    }

    @Override
    public void attachView(EditContract.View view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        this.view = null;
    }
}
