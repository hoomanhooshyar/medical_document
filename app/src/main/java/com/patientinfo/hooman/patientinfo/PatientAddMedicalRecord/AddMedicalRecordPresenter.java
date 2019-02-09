package com.patientinfo.hooman.patientinfo.PatientAddMedicalRecord;

import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.patientinfo.hooman.patientinfo.Classes.G;
import com.patientinfo.hooman.patientinfo.Data.Drug;
import com.patientinfo.hooman.patientinfo.Data.MedicalRecord;
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
    private List<MedicalRecord> soldDrugs;
    private List<MedicalRecord> medicalRecords;

    public AddMedicalRecordPresenter(PatientDataSource patientDataSource) {
        this.patientDataSource = patientDataSource;
        dates = new ArrayList<>();
        patientDatabase = new PatientDatabase(G.context);
        drugList = new ArrayList<>();
        soldDrugs = new ArrayList<>();
        medicalRecords = new ArrayList<>();
    }

    @Override
    public long addMedicalRecord(int patientId, int drugId, String date) {
        long result;
        result = patientDatabase.addVisit(patientId, date, drugId);
        if (result > 0) {
            return result;
        } else {
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
        List<String> dates = new ArrayList<>();
        String date = "";
        result = patientDatabase.getSoldDrug(patientId);
        for (result.moveToFirst(); !result.isAfterLast(); result.moveToNext()) {
            if (!date.equals(result.getString(1))) {
                dates.add(result.getString(1));
            }
            date = result.getString(1);
        }

        for (String chk : dates) {
            MedicalRecord medicalRecord = new MedicalRecord();
            List<String> drugs = new ArrayList<>();
            for (result.moveToFirst(); !result.isAfterLast(); result.moveToNext()) {
                if (result.getString(1).equals(chk)) {
                    date = chk;
                    drugs.add(result.getString(0));
                }
            }
            if (!date.equals("")) {
                medicalRecord.setDate(date);
            }
            medicalRecord.setDrugs(drugs);
            soldDrugs.add(medicalRecord);
        }
        /*for(result.moveToFirst();!result.isAfterLast();result.moveToNext()){
            drugs = new ArrayList<>();
            MedicalRecord medicalRecord = new MedicalRecord();
            medicalRecord.setDate(result.getString(1));
            drugs.add(result.getString(0));
            medicalRecord.setDrugs(drugs);
            soldDrugs.add(medicalRecord);
        }*/

        //soldDrugs = getSoldDrug(result);
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

    private List<Drug> getDrugs(Cursor result) {
        List<Drug> drugs = new ArrayList<>();
        for (result.moveToFirst(); !result.isAfterLast(); result.moveToNext()) {
            drug = new Drug();
            drug.setDrugId(result.getInt(0));
            drug.setDrugName(result.getString(1));
            drugs.add(drug);
        }
        result.close();
        return drugs;
    }

    private List<MedicalRecord> getSoldDrug(Cursor result) {
        List<MedicalRecord> drugs = new ArrayList<>();
        List<String> correctDate;
        Cursor date = result;
        MedicalRecord correctMedical;
        //Log.e("SQL","date: "+DatabaseUtils.dumpCurrentRowToString(date));

        for (date.moveToFirst(); !date.isAfterLast(); date.moveToNext()) {
            correctMedical = new MedicalRecord();
            correctDate = new ArrayList<>();
            String strDate = date.getString(1);
            for (result.moveToFirst(); !result.isAfterLast(); result.moveToNext()) {
                correctMedical = new MedicalRecord();

                String soldDrug;
                if (strDate.equals(result.getString(1))) {
                    correctDate.add(result.getString(0));
                }
            }
            if (correctDate != null) {
                correctMedical.setDrugs(correctDate);
                correctMedical.setDate(strDate);
                drugs.add(correctMedical);
            }
        }
        return drugs;
    }
}
