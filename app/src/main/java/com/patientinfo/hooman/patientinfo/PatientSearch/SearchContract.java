package com.patientinfo.hooman.patientinfo.PatientSearch;

import com.patientinfo.hooman.patientinfo.Base.BasePresenter;
import com.patientinfo.hooman.patientinfo.Base.BaseView;
import com.patientinfo.hooman.patientinfo.Data.Patient;

import java.util.List;

public interface SearchContract {
    interface View extends BaseView{
        void showError(String e);
        void showSearchedPatients(List<Patient> patients);
        void updateList(boolean delete);
    }
    interface Presenter extends BasePresenter<View>{
        void getSearchedPatient(CharSequence charSequence,int id);
        void deletePatient(int id);
    }
}
