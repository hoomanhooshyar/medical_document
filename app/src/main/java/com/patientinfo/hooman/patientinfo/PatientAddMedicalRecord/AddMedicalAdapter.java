package com.patientinfo.hooman.patientinfo.PatientAddMedicalRecord;

import android.content.Context;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.patientinfo.hooman.patientinfo.Data.MedicalRecord;
import com.patientinfo.hooman.patientinfo.Data.PatientDatabase;
import com.patientinfo.hooman.patientinfo.PatientInfo.InfoFragment;
import com.patientinfo.hooman.patientinfo.R;

import java.util.ArrayList;
import java.util.List;

import ir.hamsaa.persiandatepicker.util.PersianCalendar;

public class AddMedicalAdapter extends RecyclerView.Adapter<AddMedicalAdapter.AddMedicalViewHolder> {
    private Context context;
    private List<MedicalRecord> drugs;
    private String date;
    public AddMedicalAdapter(Context context,List<MedicalRecord> drugs){
        this.context = context;
        this.date = date;
        this.drugs = drugs;
    }

    @NonNull
    @Override
    public AddMedicalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_view_drug_medical_reord_adapter,parent,false);
        return new AddMedicalViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AddMedicalViewHolder holder, int position) {
        final MedicalRecord medicalRecord = drugs.get(position);
        List<String> drugs = new ArrayList<>();
        drugs = medicalRecord.getDrugs();
        holder.txtDate.setText(medicalRecord.getDate());
        for(String drug:drugs){
            holder.txtDrugs.append(drug+"\n");
        }

    }

    @Override
    public int getItemCount() {
        return drugs.size();
    }

    public class AddMedicalViewHolder extends RecyclerView.ViewHolder {
        TextView txtDate;
        TextView txtDrugs;

        public AddMedicalViewHolder(View itemView) {
            super(itemView);
            txtDate = itemView.findViewById(R.id.txt_row_viewDrug_date);
            txtDrugs = itemView.findViewById(R.id.txt_row_viewDrug_soledDrug);
        }
    }

}
