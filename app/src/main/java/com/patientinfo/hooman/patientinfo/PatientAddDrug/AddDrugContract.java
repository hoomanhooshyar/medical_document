package com.patientinfo.hooman.patientinfo.PatientAddDrug;


import android.database.Cursor;

import com.patientinfo.hooman.patientinfo.Base.BasePresenter;
import com.patientinfo.hooman.patientinfo.Base.BaseView;
import com.patientinfo.hooman.patientinfo.Data.Drug;

public interface AddDrugContract {
    interface View extends BaseView{
        void showError(Throwable e);
    }
    interface Presenter extends BasePresenter<View>{
        long addDrug(Drug drug);
    }
}
