package com.patientinfo.hooman.patientinfo.PatinetInsert;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.patientinfo.hooman.patientinfo.Base.BaseFragment;
import com.patientinfo.hooman.patientinfo.Classes.G;
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
    public final int MY_REQUEST_CODE = 1;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new InsertPresenter(new PatientRepository());
        if(Build.VERSION.SDK_INT>23){
            checkPermission();
        }else {

            setupViews();
        }
    }

    private void checkPermission() {
        if(ContextCompat.checkSelfPermission(G.context,
                Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(getActivity(),new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE},MY_REQUEST_CODE);
        }else if(ContextCompat.checkSelfPermission(G.context,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(getActivity(),new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE},MY_REQUEST_CODE);
        }else{
            //Write code here
            //presenter = new InsertPresenter(new PatientRepository());
            setupViews();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case MY_REQUEST_CODE:
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED &&
                        grantResults[1] == PackageManager.PERMISSION_GRANTED){
                    //write code here
                    //presenter = new InsertPresenter(new PatientRepository());
                    setupViews();

                }
        }
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
        Toast.makeText(getViewContext(), error.toString(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public Context getViewContext() {
        return getContext();
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.attachView(this);
        //setupViews();

    }

    @Override
    public void onStop() {
        super.onStop();
        presenter.detachView();
    }
}
