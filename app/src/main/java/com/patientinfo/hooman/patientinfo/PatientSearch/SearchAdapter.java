package com.patientinfo.hooman.patientinfo.PatientSearch;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.patientinfo.hooman.patientinfo.Classes.G;
import com.patientinfo.hooman.patientinfo.Data.Patient;
import com.patientinfo.hooman.patientinfo.Data.PatientDatabase;
import com.patientinfo.hooman.patientinfo.PatientInfo.InfoFragment;
import com.patientinfo.hooman.patientinfo.R;

import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.PatientSearchViewHolder> {
    List<Patient> patientList;
    Context context;
    Activity activity;
   // PatientDatabase patientDatabase;
    public SearchAdapter(Context context,List<Patient> patientList,Activity activity){
        this.patientList = patientList;
        this.context = context;
        this.activity = activity;
        //patientDatabase = new PatientDatabase(context);
    }
    @NonNull
    @Override
    public PatientSearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_patient,parent,false);
        return new PatientSearchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PatientSearchViewHolder holder, int position) {
        final Patient patient = patientList.get(position);
        holder.txtPatientName.setText(patient.getName());
        holder.txtPatientFamily.setText(patient.getFamily());
        holder.txtPatientDisease.setText(patient.getDisease());
        holder.txtPatientIdNumber.setText(patient.getId_number());
        holder.cvPatientRow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(context, patient.getName(), Toast.LENGTH_SHORT).show();
                Bundle bundle = new Bundle();
                bundle.putInt("id",patient.getId());
                bundle.putString("patient_name",patient.getName());
                bundle.putString("patient_family",patient.getFamily());
                bundle.putString("patient_id",patient.getId_number());
                bundle.putString("patient_disease",patient.getDisease());
                bundle.putString("patient_desc",patient.getDescription());
                InfoFragment infoFragment = new InfoFragment();
                infoFragment.setArguments(bundle);
                FragmentManager fragmentManager = ((FragmentActivity)activity).getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.mainFrame,infoFragment).commit();

            }
        });
    }
    @Override
    public int getItemCount() {
        return patientList.size();
    }
    public class PatientSearchViewHolder extends RecyclerView.ViewHolder{
        TextView txtPatientName;
        TextView txtPatientFamily;
        TextView txtPatientIdNumber;
        TextView txtPatientDisease;
        Button btnDelete;
        Button btnEdit;
        CardView cvPatientRow;
        public PatientSearchViewHolder(View itemView) {
            super(itemView);
            txtPatientName = itemView.findViewById(R.id.txt_row_patientName);
            txtPatientFamily = itemView.findViewById(R.id.txt_row_patientFamily);
            txtPatientIdNumber = itemView.findViewById(R.id.txt_row_patientIdNumber);
            txtPatientDisease = itemView.findViewById(R.id.txt_row_patientDisease);
            btnEdit = itemView.findViewById(R.id.btn_row_patientEdit);
            btnDelete = itemView.findViewById(R.id.btn_row_patientDelete);
            cvPatientRow = itemView.findViewById(R.id.cv_row_searchedPatient);
        }
    }
}
