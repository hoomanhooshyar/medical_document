package com.patientinfo.hooman.patientinfo.PatinetSendSms;

import com.patientinfo.hooman.patientinfo.Base.BasePresenter;
import com.patientinfo.hooman.patientinfo.Base.BaseView;

public interface SendSmsContract {
    interface View extends BaseView{
        void showMessage(String msg);
    }
    interface Presenter extends BasePresenter<View>{
        void getPatient();
    }
}
