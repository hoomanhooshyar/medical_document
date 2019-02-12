package com.patientinfo.hooman.patientinfo.PatientEdit;

import com.patientinfo.hooman.patientinfo.Base.BasePresenter;
import com.patientinfo.hooman.patientinfo.Base.BaseView;
import com.patientinfo.hooman.patientinfo.Data.Patient;

public interface EditContract {
    interface View extends BaseView{
        void showPatient(Patient patient);
        void checkUpdatePatient(long result);
        void showError(String error);
    }
    interface Presenter extends BasePresenter<View>{
        void getPatient(int id);
        void updatePationt(int id,Patient patient);
    }
}
