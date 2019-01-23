package com.patientinfo.hooman.patientinfo.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class PatientDatabase extends SQLiteOpenHelper {
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "db_patinet";
    private static final String TBL_PATIENT = "tbl_patient";
    private static final String TBL_DRUG = "tbl_drug";
    private static final String TBL_SOLD_DRUG = "tbl_sold_drug";
    private static final String TBL_VISIT = "tbl_visit";
    private static final String COL_TBLPATIENT_ID = "id";
    private static final String COL_TBLPATIENT_NAME = "name";
    private static final String COL_TBLPATIENT_FAMILY = "family";
    private static final String COL_TBLPATIENT_BIRTHDAY = "birth_day";
    private static final String COL_TBLPATIENT_CELL = "mobile";
    private static final String COL_TBLPATIENT_PHONE = "phone";
    private static final String COL_TBLPATIENT_ADDRESS = "address";
    private static final String COL_TBLPATIENT_CITY = "city";
    private static final String COL_TBLPATIENT_IDNUMBER = "id_number";
    private static final String COL_TBLPATIENT_DISEASE = "disease";
    private static final String COL_TBLPATIENT_DESC = "description";
    private static final String COL_TBLVISIT_ID = "id";
    private static final String COL_TBLVISIT_ID_PATIENT = "id_patient";
    private static final String COL_TBLVISIT_DATE_VISIT = "date_visit";
    private static final String COL_TBLVISIT_ID_SOLD_DRUG = "id_sold_drug";
    private static final String COL_TBLDRUG_ID = "id";
    private static final String COL_TBLDRUG_NAME = "drug_name";
    private static final String COL_SOLD_DRUG_ID = "id";
    private static final String COL_SOLD_DRUG_PATIENT_ID = "id_patient";
    private static final String COL_SOLD_DRUG_DRUG_ID = "id_drug";
    private static final String QUERY_TBLPATINET = "CREATE TABLE IF NOT EXISTS " + TBL_PATIENT + "(" +
            COL_TBLPATIENT_ID + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
            COL_TBLPATIENT_NAME + " VARCHAR(100) NOT NULL," +
            COL_TBLPATIENT_FAMILY + " VARCHAR(100) NOT NULL," +
            COL_TBLPATIENT_BIRTHDAY + " VARCHAR(12) NOT NULL," +
            COL_TBLPATIENT_CELL + " VARCHAR(12) NOT NULL," +
            COL_TBLPATIENT_PHONE + " VARCHAR(12) NOT NULL," +
            COL_TBLPATIENT_IDNUMBER + " VARCHAR(12) NOT NULL," +
            COL_TBLPATIENT_ADDRESS + " TEXT NOT NULL," +
            COL_TBLPATIENT_CITY + " VARCHAR(50) NOT NULL," +
            COL_TBLPATIENT_DISEASE + " TEXT NOT NULL," +
            COL_TBLPATIENT_DESC + " TEXT);";
    private static final String QUERY_TBLVISIT = "CREATE TABLE IF NOT EXISTS " + TBL_VISIT + "(" +
            COL_TBLVISIT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COL_TBLVISIT_ID_PATIENT + " INTEGER NOT NULL REFERENCES " + TBL_PATIENT + "(" + COL_TBLPATIENT_ID + ")," +
            COL_TBLVISIT_DATE_VISIT + " VARCHAR(12)," +
            COL_TBLVISIT_ID_SOLD_DRUG + " INTEGER NOT NULL REFERENCES " + TBL_DRUG + "(" + COL_TBLDRUG_ID + "));";
    private static final String QUERY_TBLDRUG = "CREATE TABLE IF NOT EXISTS " + TBL_DRUG + "(" +
            COL_TBLDRUG_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            COL_TBLDRUG_NAME + " VARCHAR(100));";
    private static final String QUERY_TBLSOLDDRUG = "CREATE TABLE IF NOT EXISTS " + TBL_SOLD_DRUG + "(" +
            COL_SOLD_DRUG_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            COL_SOLD_DRUG_PATIENT_ID + " INTEGER NOT NULL REFERENCES " + TBL_PATIENT + "(" + COL_TBLPATIENT_ID + ")," +
            COL_SOLD_DRUG_DRUG_ID + " INTEGER NOT NULL REFERENCES " + TBL_DRUG + "(" + COL_TBLDRUG_ID + "));";
    Context context;

    public PatientDatabase(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(QUERY_TBLPATINET);
        sqLiteDatabase.execSQL(QUERY_TBLDRUG);
        sqLiteDatabase.execSQL(QUERY_TBLSOLDDRUG);
        sqLiteDatabase.execSQL(QUERY_TBLVISIT);
    }

    public long addPatient(String name, String family, String birthDay, String cell, String phone, String idNumber, String address, String city, String disease, String desc) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_TBLPATIENT_NAME, name);
        contentValues.put(COL_TBLPATIENT_FAMILY, family);
        contentValues.put(COL_TBLPATIENT_BIRTHDAY, birthDay);
        contentValues.put(COL_TBLPATIENT_CELL, cell);
        contentValues.put(COL_TBLPATIENT_PHONE, phone);
        contentValues.put(COL_TBLPATIENT_IDNUMBER, idNumber);
        contentValues.put(COL_TBLPATIENT_ADDRESS, address);
        contentValues.put(COL_TBLPATIENT_CITY, city);
        contentValues.put(COL_TBLPATIENT_DISEASE, disease);
        contentValues.put(COL_TBLPATIENT_DESC, desc);
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        long result = sqLiteDatabase.insert(TBL_PATIENT, null, contentValues);
        sqLiteDatabase.close();
        if (result > 0) {
            return result;
        } else {
            return result;
        }
    }

    public Patient getPatientbyName(String name) {
        Patient patient = new Patient();
        String query = "SELECT * FROM tbl_patient WHERE name=" + name;
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor result = sqLiteDatabase.rawQuery(query, null);
        if (result.moveToFirst()) {
            do {
                patient.setName(result.getString(1));
                patient.setFamily(result.getString(2));
                patient.setBirth_day(result.getString(3));
                patient.setMobile(result.getString(4));
                patient.setPhone(result.getString(5));
                patient.setId_number(result.getString(6));
                patient.setAddress(result.getString(7));
                patient.setCity(result.getString(8));
                patient.setDisease(result.getString(9));
                patient.setDescription(result.getString(10));
            } while (result.moveToNext());
        }
        result.close();
        sqLiteDatabase.close();
        return patient;

    }

    public long addDrug(String drugName) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_TBLDRUG_NAME, drugName);
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        long result = sqLiteDatabase.insert(TBL_DRUG, null, contentValues);
        if (result > 0) {
            return result;
        } else {
            return result;
        }
    }

    public long addSoldDrug(int patientId, int drug) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_SOLD_DRUG_DRUG_ID, drug);
        contentValues.put(COL_SOLD_DRUG_PATIENT_ID, patientId);
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        long result = sqLiteDatabase.insert(TBL_SOLD_DRUG, null, contentValues);
        if (result > 0) {
            return result;
        } else {
            return result;
        }
    }

    public long addVisit(int patientId, String visitDate, int soldDrug) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_TBLVISIT_ID_PATIENT, patientId);
        contentValues.put(COL_TBLVISIT_DATE_VISIT, visitDate);
        contentValues.put(COL_TBLVISIT_ID_SOLD_DRUG, soldDrug);
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        long result = sqLiteDatabase.insert(TBL_VISIT, null, contentValues);
        if (result > 0) {
            return result;
        } else {
            return result;
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
