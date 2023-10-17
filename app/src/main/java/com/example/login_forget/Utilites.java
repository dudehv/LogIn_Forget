package com.example.login_forget;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.util.Patterns;
import android.widget.TextView;
import android.widget.Toast;

public class Utilites {


    public static void showingToast(Context context) {
        Toast.makeText(context, "Pls check your Entered Value !! ", Toast.LENGTH_SHORT).show();
    }

    public static boolean isValid(String userfullname, String useremail, String userpass1, String userpass2) {
        Boolean isval=true;

        if (userfullname.isEmpty() && useremail.isEmpty() && userpass1.isEmpty() && userpass2.isEmpty()) {
            return false;
        } else {
            if (userfullname.length() < 2 || userfullname.length() > 8) {
                return false;
            }else{
                isval=true;
            }
            if (!Patterns.EMAIL_ADDRESS.matcher(useremail).matches()) {
                return false;
            }else{
                isval=true;
            }
            if (userpass1.length() == userpass2.length()) {
                if (!userpass1.equals(userpass2)) {
                    return false;
                }else{
                    isval=true;
                }
            } else {
                return false;
            }


        }
        return isval;
    }

    public static boolean isValid(String useremail, String userpass) {
        if (useremail.isEmpty() && userpass.isEmpty()) {

            return false;
        } else {
            if (!Patterns.EMAIL_ADDRESS.matcher(useremail).matches()) {
                return false;
            }
        }
        return true;
    }

    public static boolean saveIntosp(Context context, String userfullname, String useremail, String userpass1,String userpass2) {
                DBmanager sbm=new DBmanager(context);
                sbm.open();
                Cursor cursor = sbm.fetch();
                boolean isemailnotavail=true;
                if (((Cursor) cursor).moveToFirst()){
                    do{
                        int index=cursor.getColumnIndex("email");
                        if(index>-1) {
                            String dbemail = cursor.getString(index);
                            if (dbemail.equals(useremail)){
                                isemailnotavail= false;
                                break;
                            }
                        }
                    }while(cursor.moveToNext());
                }
                cursor.close();
                if (isemailnotavail) {
                    sbm.open().insert(userfullname, useremail, userpass1);
                }
        return isemailnotavail;
    }


    public static int cheackAuthentication(LogIN logIN, String useremail, String userpass) {
        DBmanager sbm=new DBmanager(logIN);
        sbm.open();
        Cursor cursor = sbm.fetch();
        int isauthSuccess=000;
        if (((Cursor) cursor).moveToFirst()){
            do{
                int index=cursor.getColumnIndex("email");
                int indexpass=cursor.getColumnIndex("password");
                if(index>-1 && indexpass>-1) {
                    String dbemail = cursor.getString(index);
                    String dbpassword = cursor.getString(indexpass);
                    if (dbemail.equals(useremail)){
                        if(dbpassword.equals(userpass)){
                        isauthSuccess= 101;
                        break;
                        }else {
                        isauthSuccess=102;
                         }
                    }else{
                        isauthSuccess=103;
                    }
                }else {
                    isauthSuccess=102;
                }
            }while(cursor.moveToNext());
        }
        cursor.close();
        return isauthSuccess;
    }
    public static boolean isValPass(String newpass1, String newpass2, String userid) {
        if(newpass1.equalsIgnoreCase(newpass2)){
           return true;
        }else {
            return false;
        }
    }
    public static void updatepass(String newpass1, String userid,Context context) {
        DBmanager sbm=new DBmanager(context);
        sbm.open();
        int i=sbm.update(userid,newpass1);
        Log.d("pass",""+i);
    }
    public static String getPassagainstemail(String userid,Context context){
        DBmanager sbm=new DBmanager(context);
        sbm.open();
        String strpass=sbm.getPassagainstemail(userid);
        return strpass;
    }
}

