package com.example.zmns.baiusttransport;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MessageDatabase extends SQLiteOpenHelper {

    public static final String V_DATABASE_NAME = "Message.db";
    public static final String V_TABLE_NAME = "Message";
    public static final String V_STUDENT_ID = "STUDENT_ID";
    public static final String V_MESSAGE = "MESSAGE";

    public MessageDatabase(Context context) {
        super(context, V_DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL( " CREATE TABLE " + V_TABLE_NAME + " ( " + V_STUDENT_ID + " TEXT , " + V_MESSAGE + " TEXT, PRIMARY KEY ( " + V_STUDENT_ID + " , " + V_MESSAGE + " ) ) ; " );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL(" DROP TABLE IF EXISTS " + V_TABLE_NAME );
        onCreate(db);

    }

    public boolean addSuggestion(String id, String message){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(V_STUDENT_ID, id);
        contentValues.put(V_MESSAGE, message);

        long result = db.insert(V_TABLE_NAME, null, contentValues);

        return result != -1;

    }

    public Cursor viewMessage(){

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor result = db.rawQuery(" SELECT * FROM " + V_TABLE_NAME, null);

        return result;
    }

}
