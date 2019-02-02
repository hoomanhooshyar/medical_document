package com.patientinfo.hooman.patientinfo.PatientAddDrug;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.patientinfo.hooman.patientinfo.Base.BaseFragment;
import com.patientinfo.hooman.patientinfo.Data.Drug;
import com.patientinfo.hooman.patientinfo.Data.PatientRepository;
import com.patientinfo.hooman.patientinfo.R;

public class AddDrugFragment extends BaseFragment implements AddDrugContract.View {
    private AddDrugContract.Presenter presenter;
    EditText edtDrugName;
    Button btnDrugSave;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new AddDrugPresenter(new PatientRepository());
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_add_drug;
    }

    @Override
    public void setupViews() {
        edtDrugName = rootView.findViewById(R.id.edt_addDrugFragment_name);
        btnDrugSave = rootView.findViewById(R.id.btn_addDrugFragment_save);
        btnDrugSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(edtDrugName.getText().toString().equals("")){
                    Toast.makeText(getViewContext(), "لطفا نام دارو را وارد کنید", Toast.LENGTH_SHORT).show();
                }else{
                    long result;
                    Drug drug = new Drug();
                    String drugName = edtDrugName.getText().toString();
                    drug.setDrugName(drugName);
                    result = presenter.addDrug(drug);
                    if(result > 0 ){
                        edtDrugName.setText("");
                        Toast.makeText(getViewContext(), "دارو با موفقیت ثبت شد", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(getViewContext(), "مشکلی در ثبت دارو بوجود آمده است. لطفا مجدد تلاش کنید", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    @Override
    public void showError(Throwable e) {
        Toast.makeText(getViewContext(), e.toString(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public Context getViewContext() {
        return getContext();
    }
}
