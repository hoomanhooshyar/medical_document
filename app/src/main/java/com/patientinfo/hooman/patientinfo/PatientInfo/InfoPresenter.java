package com.patientinfo.hooman.patientinfo.PatientInfo;

import com.patientinfo.hooman.patientinfo.Classes.G;
import com.patientinfo.hooman.patientinfo.Data.Patient;
import com.patientinfo.hooman.patientinfo.Data.PatientDataSource;
import com.patientinfo.hooman.patientinfo.Data.PatientDatabase;

public class InfoPresenter implements InfoContract.Presenter {
    private InfoContract.View view;
    private PatientDatabase patientDatabase;
    private Patient patient;
    private PatientDataSource patientDataSource;
    public InfoPresenter(PatientDataSource patientDataSource){
        this.patientDataSource = patientDataSource;
        patientDatabase = new PatientDatabase(G.context);
        patient = new Patient();
    }
    @Override
    public void updatePatientInfo(int id,String desc) {
        long result = patientDatabase.updatePatientDesc(id,desc);
        view.showPatientInfo(result);
    }

    @Override
    public void attachView(InfoContract.View view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        this.view = null;
    }
}
