package com.patientinfo.hooman.patientinfo.PatientAddMedicalRecord;



import com.patientinfo.hooman.patientinfo.Base.BasePresenter;
import com.patientinfo.hooman.patientinfo.Base.BaseView;
import com.patientinfo.hooman.patientinfo.Data.Drug;

import java.util.List;

public interface AddMedicalRecordContract {
    interface View extends BaseView{
        void showError(Throwable e);
        void getDrugs(List<Drug> drugs);
    }
    interface Presenter extends BasePresenter<View>{
        long addMedicalRecord(String date, List<Drug> drugs);
        void getDrug();
    }
}
