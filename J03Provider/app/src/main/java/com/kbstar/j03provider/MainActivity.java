package com.kbstar.j03provider;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button btnInsert, btnUpdate, btnSelect, btnDelete;
    TextView debugText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnInsert = findViewById(R.id.btnInsert);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnSelect = findViewById(R.id.btnSelect);
        btnDelete = findViewById(R.id.btnDelete);
        debugText = findViewById(R.id.debugText);

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insert();
            }
        });

        btnSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                select();
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                update();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                delete();
            }
        });

    }

    public void insert() {

        String uriString = "content://com.kbstar.j03provider/member";
        Uri uri = new Uri.Builder().build().parse(uriString);
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);

        String[] columns = cursor.getColumnNames();

        for(int i = 0; i<columns.length; i++)
        {
            printDebug(i + " : " + columns[i]);
        }

        ContentValues values = new ContentValues();
        values.put("name", "홍길동");
        values.put("age", 11);
        values.put("mobile", "010-0000-0000");

        uri = getContentResolver().insert(uri, values);

        printDebug("INSERT : " + uri.toString());
    }

    public void printDebug(String msg)
    {
        debugText.append(msg + "\n");
    }

    public void select() {
        try {
            String uriString = "content://com.kbstar.j03provider/member";
            Uri uri = new Uri.Builder().build().parse(uriString);
            Cursor cursor = getContentResolver().query(uri, null, null, null, "idx DESC");

            String[] columns = new String[] { "idx", "name", "age", "mobile"};

            while(cursor.moveToNext())
            {
                printDebug("columns[0] = " + columns[0]);
                int idx = cursor.getInt(0);
                String name = cursor.getString(1);
                int age = cursor.getInt(2);
                String mobile = cursor.getString(3);

                printDebug("idx = " + idx + ", name = " + name + ", age = " + age + ",  mobile = " + mobile);
            }

        }catch(Exception e)
        {

        }
    }
    public void update() {
        String uriString = "content://com.kbstar.j03provider/member";
        Uri uri = new Uri.Builder().build().parse(uriString);
        ContentValues updateValue = new ContentValues();
        String[] selectionArgs = new String[] {"010-0000-0000"};
        updateValue.put("mobile", "010-2222-3333");
        int count = getContentResolver().update(uri, updateValue, "mobile = ?",selectionArgs);
        printDebug("Update Result : " + count);

    }
    public void delete() {
        String uriString = "content://com.kbstar.j03provider/member";
        Uri uri = new Uri.Builder().build().parse(uriString);
        String selection = "name = ?";
        String[] selectionArgs = new String[] {"홍길동"};

        int count = getContentResolver().delete(uri, selection, selectionArgs);
        printDebug("delete Result : " + count);
    }

}

/*
    Content Provider
        다른 앱에서 데이터를 접근하는 경우.. 대신 데이터를 접근할 수 있는
        컨텐츠 제공해준다.
        제공되는 데이터는 Resolver를 통해서 접근
        안드로이드는 4대 구성요소
            AndroidManifest 에 등록되어야한다.
            앱 보안
 */
