package com.patientinfo.hooman.patientinfo;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.patientinfo.hooman.patientinfo.PatientAddDrug.AddDrugFragment;
import com.patientinfo.hooman.patientinfo.PatientSearch.SearchFragment;
import com.patientinfo.hooman.patientinfo.PatinetInsert.InsertFragment;
import com.patientinfo.hooman.patientinfo.PatinetSendSms.SmsSendFragment;
import com.ss.bottomnavigation.BottomNavigation;
import com.ss.bottomnavigation.events.OnSelectedItemChangeListener;

public class MainActivity extends AppCompatActivity {
    BottomNavigation bottomNavigation;
    FragmentTransaction transaction;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigation = findViewById(R.id.bn_main);
        bottomNavigation.setOnSelectedItemChangeListener(new OnSelectedItemChangeListener() {
            @Override
            public void onSelectedItemChanged(int i) {
                switch (i){
                    case R.id.tab_drug_add:
                        transaction = getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.mainFrame,new AddDrugFragment());
                        transaction.commit();
                        break;
                    case R.id.tab_patient_search:
                        transaction = getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.mainFrame,new SearchFragment());
                        transaction.commit();
                        break;
                    case R.id.tab_patient_add:
                        transaction = getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.mainFrame,new InsertFragment());
                        transaction.commit();
                        break;
                    case R.id.tab_send_sms:
                        transaction = getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.mainFrame,new SmsSendFragment());
                        transaction.commit();
                }
            }
        });
    }
}
