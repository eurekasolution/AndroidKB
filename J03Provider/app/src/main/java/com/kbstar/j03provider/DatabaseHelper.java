package com.kbstar.j02search;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static String dbName = "kbstar.db";
    public static int VERSION = 1;

    public DatabaseHelper(@Nullable Context context) {
        super(context, dbName, null, VERSION);
    }

    public DatabaseHelper(@Nullable Context context, @Nullable String name, int version) {
        super(context, name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        printLog("onCreate() Helper");

        String sql = "create table if not exists  user_table ( "
                + " idx integer ,  "
                + " name text, "
                + " age integer, "
                + " mobile text ,"
                + " primary key(idx) "
                + ")";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        printLog("onUpgrade()");

        if(newVersion >1)
        {
            db.execSQL("Drop table if exists " + dbName);
            String sql = "ALTER TABLE user_table "
                    + " add mobile text after age ";
            db.execSQL(sql);
        }

    }

    public void printLog(String msg)
    {
        Log.d("DBHelper ", "---------------------" + msg);
    }
}
