package com.patientinfo.hooman.patientinfo;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.patientinfo.hooman.patientinfo.PatientAddDrug.AddDrugFragment;
import com.patientinfo.hooman.patientinfo.PatientSearch.SearchFragment;
import com.patientinfo.hooman.patientinfo.PatinetInsert.InsertFragment;
import com.patientinfo.hooman.patientinfo.PatinetSendSms.SmsSendFragment;
import com.ss.bottomnavigation.BottomNavigation;
import com.ss.bottomnavigation.events.OnSelectedItemChangeListener;

public class MainActivity extends AppCompatActivity {
    BottomNavigation bottomNavigation;
    FragmentTransaction transaction;
    Fragment mainFragment = null;
    int fragmentCheck = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainFragment = new AddDrugFragment();
        bottomNavigation = findViewById(R.id.bn_main);
        bottomNavigation.setOnSelectedItemChangeListener(new OnSelectedItemChangeListener() {
            @Override
            public void onSelectedItemChanged(int i) {
                switch (i) {
                    case R.id.tab_drug_add:
                            mainFragment = new AddDrugFragment();
                            transaction = getSupportFragmentManager().beginTransaction();
                            transaction.replace(R.id.mainFrame, mainFragment);
                            transaction.commit();
                        break;
                    case R.id.tab_patient_search:
                            mainFragment = new SearchFragment();
                            transaction = getSupportFragmentManager().beginTransaction();
                            transaction.replace(R.id.mainFrame, mainFragment);
                            transaction.commit();
                        break;
                    case R.id.tab_patient_add:
                            mainFragment = new InsertFragment();
                            transaction = getSupportFragmentManager().beginTransaction();
                            transaction.replace(R.id.mainFrame, mainFragment);
                            transaction.commit();
                        break;
                    case R.id.tab_send_sms:
                            mainFragment = new SmsSendFragment();
                            transaction = getSupportFragmentManager().beginTransaction();
                            transaction.replace(R.id.mainFrame, mainFragment);
                            transaction.commit();
                        break;
                }
            }
        });
    }

    @Override
    public void onBackPressed() {

    }
}
