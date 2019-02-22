package com.patientinfo.hooman.patientinfo.PatinetInsert;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.patientinfo.hooman.patientinfo.Base.BaseFragment;
import com.patientinfo.hooman.patientinfo.Data.Patient;
import com.patientinfo.hooman.patientinfo.Data.PatientRepository;
import com.patientinfo.hooman.patientinfo.R;

import ir.hamsaa.persiandatepicker.Listener;
import ir.hamsaa.persiandatepicker.PersianDatePickerDialog;
import ir.hamsaa.persiandatepicker.util.PersianCalendar;

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
    PersianDatePickerDialog persianDatePicker;
    PersianCalendar initDate;
    boolean twice = false;
    public final int MY_REQUEST_CODE = 1;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new InsertPresenter(new PatientRepository());
        /*if(Build.VERSION.SDK_INT>23){
            checkPermission();
        }else {
            Toast.makeText(getViewContext(), "SDK version is low", Toast.LENGTH_SHORT).show();

        }*/
    }

   /* private void checkPermission() {
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
            Toast.makeText(getViewContext(), "Permission had accepted", Toast.LENGTH_SHORT).show();
            setupViews();
        }
    }*/

   /* @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case MY_REQUEST_CODE:
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED &&
                        grantResults[1] == PackageManager.PERMISSION_GRANTED){
                    //write code here
                    //presenter = new InsertPresenter(new PatientRepository());
                    Toast.makeText(getViewContext(), "After accept permission", Toast.LENGTH_SHORT).show();
                    setupViews();


                }
        }
    }*/

    @Override
    public int getLayout() {
        return R.layout.fragment_insert_patient;
    }

    @Override
    public void setupViews() {
        edtName = rootView.findViewById(R.id.txt_fragmentEdit_name);
        edtFamily = rootView.findViewById(R.id.txt_fragmentEdit_family);
        edtDisease = rootView.findViewById(R.id.txt_fragmentEdit_disease);
        edtPhone = rootView.findViewById(R.id.txt_fragmentEdit_phone);
        edtMobile = rootView.findViewById(R.id.txt_fragmentEdit_mobile);
        edtIdnumber = rootView.findViewById(R.id.txt_fragmentEdit_idNumber);
        edtCity = rootView.findViewById(R.id.txt_fragmentEdit_city);
        edtAddress = rootView.findViewById(R.id.txt_fragmentEdit_address);
        edtBirthday = rootView.findViewById(R.id.txt_fragmentEdit_birthDay);
        btnRegister = rootView.findViewById(R.id.btn_fragmentEdit_edit);
        initDate = new PersianCalendar();
        initDate.setPersianDate(1369, 4, 4);
        persianDatePicker = new PersianDatePickerDialog(getViewContext())
                .setPositiveButtonString("باشه")
                .setNegativeButton("بیخیال")
                .setTodayButtonVisible(false)
                .setInitDate(initDate)
                .setMaxYear(PersianDatePickerDialog.THIS_YEAR)
                .setMinYear(1300)
                .setActionTextColor(Color.GRAY)
                .setListener(new Listener() {
                    @Override
                    public void onDateSelected(PersianCalendar persianCalendar) {
                        String date = persianCalendar.getPersianYear() + "/" + persianCalendar.getPersianMonth() + "/" + persianCalendar.getPersianDay();
                        edtBirthday.setText(date);
                    }

                    @Override
                    public void onDismissed() {

                    }
                });
        edtBirthday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                persianDatePicker.show();
            }
        });
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edtDisease.getText().toString().equals("")) {
                    Toast.makeText(getViewContext(), "لطفا اسم بیماری را وارد کنید", Toast.LENGTH_SHORT).show();
                } else if (edtName.getText().toString().equals("")){
                    Toast.makeText(getViewContext(), "لطفا نام بیمار را وارد کنید", Toast.LENGTH_SHORT).show();
                } else if (edtFamily.getText().toString().equals("")) {
                    Toast.makeText(getViewContext(), "لطفا نام خانوادگی بیمار را وارد کنید", Toast.LENGTH_SHORT).show();
                } else if (edtBirthday.getText().toString().equals("")) {
                    Toast.makeText(getViewContext(), "لطفا تاریخ تولد بیمار را وارد کنید", Toast.LENGTH_SHORT).show();
                } else if (edtPhone.getText().toString().equals("")) {
                    Toast.makeText(getViewContext(), "لطفا شماره تلفن را وارد کنید", Toast.LENGTH_SHORT).show();
                } else if (edtMobile.getText().toString().equals("")) {
                    Toast.makeText(getViewContext(), "لطفا شماره موبایل را وارد کنید", Toast.LENGTH_SHORT).show();
                } else if (edtIdnumber.getText().toString().equals("")) {
                    Toast.makeText(getViewContext(), "لطفا شماره ملی را وارد کنید", Toast.LENGTH_SHORT).show();
                } else if (edtCity.getText().toString().equals("")) {
                    Toast.makeText(getViewContext(), "لطفا شهر را وارد کنید", Toast.LENGTH_SHORT).show();
                } else if (edtAddress.getText().toString().equals("")) {
                    Toast.makeText(getViewContext(), "لطفا آدرس را وارد کنید", Toast.LENGTH_SHORT).show();
                } else if (edtIdnumber.length() < 10) {
                    Toast.makeText(getViewContext(), "کد ملی را به درستی وارد نکرده اید", Toast.LENGTH_SHORT).show();
                } else {
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
                    long result = presenter.insertPatient(patient);
                    if (result > 0) {
                        Toast.makeText(getViewContext(), "مشخصات بیمار با موفقیت ثبت شد", Toast.LENGTH_SHORT).show();
                        edtName.setText("");
                        edtFamily.setText("");
                        edtDisease.setText("");
                        edtPhone.setText("");
                        edtMobile.setText("");
                        edtIdnumber.setText("");
                        edtCity.setText("");
                        edtAddress.setText("");
                        edtBirthday.setText("");
                    } else {
                        Toast.makeText(getViewContext(), "مشکلی در ثبت بیمار بوجود آمد\nلطفا مجدد تلاش کنید", Toast.LENGTH_SHORT).show();
                    }
                }
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
        setupViews();

    }

    @Override
    public void onResume() {
        super.onResume();
        if (getView() == null) {
            return;
        }
        getView().setFocusableInTouchMode(true);
        getView().requestFocus();
        getView().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (keyEvent.getAction() == KeyEvent.ACTION_UP && i == KeyEvent.KEYCODE_BACK) {
                    if (twice) {
                        Intent intent = new Intent(Intent.ACTION_MAIN);
                        intent.addCategory(Intent.CATEGORY_HOME);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        getActivity().finish();
                        System.exit(0);
                    }else if(!twice){
                        Toast.makeText(getViewContext(), "برای خروج دوباره دکمه بازگشت را کلیک کنید", Toast.LENGTH_SHORT).show();
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                twice = false;
                            }
                        },2000);
                        twice = true;
                    }
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
