package com.patientinfo.hooman.patientinfo.PatientInfo;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.patientinfo.hooman.patientinfo.Base.BaseFragment;
import com.patientinfo.hooman.patientinfo.Data.Patient;
import com.patientinfo.hooman.patientinfo.Data.PatientRepository;
import com.patientinfo.hooman.patientinfo.R;

public class InfoFragment extends BaseFragment implements InfoContract.View {
    String patientName;
    String patientFamily;
    String patientIdNumber;
    String patientDisease;
    String patientDesc;
    int patientId;
    private InfoContract.Presenter presenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new InfoPresenter(new PatientRepository());
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_info_patient;
    }

    @Override
    public void setupViews() {

    }

    @Override
    public void showPatientInfo(Patient patient) {

    }

    @Override
    public Context getViewContext() {
        return getContext();
    }
    @Override
    public void onStart() {
        super.onStart();
        patientId = this.getArguments().getInt("id");
        patientName = this.getArguments().getString("patient_name");
        patientFamily = this.getArguments().getString("patient_family");
        patientIdNumber = this.getArguments().getString("patient_id");
        patientDisease = this.getArguments().getString("patient_disease");
        patientDesc = this.getArguments().getString("patient_desc");
        if (patientName.equals("") || patientFamily.equals("") || patientIdNumber.equals("") || patientDisease.equals("") || patientDesc.equals("")) {
            Toast.makeText(getViewContext(), "مشکلی پیش آمده است", Toast.LENGTH_SHORT).show();
        } else {
            presenter.attachView(this);
            setupViews();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        presenter.detachView();
    }
}
