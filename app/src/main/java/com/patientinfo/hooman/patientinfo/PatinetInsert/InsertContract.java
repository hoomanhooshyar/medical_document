package com.patientinfo.hooman.patientinfo.PatinetInsert;

import com.patientinfo.hooman.patientinfo.Base.BasePresenter;
import com.patientinfo.hooman.patientinfo.Base.BaseView;

public interface InsertContract {
    interface View extends BaseView{
        void showError(Throwable error);
    }
    interface Presenter extends BasePresenter<View>{
        void insertPatient();
    }
}
