package com.patientinfo.hooman.patientinfo.PatientAddMedicalRecord;

import android.database.Cursor;

import com.patientinfo.hooman.patientinfo.Classes.G;
import com.patientinfo.hooman.patientinfo.Data.Drug;
import com.patientinfo.hooman.patientinfo.Data.PatientDataSource;
import com.patientinfo.hooman.patientinfo.Data.PatientDatabase;

import java.util.ArrayList;
import java.util.List;



public class AddMedicalRecordPresenter implements AddMedicalRecordContract.Presenter {
    private AddMedicalRecordContract.View view;
    private PatientDatabase patientDatabase;
    private List<String> dates;
    private PatientDataSource patientDataSource;
    private Drug drug;
    private List<Drug> drugList;
    public AddMedicalRecordPresenter(PatientDataSource patientDataSource){
        this.patientDataSource = patientDataSource;
        dates = new ArrayList<>();
        patientDatabase = new PatientDatabase(G.context);
        drugList = new ArrayList<>();
    }
    @Override
    public long addMedicalRecord(String date, List<Drug> drugs) {
        return 0;
    }

    @Override
    public void getDrug() {
        Cursor result;
        result = patientDatabase.getDrug();
        drugList = getDrug(result);
        view.getDrugs(drugList);
    }

    @Override
    public void attachView(AddMedicalRecordContract.View view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        view = null;
    }
    private List<Drug> getDrug(Cursor result){
        List<Drug> drugs = new ArrayList<>();
        for(result.moveToFirst();!result.isAfterLast();result.moveToNext()){
            drug = new Drug();
            drug.setDrugId(result.getInt(0));
            drug.setDrugName(result.getString(1));
            drugs.add(drug);
        }
        result.close();
        return drugs;
    }
}
