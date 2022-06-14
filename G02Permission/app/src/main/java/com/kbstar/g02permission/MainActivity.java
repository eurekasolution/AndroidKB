package com.kbstar.g02permission;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);

        String[] permission = {
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
        };

        //textView.setText("READ: " + Manifest.permission.READ_EXTERNAL_STORAGE);
        confirmPermission(permission);

    }

    public void confirmPermission(String[] permission)
    {
        // 1. 현재까지 권한 있는지, 거절되었는지 확인
        ArrayList<String> list = new ArrayList<String>();

        for(int i=0; i<permission.length; i++)
        {
            String currentPermission = permission[i];

            int check = ContextCompat.checkSelfPermission(this, currentPermission);

            if(check == PackageManager.PERMISSION_GRANTED)
            {
                textView.append("\n" + currentPermission + " 권한 있음.");
            }else
            {
                textView.append("\n" + currentPermission + " 권한 없음 XXXXX.");

                // 권한이 없으면 다시 물어보기(권한 설명..)
                if(ActivityCompat.shouldShowRequestPermissionRationale(this, currentPermission))
                {
                    textView.append("\n권한 설명이 필요합니다.");
                }else
                {
                    list.add(currentPermission);
                }
            }

        }

        Log.d("XXXXX", "============== list size = " + list.size());
        // 2. 위험 권한 부여 요청
        String[] targetArray = new String[list.size()];
        list.toArray(targetArray);

        if(list.size() >0)
            ActivityCompat.requestPermissions(this, targetArray, 101);

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode == 101)
        {
            if(grantResults.length >0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                textView.append("\n권한 승인하였습니다.");
            }else
            {
                textView.append("\n권한 거절하였습니다.");
            }
        }
    }
}