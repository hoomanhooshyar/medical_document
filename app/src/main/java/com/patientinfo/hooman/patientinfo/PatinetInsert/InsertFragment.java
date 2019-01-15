package com.patientinfo.hooman.patientinfo.PatinetInsert;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.patientinfo.hooman.patientinfo.Base.BaseFragment;
import com.patientinfo.hooman.patientinfo.Data.Patient;
import com.patientinfo.hooman.patientinfo.Data.PatientRepository;
import com.patientinfo.hooman.patientinfo.R;

public class InsertFragment extends BaseFragment implements InsertContract.View {
    private InsertContract.Presenter presenter;
    EditText edtName;
    EditText edtFamily;
    EditText edtDisease;
    EditText edtPhone;
    EditText edtMobile;
    EditText edtIdnumber;
    EditText edtCity;
    EditText edtAddress;
    EditText edtBirthday;
    Button btnRegister;
    Patient patient;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new InsertPresenter(new PatientRepository());
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_insert_patient;
    }

    @Override
    public void setupViews() {
        edtName = rootView.findViewById(R.id.txtName);
        edtFamily = rootView.findViewById(R.id.txtFamily);
        edtDisease = rootView.findViewById(R.id.txtDisease);
        edtPhone = rootView.findViewById(R.id.txtPhone);
        edtMobile = rootView.findViewById(R.id.txtMobile);
        edtIdnumber = rootView.findViewById(R.id.txtIdnumber);
        edtCity = rootView.findViewById(R.id.txtCity);
        edtAddress = rootView.findViewById(R.id.txtAddress);
        edtBirthday = rootView.findViewById(R.id.txtBirthday);
        btnRegister = rootView.findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                patient = new Patient();
                patient.setName(edtName.getText().toString());
                patient.setFamily(edtFamily.getText().toString());
                patient.setDisease(edtDisease.getText().toString());
                patient.setPhone(edtPhone.getText().toString());
                patient.setMobile(edtMobile.getText().toString());
                patient.setId_number(edtIdnumber.getText().toString());
                patient.setCity(edtCity.getText().toString());
                patient.setAddress(edtAddress.getText().toString());
                patient.setBirth_day(edtBirthday.getText().toString());
                presenter.insertPatient(patient);
            }
        });
    }

    @Override
    public void showError(Throwable error) {

    }

    @Override
    public Context getViewContext() {
        return getContext();
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.attachView(this);
        setupViews();
    }

    @Override
    public void onStop() {
        super.onStop();
        presenter.detachView();
    }
}
