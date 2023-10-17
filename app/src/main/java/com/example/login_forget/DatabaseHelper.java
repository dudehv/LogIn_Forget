package com.example.login_forget;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    //table name
    public static final String TABLE_NAME = "Register";
    //columns
    public static final String EMAIL = "email";
    public static final String FULLNAME = "fullname";
    public static String pass1 = "password";
    static final String DB_NAME = "Database";
    static final int DB_VERSION = 1;
    private static final String CREATE_TABLE = "create table " + TABLE_NAME + "(" + EMAIL
            + " TEXT PRIMARY KEY, " + FULLNAME + " TEXT , " + pass1 + " TEXT);";
    ;


    public DatabaseHelper(@Nullable Context context) {

        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }
}

