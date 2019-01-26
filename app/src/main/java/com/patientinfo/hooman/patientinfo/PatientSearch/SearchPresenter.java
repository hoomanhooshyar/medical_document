package com.patientinfo.hooman.patientinfo.PatientSearch;

import android.content.CursorLoader;
import android.database.Cursor;

import com.patientinfo.hooman.patientinfo.Classes.G;
import com.patientinfo.hooman.patientinfo.Data.Patient;
import com.patientinfo.hooman.patientinfo.Data.PatientDataSource;
import com.patientinfo.hooman.patientinfo.Data.PatientDatabase;

import java.util.ArrayList;
import java.util.List;

public class SearchPresenter implements SearchContract.Presenter {
    private SearchContract.View view;
    private PatientDataSource patientDataSource;
    private PatientDatabase patientDatabase;
    private Patient patient;
    private List<Patient> patientList;

    public SearchPresenter(PatientDataSource patientDataSource) {
        this.patientDataSource = patientDataSource;
        patientDatabase = new PatientDatabase(G.context);
        patientList = new ArrayList<>();
    }

    @Override
    public void getSearchedPatient(CharSequence charSequence, int id) {
        Cursor result;
        if (charSequence.length() > 0) {
            switch (id) {
                case 1:
                    result = patientDatabase.getPatientbyName(charSequence);
                    patientList = getPatientInfo(result);
                    view.showSearchedPatients(patientList);
                    break;
                case 2:
                    result = patientDatabase.getPatientbyDisease(charSequence);
                    patientList = getPatientInfo(result);
                    view.showSearchedPatients(patientList);
                    break;
                case 3:
                    result = patientDatabase.getPatientbyIdNumber(charSequence);
                    patientList = getPatientInfo(result);
                    view.showSearchedPatients(patientList);
                    break;
                case 4:
                    result = patientDatabase.getPatientbyCity(charSequence);
                    patientList = getPatientInfo(result);
                    view.showSearchedPatients(patientList);
                    break;
            }
        }
    }

    private List<Patient> getPatientInfo(Cursor result) {
        List<Patient> patients = new ArrayList<>();

        for (result.moveToFirst(); !result.isAfterLast(); result.moveToNext()) {
            patient = new Patient();
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
            patients.add(patient);
        }
        result.close();
        return patients;
    }

    @Override
    public void attachView(SearchContract.View view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        this.view = null;
    }
}
