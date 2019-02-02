package com.patientinfo.hooman.patientinfo.PatientMedicalRecord;

import android.content.Context;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.patientinfo.hooman.patientinfo.Base.BaseFragment;
import com.patientinfo.hooman.patientinfo.PatientAddMedicalRecord.AddMedicalRecordFragment;
import com.patientinfo.hooman.patientinfo.R;

public class MedicalRecordFragment extends BaseFragment implements MedicalRecordContract.View {
    Button btnMedicalRecord;
    FragmentTransaction transaction;
    @Override
    public int getLayout() {
        return R.layout.fragment_medical_record;
    }

    @Override
    public void setupViews() {

    }

    @Override
    public void showError(Throwable e) {
        Toast.makeText(getViewContext(), e.toString(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showMedicalRecord() {

    }

    @Override
    public Context getViewContext() {
        return getContext();
    }
}
