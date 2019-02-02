package com.patientinfo.hooman.patientinfo.PatientAddMedicalRecord;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.patientinfo.hooman.patientinfo.Base.BaseFragment;
import com.patientinfo.hooman.patientinfo.Data.Drug;
import com.patientinfo.hooman.patientinfo.Data.PatientRepository;
import com.patientinfo.hooman.patientinfo.R;

import java.util.ArrayList;
import java.util.List;

import ir.hamsaa.persiandatepicker.util.PersianCalendar;

public class AddMedicalRecordFragment extends BaseFragment implements AddMedicalRecordContract.View {
    private AddMedicalRecordContract.Presenter presenter;
    private List<String> spDrugs;
    Button btnAdd;
    TextView txtDate;
    private String date;
    private PersianCalendar currentDate;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new AddMedicalRecordPresenter(new PatientRepository());
        spDrugs = new ArrayList<>();
        currentDate = new PersianCalendar();
        date = currentDate.getPersianShortDate();
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_add_medical_record;
    }

    @Override
    public void setupViews() {
        btnAdd = rootView.findViewById(R.id.btn_addMedicalRecordFragment_add);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getViewContext(), "OK", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void showError(Throwable e) {
        Toast.makeText(getViewContext(), e.toString(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getDrugs(List<Drug> drugs) {
        for (Drug drug : drugs) {
            spDrugs.add(drug.getDrugName());
        }
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
