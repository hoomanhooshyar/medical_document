package com.patientinfo.hooman.patientinfo.Data;

public class PatientRepository implements PatientDataSource {
    private LocalDataSource localDataSource = new LocalDataSource();
    @Override
    public long insertPatient(Patient patient) {
        return localDataSource.insertPatient(patient);
    }

    @Override
    public Patient searchPatient(String charSequence) {
        return localDataSource.searchPatient(charSequence);
    }
}
