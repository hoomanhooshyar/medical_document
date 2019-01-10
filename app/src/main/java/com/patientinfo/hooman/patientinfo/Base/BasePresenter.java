package com.patientinfo.hooman.patientinfo.Base;

public interface BasePresenter<T extends BaseView> {
    void attachView(T view);
    void detachView();
}
