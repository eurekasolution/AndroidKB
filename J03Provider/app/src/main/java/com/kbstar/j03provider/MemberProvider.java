package com.kbstar.j03provider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.provider.ContactsContract;
import android.util.Log;

public class MemberProvider extends ContentProvider {

    private SQLiteDatabase db;
    private DatabaseHelper dbHelper;

    public static String AUTH = "com.kbstar.j03provider";
    public static String BASE = "member";

    private static final int MEMBERS = 1;
    private static final int MEMBER_ID = 2;

    static final Uri CONTENT_URI = Uri.parse("content://" + AUTH + "/" + BASE);

        //   contents://com.kbstar.j03provider/member/test

    public MemberProvider() {
    }


    public static final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    static {
        uriMatcher.addURI(AUTH, BASE, MEMBERS);
        uriMatcher.addURI(AUTH, BASE + "/#", MEMBER_ID);

        // contents://com.kbstar.j03provider/member 1 첫번째 데이터
        // contents://com.kbstar.j03provider/member/#2  , idx 2
    }

        // content://com.kbstar.j03provider/1

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int count = 0;
        if(uriMatcher.match(uri) == MEMBERS)
        {
            count = db.delete(DatabaseHelper.TABLE, selection, selectionArgs);
        }else
        {
            throw new IllegalArgumentException("Unknown URI " + uri);
        }

        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }

    @Override
    public String getType(Uri uri) {
        if(uriMatcher.match(uri) == MEMBERS)
            return "vnd.android.curor.dir/member";
        else
            throw new IllegalArgumentException("Unknown URI" + uri);
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        long id = db.insert(DatabaseHelper.TABLE, null, values);

        if(id >0) {
            Uri tmpUri = ContentUris.withAppendedId(CONTENT_URI, id);
            getContext().getContentResolver().notifyChange(tmpUri, null);
            Log.d("XXXXXX", tmpUri.getHost());
            return tmpUri;
        }

        throw new SQLException("INSERT ERROR : URI = " + uri);
    }

    @Override
    public boolean onCreate() {
        // TODO: Implement this to initialize your content provider on startup.
        // Stub
        dbHelper = new DatabaseHelper(getContext());
        db = dbHelper.getWritableDatabase();

        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {

        Cursor cursor;
        if(uriMatcher.match(uri) == MEMBERS)
        {
            cursor = db.query(DatabaseHelper.TABLE, DatabaseHelper.DBCOLUMNS, selection, null, null, null, DatabaseHelper.NAME + " ASC");

            Log.d("Resolver " , "------- POINT 1");
        }else
        {
            Log.d("Resolver " , "------- POINT 2");
            throw new IllegalArgumentException("Unknown URI " + uri);
        }

        Log.d("Resolver " , "------- POINT 3");
        cursor.setNotificationUri(getContext().getContentResolver(), uri);
        Log.d("Resolver " , "------- POINT 4");
        return cursor;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        int count = 0;
        if(uriMatcher.match(uri) == MEMBERS)
        {
            count = db.update(DatabaseHelper.TABLE, values, selection, selectionArgs);
        }else
        {
            throw new IllegalArgumentException("Unknown URI " + uri);
        }

        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }

}