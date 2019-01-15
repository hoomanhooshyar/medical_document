package com.patientinfo.hooman.patientinfo.PatinetInsert;

import com.patientinfo.hooman.patientinfo.Classes.G;
import com.patientinfo.hooman.patientinfo.Data.Patient;
import com.patientinfo.hooman.patientinfo.Data.PatientDataSource;
import com.patientinfo.hooman.patientinfo.Data.PatientDatabase;

public class InsertPresenter implements InsertContract.Presenter {
    private InsertContract.View view;
    private PatientDataSource patientDataSource;
    PatientDatabase patientDatabase;
    public InsertPresenter(PatientDataSource patientDataSource){
        this.patientDataSource = patientDataSource;
        patientDatabase = new PatientDatabase(G.context);
    }
    @Override
    public void insertPatient(Patient patient) {
        String name = patient.getName();
        String family = patient.getFamily();
        String birthDay = patient.getBirth_day();
        String mobile = patient.getMobile();
        String phone = patient.getPhone();
        String idNumber = patient.getId_number();
        String address = patient.getAddress();
        String city = patient.getCity();
        String disease = patient.getDisease();
        patientDatabase.addPatient(name,family,birthDay,mobile,phone,idNumber,address,city,disease,null);
    }

    @Override
    public void attachView(InsertContract.View view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        this.view = null;
    }
}
