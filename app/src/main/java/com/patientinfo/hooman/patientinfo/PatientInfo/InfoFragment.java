package com.patientinfo.hooman.patientinfo.PatientInfo;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.patientinfo.hooman.patientinfo.Base.BaseFragment;
import com.patientinfo.hooman.patientinfo.Data.Patient;
import com.patientinfo.hooman.patientinfo.Data.PatientRepository;
import com.patientinfo.hooman.patientinfo.PatientAddMedicalRecord.AddMedicalRecordFragment;
import com.patientinfo.hooman.patientinfo.PatientSearch.SearchFragment;
import com.patientinfo.hooman.patientinfo.R;

public class InfoFragment extends BaseFragment implements InfoContract.View {
    String patientName;
    String patientFamily;
    String patientIdNumber;
    String patientDisease;
    String patientDesc;
    public static int patientId;
    TextView txtPatientInfo;
    EditText edtSummary;
    Button btnEdit;
    Button btnSummary;
    int status = 0;
    private FragmentTransaction transaction;
    private InfoContract.Presenter presenter;
    Patient patient;
    Fragment addMedicalRecordFragment;
    Fragment searchFragment;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new InfoPresenter(new PatientRepository());
        patient = new Patient();
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_info_patient;
    }

    @Override
    public void setupViews() {
        String paientInfo = patientName+" "+patientFamily+" "+patientIdNumber;
        txtPatientInfo = rootView.findViewById(R.id.txt_patientFragment_info);
        edtSummary = rootView.findViewById(R.id.edt_patientInfoFragment_summary);
        btnEdit = rootView.findViewById(R.id.btn_patientInfoFragment_edit);
        btnSummary = rootView.findViewById(R.id.btn_patientInfoFragment_summary);
        edtSummary.setText(patientDesc);
        txtPatientInfo.setText(paientInfo);
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (status == 0) {
                    btnEdit.setText("ویرایش شرح حال");
                    status = 1;
                    edtSummary.setEnabled(true);
                } else if (status == 1) {
                    btnEdit.setText("ثبت شرح حال");
                    status = 2;
                }else if(status == 2){
                    patientDesc = edtSummary.getText().toString();
                    presenter.updatePatientInfo(patientId,patientDesc);
                    status = 0;
                    edtSummary.setEnabled(false);
                }
            }
        });
        btnSummary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putInt("id",patientId);
                bundle.putString("patient_name",patientName);
                bundle.putString("patient_family",patientFamily);
                bundle.putString("patient_id",patientIdNumber);
                bundle.putString("patient_disease",patientDisease);
                bundle.putString("patient_desc",patientDesc);
                addMedicalRecordFragment = new AddMedicalRecordFragment();
                addMedicalRecordFragment.setArguments(bundle);
                FragmentManager fragmentManager = ((FragmentActivity)getActivity()).getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.mainFrame,addMedicalRecordFragment).commit();
            }
        });

    }

    @Override
    public void showPatientInfo(long result) {
        if(result > 0){
            Toast.makeText(getViewContext(), "اطلاعات با موفقیت ویرایش شد", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(getViewContext(), "لطفا دوباره تلاش کنید", Toast.LENGTH_SHORT).show();
        }
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
        if (patientName.equals("") || patientFamily.equals("") || patientIdNumber.equals("") || patientDisease.equals("")) {
            Toast.makeText(getViewContext(), "مشکلی پیش آمده است", Toast.LENGTH_SHORT).show();
        } else {
            presenter.attachView(this);
            setupViews();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if(getView() == null){
            return;
        }
        getView().setFocusableInTouchMode(true);
        getView().requestFocus();
        getView().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if(keyEvent.getAction() == KeyEvent.ACTION_UP && i == KeyEvent.KEYCODE_BACK){
                    searchFragment = new SearchFragment();
                    FragmentManager fragmentManager = ((FragmentActivity)getActivity()).getSupportFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.mainFrame,searchFragment).commit();
                    return true;
                }
                return false;
            }
        });
    }

    @Override
    public void onStop() {
        super.onStop();
        presenter.detachView();
    }
}
