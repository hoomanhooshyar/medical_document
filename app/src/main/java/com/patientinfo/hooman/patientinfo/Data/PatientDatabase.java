package com.patientinfo.hooman.patientinfo.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
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
            COL_TBLPATIENT_NAME + " NVARCHAR(100) NOT NULL," +
            COL_TBLPATIENT_FAMILY + " NVARCHAR(100) NOT NULL," +
            COL_TBLPATIENT_BIRTHDAY + " NVARCHAR(12) NOT NULL," +
            COL_TBLPATIENT_CELL + " NVARCHAR(12) NOT NULL," +
            COL_TBLPATIENT_PHONE + " NVARCHAR(12) NOT NULL," +
            COL_TBLPATIENT_IDNUMBER + " NVARCHAR(12) NOT NULL," +
            COL_TBLPATIENT_ADDRESS + " TEXT NOT NULL," +
            COL_TBLPATIENT_CITY + " NVARCHAR(50) NOT NULL," +
            COL_TBLPATIENT_DISEASE + " TEXT NOT NULL," +
            COL_TBLPATIENT_DESC + " TEXT);";
    private static final String QUERY_TBLVISIT = "CREATE TABLE IF NOT EXISTS " + TBL_VISIT + "(" +
            COL_TBLVISIT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COL_TBLVISIT_ID_PATIENT + " INTEGER NOT NULL REFERENCES " + TBL_PATIENT + "(" + COL_TBLPATIENT_ID + ")," +
            COL_TBLVISIT_DATE_VISIT + " NVARCHAR(12)," +
            COL_TBLVISIT_ID_SOLD_DRUG + " INTEGER NOT NULL REFERENCES " + TBL_DRUG + "(" + COL_TBLDRUG_ID + "));";
    private static final String QUERY_TBLDRUG = "CREATE TABLE IF NOT EXISTS " + TBL_DRUG + "(" +
            COL_TBLDRUG_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            COL_TBLDRUG_NAME + " NVARCHAR(100));";
   /* private static final String QUERY_TBLSOLDDRUG = "CREATE TABLE IF NOT EXISTS " + TBL_SOLD_DRUG + "(" +
            COL_SOLD_DRUG_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            COL_SOLD_DRUG_PATIENT_ID + " INTEGER NOT NULL REFERENCES " + TBL_PATIENT + "(" + COL_TBLPATIENT_ID + ")," +
            COL_SOLD_DRUG_DRUG_ID + " INTEGER NOT NULL REFERENCES " + TBL_DRUG + "(" + COL_TBLDRUG_ID + "));";*/
    Context context;

    public PatientDatabase(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(QUERY_TBLPATINET);
        sqLiteDatabase.execSQL(QUERY_TBLDRUG);
       // sqLiteDatabase.execSQL(QUERY_TBLSOLDDRUG);
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

    public Cursor getPatientbyName(CharSequence name) {
        // Patient patient = new Patient();
        String query = "SELECT * FROM " + TBL_PATIENT + " WHERE " + COL_TBLPATIENT_FAMILY + " LIKE '" + name + "%';";
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        return sqLiteDatabase.rawQuery(query, null);
    }

    public Cursor getPatientbyDisease(CharSequence disease) {
        String query = "SELECT * FROM " + TBL_PATIENT + " WHERE " + COL_TBLPATIENT_DISEASE + " LIKE '" + disease + "%';";
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        return sqLiteDatabase.rawQuery(query, null);
    }

    public Cursor getPatientbyIdNumber(CharSequence idnumber) {
        String query = "SELECT * FROM " + TBL_PATIENT + " WHERE " + COL_TBLPATIENT_IDNUMBER + " LIKE '" + idnumber + "%';";
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        return sqLiteDatabase.rawQuery(query, null);
    }

    public Cursor getPatientbyCity(CharSequence city) {
        String query = "SELECT * FROM " + TBL_PATIENT + " WHERE " + COL_TBLPATIENT_CITY + " LIKE '" + city + "%';";
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        return sqLiteDatabase.rawQuery(query, null);
    }
    public Cursor getDrug(){
        String query = "SELECT * FROM "+TBL_DRUG+";";
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        return sqLiteDatabase.rawQuery(query,null);
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
    public Cursor getSoldDrug(int patientId){
        String query = "SELECT "+COL_TBLDRUG_NAME+","+COL_TBLVISIT_DATE_VISIT+" FROM "+TBL_DRUG + " INNER JOIN "+TBL_VISIT+" ON "+
                TBL_VISIT+"."+COL_TBLVISIT_ID_SOLD_DRUG+" = "+TBL_DRUG+"."+COL_TBLDRUG_ID+" AND "+
                TBL_VISIT+"."+COL_TBLVISIT_ID_PATIENT+" = "+patientId+";";
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        return sqLiteDatabase.rawQuery(query,null);
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
    public long updatePatientDesc(int id,String desc){
        ContentValues contentValues = new ContentValues();
        contentValues.put("description",desc);
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        long result = sqLiteDatabase.update(TBL_PATIENT,contentValues,"id="+id,null);
        if(result > 0){
            return result;
        }else {
            return result;
        }

    }
    public boolean deleteUser(int id){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        boolean patientDelete =  sqLiteDatabase.delete(TBL_PATIENT,COL_TBLPATIENT_ID+" = "+id,null) >0;
        boolean visitDelete = sqLiteDatabase.delete(TBL_VISIT,COL_TBLVISIT_ID_PATIENT+" = "+id,null)>0;
        if(patientDelete){
            return true;
        }else{
            return false;
        }
    }
       /* public long addSoldDrug(int patientId, int drug,String date) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_TBLVISIT_ID_SOLD_DRUG, drug);
        contentValues.put(COL_TBLVISIT_ID_PATIENT, patientId);
        contentValues.put(COL_TBLVISIT_DATE_VISIT,date);
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        long result = sqLiteDatabase.insert(TBL_VISIT, null, contentValues);
        if (result > 0) {
            return result;
        } else {
            return result;
        }
    }*/

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
