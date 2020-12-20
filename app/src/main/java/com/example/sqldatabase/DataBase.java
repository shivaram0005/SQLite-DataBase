package com.example.sqldatabase;

import android.app.TaskStackBuilder;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DataBase extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "STUDENT.db";
    public static final String TABLE_NAME = "STUDENTDATA";
    public static final String col1 = "ID";
    public static final String col2 = "NAME";
    public static final String col3 = "SURNAME";
    public static final String col4 = "PHONENUMBER";

    public DataBase(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT,NAME TEXT,SURNAME TEXT,PHONENUMBER INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String name, String surname, String phonenumber) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(col2, name);
        contentValues.put(col3, surname);
        contentValues.put(col4, phonenumber);
        long result = sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Cursor getData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor=db.rawQuery("Select * from " +TABLE_NAME,null);
        return cursor;

    }

    public boolean updateData(String id, String name, String surname, String phonenumber) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(col1, id);
        contentValues.put(col2, name);
        contentValues.put(col3, surname);
        contentValues.put(col4, phonenumber);
        sqLiteDatabase.update(TABLE_NAME, contentValues, "ID=?", new String[]{id});
        return true;
    }

    public Integer daleteData(String id) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        return sqLiteDatabase.delete(TABLE_NAME, "ID=?", new String[]{id});

    }

}
