package com.patientinfo.hooman.patientinfo.PatinetSendSms;

import android.database.Cursor;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.patientinfo.hooman.patientinfo.Classes.G;
import com.patientinfo.hooman.patientinfo.Data.PatientDataSource;
import com.patientinfo.hooman.patientinfo.Data.PatientDatabase;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class SmsSendPresenter implements SendSmsContract.Presenter {
    private SendSmsContract.View view;
    private PatientDatabase patientDatabase;
    private PatientDataSource patientDataSource;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    ArrayList<String> numbers;
    public SmsSendPresenter(PatientDataSource patientDataSource) {
        this.patientDataSource = patientDataSource;
        patientDatabase = new PatientDatabase(G.context);
        numbers = new ArrayList<>();
    }
    @Override
    public void sendNumber(String item, int cat) {
        Cursor numberCursor;

        if (cat == 1) {
            numberCursor = patientDatabase.getNumberByDisease(item);
        } else {
            numberCursor = patientDatabase.getNumberByCity(item);
        }
        for (numberCursor.moveToFirst(); !numberCursor.isAfterLast(); numberCursor.moveToNext()) {
            numbers.add(numberCursor.getString(0));
        }
        view.getNumbers(numbers);
    }

    @Override
    public void getDisease() {
        Cursor diseaseCursor;
        diseaseCursor = patientDatabase.getDisease();
        view.fillSpinner(diseaseCursor);
    }

    @Override
    public void getCity() {
        Cursor cityCursor;
        cityCursor = patientDatabase.getCity();
        view.fillSpinner(cityCursor);
    }

    @Override
    public void sendMessage(ArrayList<String> numbers, String message) {
        patientDataSource.sendMessage(numbers,message).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onSuccess(String s) {
                        view.showSendNumberResult(s);
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.showSendNumberResult(e.toString());
                    }
                });
    }

    @Override
    public void attachView(SendSmsContract.View view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        this.view = null;

    }
}
