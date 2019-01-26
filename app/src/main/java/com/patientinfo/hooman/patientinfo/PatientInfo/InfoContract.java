package com.patientinfo.hooman.patientinfo.PatientInfo;

import com.patientinfo.hooman.patientinfo.Base.BasePresenter;
import com.patientinfo.hooman.patientinfo.Base.BaseView;
import com.patientinfo.hooman.patientinfo.Data.Patient;

public interface InfoContract {
    interface View extends BaseView{
        void showPatientInfo(Patient patient);
    }
    interface Presenter extends BasePresenter<View>{
        void updatePatientInfo();
    }
}
