package com.patientinfo.hooman.patientinfo.Data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class PatientDatabase extends SQLiteOpenHelper {
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "db_patinet";
    Context context;
    public PatientDatabase(Context context){
        super(context,DB_NAME,null,DB_VERSION);
        this.context = context;
    }
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
