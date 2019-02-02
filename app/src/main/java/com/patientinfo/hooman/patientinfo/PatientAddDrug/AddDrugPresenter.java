package com.patientinfo.hooman.patientinfo.PatientAddDrug;

import android.database.Cursor;

import com.patientinfo.hooman.patientinfo.Base.BasePresenter;
import com.patientinfo.hooman.patientinfo.Base.BaseView;
import com.patientinfo.hooman.patientinfo.Classes.G;
import com.patientinfo.hooman.patientinfo.Data.Drug;
import com.patientinfo.hooman.patientinfo.Data.PatientDataSource;
import com.patientinfo.hooman.patientinfo.Data.PatientDatabase;

public class AddDrugPresenter implements AddDrugContract.Presenter {
    AddDrugContract.View view;
    private PatientDataSource patientDataSource;
    private PatientDatabase patientDatabase;
    public AddDrugPresenter(PatientDataSource patientDataSource){
        this.patientDataSource = patientDataSource;
        patientDatabase = new PatientDatabase(G.context);
    }

    @Override
    public long addDrug(Drug drug) {
        long id;
        String drugName = drug.getDrugName();
        id = patientDatabase.addDrug(drugName);
        return id;
    }

    @Override
    public void attachView(AddDrugContract.View view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        this.view = null;
    }
}
