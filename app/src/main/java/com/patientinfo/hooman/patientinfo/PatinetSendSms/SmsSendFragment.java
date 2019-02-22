package com.patientinfo.hooman.patientinfo.PatinetSendSms;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.patientinfo.hooman.patientinfo.Base.BaseFragment;
import com.patientinfo.hooman.patientinfo.Data.PatientRepository;
import com.patientinfo.hooman.patientinfo.R;

import java.util.ArrayList;
import java.util.List;

public class SmsSendFragment extends BaseFragment implements SendSmsContract.View {
    private SendSmsContract.Presenter presenter;
    private ArrayList<String> diseases;
    EditText edtSms;
    Button btnSend;
    Spinner spCat;
    RadioGroup rgCat;
    int getNumber = 0;
    private ArrayAdapter<String> spCatAdapter;
    ArrayList<String> numbers;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new SmsSendPresenter(new PatientRepository());
        diseases = new ArrayList<>();
        numbers = new ArrayList<>();

    }

    @Override
    public int getLayout() {
        return R.layout.fragment_send_sms;
    }

    @Override
    public void setupViews() {
        edtSms = rootView.findViewById(R.id.edt_smsFragment_message);
        btnSend = rootView.findViewById(R.id.btn_smsFragment_send);
        rgCat = rootView.findViewById(R.id.rg_smsFragment_cat);
        spCat = rootView.findViewById(R.id.sp_smsFragment_cat);
        rgCat.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                switch (checkedId){
                    case R.id.rb_smsFragment_disease:
                        getNumber = 1;
                        presenter.getDisease();
                        break;
                    case R.id.rb_smsFragment_city:
                        getNumber = 2;
                        presenter.getCity();
                        break;
                }
            }
        });

        spCat.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String item = spCat.getItemAtPosition(i).toString();
                if(getNumber == 1){
                    //showMessage("Disease has been selected");
                    presenter.sendNumber(item,getNumber);
                    getNumber = 0;
                }else if(getNumber == 2){
                    //showMessage("City has been selected");
                    presenter.sendNumber(item,getNumber);
                    getNumber = 0;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String message = edtSms.getText().toString();
                presenter.sendMessage(numbers,message);
            }
        });
    }

    @Override
    public void showMessage(String msg) {
        Toast.makeText(getViewContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void fillSpinner(Cursor spinnerCursor) {
        diseases.clear();
        for(spinnerCursor.moveToFirst();!spinnerCursor.isAfterLast();spinnerCursor.moveToNext()){
            diseases.add(spinnerCursor.getString(0));
        }
        spCatAdapter = new ArrayAdapter<>(getViewContext(),R.layout.spinner_item,diseases);
        spCatAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        spCat.setAdapter(spCatAdapter);
        spCatAdapter.notifyDataSetChanged();

    }

    @Override
    public void showSendNumberResult(String result) {
        if(result.equals("ارسال با موفقیت انجام گردید")){
            showMessage(result);
        }else{
            showMessage(result);
        }
    }

    @Override
    public void getNumbers(ArrayList<String> numbers) {
       this.numbers = numbers;
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
