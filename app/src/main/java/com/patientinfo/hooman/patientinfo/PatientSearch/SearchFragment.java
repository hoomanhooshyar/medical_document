package com.patientinfo.hooman.patientinfo.PatientSearch;

import android.support.v4.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.patientinfo.hooman.patientinfo.Base.BaseFragment;
import com.patientinfo.hooman.patientinfo.Data.Patient;
import com.patientinfo.hooman.patientinfo.Data.PatientRepository;
import com.patientinfo.hooman.patientinfo.R;

import java.util.ArrayList;
import java.util.List;

public class SearchFragment extends BaseFragment implements SearchContract.View {
    private SearchContract.Presenter presenter;
    EditText edtSearchFragmentSearch;
    Spinner spSearchFragmentKind;
    RecyclerView rvSearchFragmentItems;
    ArrayList<String> spinnerItems;
    ArrayAdapter<String> spinnerAdapter;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new SearchPresenter(new PatientRepository());
        spinnerItems = new ArrayList<>();
        spinnerItems.add("بیمار");
        spinnerItems.add("بیماری");
        spinnerItems.add("کد ملی");
        spinnerItems.add("شهر");
        spinnerAdapter = new ArrayAdapter<String>(getViewContext(),android.R.layout.simple_spinner_item,spinnerItems);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_search_main;
    }

    @Override
    public void setupViews() {
        edtSearchFragmentSearch = rootView.findViewById(R.id.edt_searchFragment_search);
        spSearchFragmentKind = rootView.findViewById(R.id.sp_searchFragment_kind);
        rvSearchFragmentItems = rootView.findViewById(R.id.rv_searchFragment_patient);
        spSearchFragmentKind.setAdapter(spinnerAdapter);
        spSearchFragmentKind.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String item = adapterView.getItemAtPosition(i).toString();
                switch (item){
                    case "بیمار":
                        edtSearchFragmentSearch.setHint(R.string.edt_fragmentPatientSearch_patientName);
                        break;
                    case "بیماری":
                        edtSearchFragmentSearch.setHint(R.string.edt_fragmentPatientSearch_disease);
                        break;
                    case "کد ملی":
                        edtSearchFragmentSearch.setHint(R.string.edt_fragmentIdNumberSearch_idNumber);
                        break;
                    case "شهر":
                        edtSearchFragmentSearch.setHint(R.string.edt_fragmentCitySearch_city);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    @Override
    public void showError(Throwable e) {
        Toast.makeText(getViewContext(), e.toString(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showSearchedPatients(List<Patient> patients) {

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
