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
    private List<String> soldDrugs;
    public AddMedicalRecordPresenter(PatientDataSource patientDataSource){
        this.patientDataSource = patientDataSource;
        dates = new ArrayList<>();
        patientDatabase = new PatientDatabase(G.context);
        drugList = new ArrayList<>();
        soldDrugs = new ArrayList<>();
    }

    @Override
    public long addMedicalRecord(int patientId, int drugId, String date) {
        long result;
        result = patientDatabase.addVisit(patientId,date,drugId);
        if(result > 0){
            return result;
        }else{
            return result;
        }
    }

    @Override
    public void getDrug() {
        Cursor result;
        result = patientDatabase.getDrug();
        drugList = getDrugs(result);
        view.getDrugs(drugList);
    }

    @Override
    public void getSoldDrugs(int patientId) {
        Cursor result;
        result = patientDatabase.getSoldDrug(patientId);
        soldDrugs = getSoldDrug(result);
        view.showSoldDrugs(soldDrugs);
    }

    @Override
    public void attachView(AddMedicalRecordContract.View view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        view = null;
    }
    private List<Drug> getDrugs(Cursor result){
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
    private List<String> getSoldDrug(Cursor result){
        List<String> drugs = new ArrayList<>();
        for(result.moveToFirst();!result.isAfterLast();result.moveToNext()){
            String soldDrug;
            soldDrug = result.getString(0);
            drugs.add(soldDrug);
        }
        return drugs;
    }
}
