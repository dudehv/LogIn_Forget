package com.example.login_forget;

import static com.example.login_forget.DatabaseHelper.TABLE_NAME;
import static com.example.login_forget.DatabaseHelper.pass1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DBmanager {
    private DatabaseHelper dbHelper;
    private Context context;
    private SQLiteDatabase database;


    public DBmanager(Context c) {

        context = c;
    }



    public DBmanager open() throws SQLException {
        dbHelper = new DatabaseHelper(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {

        dbHelper.close();
    }

    public void insert(String fullname, String email, String password) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.EMAIL, email);
        contentValues.put(DatabaseHelper.FULLNAME, fullname);
        contentValues.put(DatabaseHelper.pass1, password);
        database.insert(TABLE_NAME, null, contentValues);
    }

    public Cursor fetch() {
        String[] columns = new String[]{DatabaseHelper.EMAIL, DatabaseHelper.FULLNAME, DatabaseHelper.pass1};
        Cursor cursor = database.query(TABLE_NAME, columns, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public int update(String Email, String Forget_Password) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.pass1, Forget_Password);
        int i = database.update(TABLE_NAME, contentValues, DatabaseHelper.EMAIL + " = ?", new String[]{Email});
        return i;
    }
    public String getPassagainstemail(String userid) {
        Cursor cursor= database.rawQuery("SELECT * FROM '"+TABLE_NAME+"' WHERE  "+DatabaseHelper.EMAIL+" = ?", new String[]{userid});
        String pass = "";
        if (((Cursor) cursor).moveToFirst()){
            do{
                int index=cursor.getColumnIndex(DatabaseHelper.pass1);
                if(index>-1) {
                    pass = cursor.getString(index);
                }
            }while(cursor.moveToNext());
        }
        cursor.close();
        return pass;
    }



}
