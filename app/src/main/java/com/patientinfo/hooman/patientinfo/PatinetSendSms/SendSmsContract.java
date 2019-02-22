package com.patientinfo.hooman.patientinfo.PatinetSendSms;

import android.database.Cursor;

import com.patientinfo.hooman.patientinfo.Base.BasePresenter;
import com.patientinfo.hooman.patientinfo.Base.BaseView;

import java.util.ArrayList;

public interface SendSmsContract {
    interface View extends BaseView{
        void showMessage(String msg);
        void fillSpinner(Cursor spinnerCursor);
        void showSendNumberResult(String result);
        void getNumbers(ArrayList<String> numbers);

    }
    interface Presenter extends BasePresenter<View>{
        void sendNumber(String item,int cat);
        void getDisease();
        void getCity();
        void sendMessage(ArrayList<String> numbers,String message);
    }
}
