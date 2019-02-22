package com.patientinfo.hooman.patientinfo.Data;

import android.database.Cursor;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

import io.reactivex.Single;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class ServerDataSource implements PatientDataSource {
    private ApiService apiService;

    public ServerDataSource() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.105/patientinfo/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
        apiService = retrofit.create(ApiService.class);
    }

    @Override
    public long insertPatient(Patient patient) {
        return 0;
    }

    @Override
    public Cursor searchPatient(CharSequence charSequence) {
        return null;
    }

    @Override
    public Cursor searchPatientByDisease(CharSequence charSequence) {
        return null;
    }

    @Override
    public Cursor searchPatientByIdnumber(CharSequence charSequence) {
        return null;
    }

    @Override
    public Cursor searchPatientByCity(CharSequence charSequence) {
        return null;
    }

    @Override
    public long updatePatientDesc(int id, String desc) {
        return 0;
    }

    @Override
    public long addDrug(String drugName) {
        return 0;
    }

    @Override
    public long addMedicalRecord(int patientId, String visitDate, int soldDrug) {
        return 0;
    }

    @Override
    public Cursor getDrug() {
        return null;
    }

    @Override
    public boolean deletePatient(int id) {
        return false;
    }

    @Override
    public Cursor getPatient(int id) {
        return null;
    }

    @Override
    public long updatePatient(int id, Patient patient) {
        return 0;
    }

    @Override
    public Cursor getDisease() {
        return null;
    }

    @Override
    public Cursor getCity() {
        return null;
    }

    @Override
    public Cursor getNumberByCity(String city) {
        return null;
    }

    @Override
    public Cursor getNumberByDisease(String disease) {
        return null;
    }

    @Override
    public Single<String> sendMessage(ArrayList<String> numbers, String message) {
        return apiService.sendNumber(numbers,message);
    }

}
