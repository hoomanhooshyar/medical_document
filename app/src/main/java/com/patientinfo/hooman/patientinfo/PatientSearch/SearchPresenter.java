package com.patientinfo.hooman.patientinfo.PatientSearch;

import com.patientinfo.hooman.patientinfo.Classes.G;
import com.patientinfo.hooman.patientinfo.Data.PatientDataSource;
import com.patientinfo.hooman.patientinfo.Data.PatientDatabase;

public class SearchPresenter implements SearchContract.Presenter {
    private SearchContract.View view;
    private PatientDataSource patientDataSource;
    private PatientDatabase patientDatabase;
    public SearchPresenter(PatientDataSource patientDataSource){
        this.patientDataSource = patientDataSource;
        patientDatabase = new PatientDatabase(G.context);
    }
    @Override
    public void getSearchedPatient(String charSequence) {
        if(charSequence.length()>0){
            patientDataSource.searchPatient(charSequence);
        }
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
