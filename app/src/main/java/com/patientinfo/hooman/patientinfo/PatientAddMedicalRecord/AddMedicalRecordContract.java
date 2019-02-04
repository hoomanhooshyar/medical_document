package com.patientinfo.hooman.patientinfo.PatientAddMedicalRecord;



import com.patientinfo.hooman.patientinfo.Base.BasePresenter;
import com.patientinfo.hooman.patientinfo.Base.BaseView;
import com.patientinfo.hooman.patientinfo.Data.Drug;

import java.util.List;

public interface AddMedicalRecordContract {
    interface View extends BaseView{
        void showError(String e);
        void getDrugs(List<Drug> drugs);
        void showSoldDrugs(List<String> soldDrugs);
    }
    interface Presenter extends BasePresenter<View>{
        long addMedicalRecord(int patientId,int drugId,String date);
        void getDrug();
        void getSoldDrugs(int patientId);
    }
}
