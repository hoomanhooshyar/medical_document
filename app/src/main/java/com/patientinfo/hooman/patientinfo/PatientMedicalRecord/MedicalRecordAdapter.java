package com.patientinfo.hooman.patientinfo.PatientMedicalRecord;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.patientinfo.hooman.patientinfo.R;

public class MedicalRecordAdapter extends RecyclerView.Adapter<MedicalRecordAdapter.MedicalRecordViewHolder> {


    @NonNull
    @Override
    public MedicalRecordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_view_drug_medical_reord_adapter,parent,false);
        return new MedicalRecordViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MedicalRecordViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
    public class MedicalRecordViewHolder extends RecyclerView.ViewHolder{

        public MedicalRecordViewHolder(View itemView) {
            super(itemView);
        }
    }
}
