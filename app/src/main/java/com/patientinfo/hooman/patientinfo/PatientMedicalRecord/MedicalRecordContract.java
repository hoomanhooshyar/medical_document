package com.patientinfo.hooman.patientinfo.PatientMedicalRecord;

import android.database.Cursor;

import com.patientinfo.hooman.patientinfo.Base.BasePresenter;
import com.patientinfo.hooman.patientinfo.Base.BaseView;

public interface MedicalRecordContract {
    interface View extends BaseView{
        void showError(Throwable e);
        void showMedicalRecord();
    }
    interface Presenter extends BasePresenter<View>{
        Cursor getMedicalRecord();
    }
}
