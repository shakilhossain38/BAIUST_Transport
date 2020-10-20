package com.example.zmns.baiusttransport;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class StudentDatabase extends SQLiteOpenHelper {


    public static final String V_DATABASE_NAME = "Student.db";
    public static final String V_TABLE_NAME = "Student";
    public static final String V_STUDENT_ID = "STUDENT_ID";
    public static final String V_STUDENT_NAME = "STUDENT_NAME";
    public static final String V_STUDENT_PASSWORD = "STUDENT_PASSWORD";
    public static final String V_STUDENT_DEPT = "STUDENT_DEPT";
    public static final String V_STUDENT_LEVEL = "STUDENT_LEVEL";
    public static final String V_STUDENT_TERM = "STUDENT_TERM";
    public static final String V_STUDENT_TYPE = "STUDENT_TYPE";
    public static final String V_STUDENT_STATUS = "STUDENT_STATUS";



    public StudentDatabase(Context context) {
        super(context, V_DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL( " CREATE TABLE " + V_TABLE_NAME + " ( " + V_STUDENT_ID + " TEXT, " + V_STUDENT_NAME + " TEXT, " + V_STUDENT_PASSWORD + " TEXT, " + V_STUDENT_DEPT + " TEXT, " + V_STUDENT_LEVEL + " TEXT, " + V_STUDENT_TERM + " TEXT, " + V_STUDENT_TYPE + " Text, " + V_STUDENT_STATUS + " TEXT, PRIMARY KEY (" + V_STUDENT_ID + ") ); " );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL(" DROP TABLE IF EXISTS " + V_TABLE_NAME );
        onCreate(db);

    }

    public boolean addStudent(String id, String name, String dept, String level, String term, String type){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(V_STUDENT_ID, id);
        contentValues.put(V_STUDENT_NAME, name);
        contentValues.put(V_STUDENT_PASSWORD, "8888");
        contentValues.put(V_STUDENT_DEPT, dept);
        contentValues.put(V_STUDENT_LEVEL, level);
        contentValues.put(V_STUDENT_TERM, term);
        contentValues.put(V_STUDENT_TYPE, type);
        contentValues.put(V_STUDENT_STATUS, "Updated");

        long result = db.insert(V_TABLE_NAME, null, contentValues);

        return result != -1;

    }

    public ArrayList<StudentModelClass> viewStudentList(){

        ArrayList<StudentModelClass> arrayList = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(" SELECT * FROM " + V_TABLE_NAME, null);

        while (cursor.moveToNext()){

            String id = ": " + cursor.getString(0);
            String name = ": " + cursor.getString(1);
            String dept = ": " + cursor.getString(3);
            String level = ": " + cursor.getString(4);
            String term = ": " + cursor.getString(5);
            String type = ": " + cursor.getString(6);
            String status = ": " + cursor.getString(7);

            StudentModelClass studentModelClass = new StudentModelClass(id, name, dept, level, term, type, status);

            arrayList.add(studentModelClass);

        }

        return arrayList;

    }

    public Integer updateStudent(String id, String level, String term, String type, String status){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(V_STUDENT_ID, id);
        contentValues.put(V_STUDENT_LEVEL, level);
        contentValues.put(V_STUDENT_TERM, term);
        contentValues.put(V_STUDENT_TYPE, type);
        contentValues.put(V_STUDENT_STATUS, status);

        return db.update(V_TABLE_NAME, contentValues,  V_STUDENT_ID + " = ? ",  new String[] { id } );

    }

    public Integer removeStudent(String id){

        SQLiteDatabase db = this.getWritableDatabase();

        return db.delete(V_TABLE_NAME,  V_STUDENT_ID + " = ? ", new String[] { id } );

    }

    public Cursor studentLogin(String id){

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor result = db.rawQuery(" SELECT * FROM " + V_TABLE_NAME + " WHERE " + V_STUDENT_ID + " = ? " , new String[] { id });

        return result;

    }

    public Integer changePassword(String id, String cp){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(V_STUDENT_ID, id);
        contentValues.put(V_STUDENT_PASSWORD, cp);

        return db.update(V_TABLE_NAME, contentValues,  V_STUDENT_ID + " = ? ",  new String[] { id } );

    }

    public void resetStatus(String status){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(V_STUDENT_STATUS, status);

        String prevStatus = "Updated";

        //return db.update(V_TABLE_NAME, contentValues,  V_STUDENT_STATUS + " = ? ",  new String[] { status } );

       // db.execSQL( " UPDATE " + V_TABLE_NAME + " SET " + V_STUDENT_STATUS +  " = " + status + " ; " );

        db.update(V_TABLE_NAME, contentValues,  V_STUDENT_STATUS + " = ? ",  new String[] { prevStatus } );
    }

    public Cursor checker(String id){

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor result = db.rawQuery(" SELECT * FROM " + V_TABLE_NAME + " WHERE " + V_STUDENT_ID + " = ? " , new String[] { id });

        return result;

    }


}