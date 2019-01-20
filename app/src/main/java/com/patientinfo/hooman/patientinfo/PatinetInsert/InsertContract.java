package com.patientinfo.hooman.patientinfo.PatinetInsert;

import com.patientinfo.hooman.patientinfo.Base.BasePresenter;
import com.patientinfo.hooman.patientinfo.Base.BaseView;
import com.patientinfo.hooman.patientinfo.Data.Patient;

public interface InsertContract {
    interface View extends BaseView{
        void showError(Throwable error);
    }
    interface Presenter extends BasePresenter<View>{
        long insertPatient(Patient patient);
    }
}
