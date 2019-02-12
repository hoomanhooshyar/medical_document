package com.patientinfo.hooman.patientinfo.PatientEdit;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.patientinfo.hooman.patientinfo.Base.BaseFragment;
import com.patientinfo.hooman.patientinfo.Data.Patient;
import com.patientinfo.hooman.patientinfo.Data.PatientRepository;
import com.patientinfo.hooman.patientinfo.PatientInfo.InfoFragment;
import com.patientinfo.hooman.patientinfo.R;

public class EditFragment extends BaseFragment implements EditContract.View {
    private EditContract.Presenter presenter;
    EditText edtEditDisease;
    EditText edtEditName;
    EditText edtEditFamily;
    EditText edtEditPhone;
    EditText edtEditMobile;
    EditText edtEditIdNumber;
    EditText edtEditCity;
    EditText edtEditAddress;
    EditText edtEditBirthday;
    Button btnEdit;
    private int id = 0;
    private Patient patient;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new EditPresenter(new PatientRepository());
        patient = new Patient();

    }

    @Override
    public int getLayout() {
        return R.layout.fragment_edit_patient;
    }

    @Override
    public void setupViews() {
        edtEditDisease = rootView.findViewById(R.id.txt_fragmentEdit_disease);
        edtEditName = rootView.findViewById(R.id.txt_fragmentEdit_name);
        edtEditFamily = rootView.findViewById(R.id.txt_fragmentEdit_family);
        edtEditBirthday = rootView.findViewById(R.id.txt_fragmentEdit_birthDay);
        edtEditPhone = rootView.findViewById(R.id.txt_fragmentEdit_phone);
        edtEditMobile = rootView.findViewById(R.id.txt_fragmentEdit_mobile);
        edtEditIdNumber = rootView.findViewById(R.id.txt_fragmentEdit_idNumber);
        edtEditCity = rootView.findViewById(R.id.txt_fragmentEdit_city);
        edtEditAddress = rootView.findViewById(R.id.txt_fragmentEdit_address);
        btnEdit = rootView.findViewById(R.id.btn_fragmentEdit_edit);


        /*edtEditDisease.setText(patient.getDisease());
        edtEditName.setText(patient.getName());
        edtEditFamily.setText(patient.getFamily());
        edtEditPhone.setText(patient.getPhone());
        edtEditMobile.setText(patient.getMobile());
        edtEditIdNumber.setText(patient.getId_number());
        edtEditCity.setText(patient.getCity());
        edtEditAddress.setText(patient.getAddress());*/
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Patient updatePatient = new Patient();
                if (edtEditDisease.getText().toString().equals("") || edtEditName.getText().toString().equals("") ||
                        edtEditFamily.getText().toString().equals("") || edtEditBirthday.getText().toString().equals("") ||
                        edtEditPhone.getText().toString().equals("") || edtEditPhone.getText().toString().equals("") ||
                        edtEditMobile.getText().toString().equals("") || edtEditIdNumber.getText().toString().equals("") ||
                        edtEditCity.getText().toString().equals("") || edtEditAddress.getText().toString().equals("")) {
                    showError("لطفا همه فیلدها را پر کنید");
                } else {
                    updatePatient.setDisease(edtEditDisease.getText().toString());
                    updatePatient.setName(edtEditName.getText().toString());
                    updatePatient.setFamily(edtEditFamily.getText().toString());
                    updatePatient.setBirth_day(edtEditBirthday.getText().toString());
                    updatePatient.setPhone(edtEditPhone.getText().toString());
                    updatePatient.setMobile(edtEditMobile.getText().toString());
                    updatePatient.setId_number(edtEditIdNumber.getText().toString());
                    updatePatient.setCity(edtEditCity.getText().toString());
                    updatePatient.setAddress(edtEditAddress.getText().toString());
                    presenter.updatePationt(id, updatePatient);
                }
            }
        });


    }

    @Override
    public void showPatient(Patient patient) {
        this.patient = patient;
        edtEditDisease.setText(patient.getDisease());
        edtEditName.setText(patient.getName());
        edtEditFamily.setText(patient.getFamily());
        edtEditBirthday.setText(patient.getBirth_day());
        edtEditPhone.setText(patient.getPhone());
        edtEditMobile.setText(patient.getMobile());
        edtEditIdNumber.setText(patient.getId_number());
        edtEditCity.setText(patient.getCity());
        edtEditAddress.setText(patient.getAddress());
    }

    @Override
    public void checkUpdatePatient(long result) {
        if (result > 0) {
            showError("تغییرات با موفقیت ثبت گردید");
        }
    }

    @Override
    public void showError(String error) {
        Toast.makeText(getViewContext(), error, Toast.LENGTH_SHORT).show();
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
        id = this.getArguments().getInt("id");
        presenter.getPatient(id);


    }

    @Override
    public void onStop() {
        super.onStop();
        presenter.detachView();
    }
}
