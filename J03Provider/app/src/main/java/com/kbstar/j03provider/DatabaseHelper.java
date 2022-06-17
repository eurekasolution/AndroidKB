package com.kbstar.j03provider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {


    public static String DB = "member.db";
    public static int VERSION = 1;

    public static final String IDX = "idx";
    public static final String NAME = "name";
    public static final String AGE = "age";
    public static final String MOBILE = "mobile";

    public static final String TABLE = "member";

    public static final String[] DBCOLUMNS = {IDX, NAME, AGE, MOBILE };

    public static final String CREATE
            = "CREATE table " + TABLE + "("
                   + IDX + " INTEGER, "
                   + NAME + " TEXT, "
                   + AGE + " INTEGER, "
                   + MOBILE + " TEXT, "
                    + " primary key(" + IDX + ")"
              + ")";


    public DatabaseHelper(@Nullable Context context) {
        super(context, DB, null, VERSION);
    }

    public DatabaseHelper(@Nullable Context context, @Nullable String name, int version) {
        super(context, name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        printLog("onCreate() Helper");
        db.execSQL(CREATE); // create table ...
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        printLog("onUpgrade()");

        if(newVersion >1)
        {
            db.execSQL("Drop table if exists " + DB);

            db.execSQL(CREATE);
        }

    }

    public void printLog(String msg)
    {
        Log.d("DBHelper ", "---------------------" + msg);
    }
}
