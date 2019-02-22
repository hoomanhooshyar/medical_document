package com.patientinfo.hooman.patientinfo.PatientSearch;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v4.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
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
import com.patientinfo.hooman.patientinfo.PatientAddMedicalRecord.AddMedicalAdapter;
import com.patientinfo.hooman.patientinfo.PatientAddMedicalRecord.Item;
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
    List<Patient> searchedPatient;
    SearchAdapter searchAdapter;
    int itemPosition = 0;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        searchedPatient = new ArrayList<>();
        presenter = new SearchPresenter(new PatientRepository());
        spinnerItems = new ArrayList<>();
        spinnerItems.add("لطفا نوع جستجوی خود را انتخاب کنید");
        spinnerItems.add("بیمار");
        spinnerItems.add("بیماری");
        spinnerItems.add("کد ملی");
        spinnerItems.add("شهر");
        spinnerAdapter = new ArrayAdapter<String>(getViewContext(), R.layout.spinner_item, spinnerItems);
        spinnerAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
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
        rvSearchFragmentItems.setLayoutManager(new LinearLayoutManager(getViewContext(), LinearLayoutManager.VERTICAL, false));
        spSearchFragmentKind.setAdapter(spinnerAdapter);
        spSearchFragmentKind.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                //String item = adapterView.getItemAtPosition(i).toString();
                //final int itemId = spSearchFragmentKind.getSelectedItemPosition();
                itemPosition = position;
                switch (itemPosition) {
                    case 1:
                        edtSearchFragmentSearch.setHint(R.string.edt_fragmentPatientSearch_patientName);
                        edtSearchFragmentSearch.setInputType(InputType.TYPE_CLASS_TEXT);
                        edtSearchFragmentSearch.setText("");
                        break;
                    case 2:
                        edtSearchFragmentSearch.setHint(R.string.edt_fragmentPatientSearch_disease);
                        edtSearchFragmentSearch.setInputType(InputType.TYPE_CLASS_TEXT);
                        edtSearchFragmentSearch.setText("");
                        break;
                    case 3:
                        edtSearchFragmentSearch.setHint(R.string.edt_fragmentIdNumberSearch_idNumber);
                        edtSearchFragmentSearch.setInputType(InputType.TYPE_CLASS_NUMBER);
                        edtSearchFragmentSearch.setText("");
                        break;
                    case 4:
                        edtSearchFragmentSearch.setHint(R.string.edt_fragmentCitySearch_city);
                        edtSearchFragmentSearch.setInputType(InputType.TYPE_CLASS_TEXT);
                        edtSearchFragmentSearch.setText("");
                        break;
                }


                edtSearchFragmentSearch.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                        presenter.getSearchedPatient(charSequence, itemPosition);
                    }

                    @Override
                    public void afterTextChanged(Editable editable) {

                    }
                });
                /*switch (itemPosition){
                    case 1:
                        edtSearchFragmentSearch.setHint(R.string.edt_fragmentPatientSearch_patientName);
                        edtSearchFragmentSearch.setInputType(InputType.TYPE_CLASS_TEXT);
                        edtSearchFragmentSearch.addTextChangedListener(new TextWatcher() {
                            @Override
                            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                            }

                            @Override
                            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                                presenter.getSearchedPatient(charSequence,itemPosition);
                            }

                            @Override
                            public void afterTextChanged(Editable editable) {

                            }
                        });

                        break;
                    case 2:

                        edtSearchFragmentSearch.setHint(R.string.edt_fragmentPatientSearch_disease);
                        edtSearchFragmentSearch.setInputType(InputType.TYPE_CLASS_TEXT);
                        edtSearchFragmentSearch.addTextChangedListener(new TextWatcher() {
                            @Override
                            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                            }

                            @Override
                            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                                presenter.getSearchedPatient(charSequence,itemPosition);
                            }

                            @Override
                            public void afterTextChanged(Editable editable) {

                            }
                        });
                        break;
                    case 3:
                        edtSearchFragmentSearch.setHint(R.string.edt_fragmentIdNumberSearch_idNumber);
                        edtSearchFragmentSearch.setInputType(InputType.TYPE_CLASS_NUMBER);
                        edtSearchFragmentSearch.addTextChangedListener(new TextWatcher() {
                            @Override
                            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                            }

                            @Override
                            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                                presenter.getSearchedPatient(charSequence,itemPosition);
                            }

                            @Override
                            public void afterTextChanged(Editable editable) {

                            }
                        });
                        break;
                    case 4:
                        edtSearchFragmentSearch.setHint(R.string.edt_fragmentCitySearch_city);
                        edtSearchFragmentSearch.setInputType(InputType.TYPE_CLASS_TEXT);
                        edtSearchFragmentSearch.addTextChangedListener(new TextWatcher() {
                            @Override
                            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                            }

                            @Override
                            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                                presenter.getSearchedPatient(charSequence,itemPosition);
                            }

                            @Override
                            public void afterTextChanged(Editable editable) {

                            }
                        });
                        break;
                }*/
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    @Override
    public void showError(String e) {
        Toast.makeText(getViewContext(), e.toString(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showSearchedPatients(List<Patient> patients) {
        searchedPatient.clear();
        searchedPatient = patients;
        if (searchedPatient.size() == 0) {
            Toast.makeText(getViewContext(), "هیچ بیماری با این مشخصات یافت نشد", Toast.LENGTH_SHORT).show();
        } else {
            searchAdapter = new SearchAdapter(getViewContext(), searchedPatient,getActivity());
            rvSearchFragmentItems.setAdapter(searchAdapter);
            searchAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void updateList(boolean delete) {
        if(delete){
            showError("بیمار با موفقیت حذف شد");
            showSearchedPatients(searchedPatient);

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
