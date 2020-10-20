package com.example.zmns.baiusttransport;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ScheduleDatabase extends SQLiteOpenHelper {

    public static final String V_DATABASE_NAME = "BusSchedule.db";
    public static final String V_TABLE_NAME = "BusSchedule";
    public static final String V_TIME = "TIME";
    public static final String V_L_FROM = "L_FROM";
    public static final String V_L_TO = "L_TO";
    public static final String V_BUS_NO = "BUS_NO";



    public ScheduleDatabase(Context context) {

        super(context, V_DATABASE_NAME, null, 1);

    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL( " CREATE TABLE " + V_TABLE_NAME + " ( " + V_TIME + " TEXT, " + V_L_FROM  + " TEXT, " + V_L_TO + " TEXT, " + V_BUS_NO + " TEXT,  PRIMARY KEY ( " + V_TIME + " , " + V_L_FROM + " , " + V_L_TO + " ,  " + V_BUS_NO + " )); " );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + V_TABLE_NAME);
        onCreate(db);

    }

    public boolean addSchedule(String time, String from, String to, String busNo){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(V_TIME, time);
        contentValues.put(V_L_FROM, from);
        contentValues.put(V_L_TO, to);
        contentValues.put(V_BUS_NO, busNo);

        long result = db.insert(V_TABLE_NAME, null, contentValues);

        if(result == -1)
            return false;
        else
            return true;

    }

    public Cursor viewSchedule(){

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor result = db.rawQuery(" SELECT * FROM " + V_TABLE_NAME, null);

        return result;
    }

    public Integer updateSchedule(String time, String from, String to, String busNO){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(V_TIME, time);
        contentValues.put(V_L_FROM, from);
        contentValues.put(V_L_TO, to);
        contentValues.put(V_BUS_NO, busNO);

        return db.update(V_TABLE_NAME, contentValues,  V_BUS_NO + " = ? AND " + V_TIME + " = ? ",  new String[] { busNO , time } );

    }

    public Integer deleteSchedule(String time, String busNO){

        SQLiteDatabase db = this.getWritableDatabase();

        return db.delete(V_TABLE_NAME,  V_TIME + " = ? AND " + V_BUS_NO + " = ? ", new String[] { time, busNO } );

    }

    public Cursor findBus(String from, String to){

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor result = db.rawQuery(" SELECT * FROM " + V_TABLE_NAME + " WHERE " + V_L_FROM + " = ? AND " + V_L_TO + " = ? " , new String[] { from, to });

        return result;

    }
}
