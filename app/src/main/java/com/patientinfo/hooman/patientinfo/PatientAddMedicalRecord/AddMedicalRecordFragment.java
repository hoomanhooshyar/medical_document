package com.patientinfo.hooman.patientinfo.PatientAddMedicalRecord;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.patientinfo.hooman.patientinfo.Base.BaseFragment;
import com.patientinfo.hooman.patientinfo.Data.Drug;
import com.patientinfo.hooman.patientinfo.Data.MedicalRecord;
import com.patientinfo.hooman.patientinfo.Data.PatientDatabase;
import com.patientinfo.hooman.patientinfo.Data.PatientRepository;
import com.patientinfo.hooman.patientinfo.PatientInfo.InfoFragment;
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
    RecyclerView rvMedicalRecord;
    Fragment infoFragment = null;
    private AddMedicalAdapter rvViewMedicalRecordAdapter;
    private ArrayList<Item> itemList;
    private List<MedicalRecord> soldDrugs;
    Spinner spAddDrug;
    private ArrayAdapter<String> spAdaper;
    final int[] drugId = new int[1];
    private PersianCalendar persianCalendar;
    String patientName;
    String patientFamily;
    String patientIdNumber;
    String patientDisease;
    String patientDesc;
    int patientId;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new AddMedicalRecordPresenter(new PatientRepository());
        soldDrugs = new ArrayList<>();
        spDrugs = new ArrayList<>();
        currentDate = new PersianCalendar();
        date = currentDate.getPersianShortDate();
        itemList = new ArrayList<>();
        persianCalendar = new PersianCalendar();
        date = persianCalendar.getPersianShortDate();
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_add_medical_record;
    }

    @Override
    public void setupViews() {
        rvMedicalRecord = rootView.findViewById(R.id.rv_addMedicalRecordFragment_list);
        rvViewMedicalRecordAdapter = new AddMedicalAdapter(getViewContext(),soldDrugs);
        rvMedicalRecord.setLayoutManager(new LinearLayoutManager(getViewContext(),LinearLayoutManager.VERTICAL,false));
        rvMedicalRecord.setAdapter(rvViewMedicalRecordAdapter);
        txtDate = rootView.findViewById(R.id.txt_row_addDrug_date);
        txtDate.setText(date);
        spAdaper = new ArrayAdapter<>(getViewContext(),R.layout.spinner_item,spDrugs);
        spAdaper.setDropDownViewResource(R.layout.spinner_dropdown_item);
        spAddDrug = rootView.findViewById(R.id.sp_row_addDrug_drug);
        btnAdd = rootView.findViewById(R.id.btn_addMedicalRecordFragment_add);


        spAddDrug.setAdapter(spAdaper);
        spAddDrug.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long id) {
                drugId[0] = (int)id+1;
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                long result;
                int patinetId = InfoFragment.patientId;
                result = presenter.addMedicalRecord(patinetId,drugId[0],date);
                if(result > 0){
                    showError("سوابق بیمار با موفقیت ثبت گردید");
                }else{
                    showError("مشکلی در درج سوابق به وجود آمده است\nلصفا مجدد تلاش کنید");
                }
            }
        });


        //rvMedicalRecord.setLayoutManager(new LinearLayoutManager(getViewContext(),LinearLayoutManager.VERTICAL,false));
        // rvMedicalRecord.setItemAnimator(new DefaultItemAnimator());
    }

    @Override
    public void showError(String e) {
        Toast.makeText(getViewContext(), e, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getDrugs(List<Drug> drugs) {
        for (Drug drug : drugs) {
            spDrugs.add(drug.getDrugName());
        }
    }

    @Override
    public void showSoldDrugs(List<MedicalRecord> soldDrugs) {
        this.soldDrugs = soldDrugs;
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
            showError("مشکلی در ارسال اطلاعات بوجود آمده است");
        } else {
            presenter.attachView(this);
            presenter.getDrug();
            presenter.getSoldDrugs(InfoFragment.patientId);
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
                    Bundle bundle = new Bundle();
                    bundle.putInt("id",patientId);
                    bundle.putString("patient_name",patientName);
                    bundle.putString("patient_family",patientFamily);
                    bundle.putString("patient_id",patientIdNumber);
                    bundle.putString("patient_disease",patientDisease);
                    bundle.putString("patient_desc",patientDesc);
                    infoFragment = new InfoFragment();
                    infoFragment.setArguments(bundle);
                    FragmentManager fragmentManager = ((FragmentActivity)getActivity()).getSupportFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.mainFrame,infoFragment).commit();
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
