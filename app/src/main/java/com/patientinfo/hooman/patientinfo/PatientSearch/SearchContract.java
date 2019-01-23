package com.patientinfo.hooman.patientinfo.PatientSearch;

import com.patientinfo.hooman.patientinfo.Base.BasePresenter;
import com.patientinfo.hooman.patientinfo.Base.BaseView;
import com.patientinfo.hooman.patientinfo.Data.Patient;

import java.util.List;

public interface SearchContract {
    interface View extends BaseView{
        void showError(Throwable e);
        void showSearchedPatients(List<Patient> patients);
    }
    interface Presenter extends BasePresenter<View>{
        void getSearchedPatient(String charSequence);
    }
}