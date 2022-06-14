package com.kbstar.g02permission;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
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

        confirmPermission(permission);

    }

    public void confirmPermission(String[] permission)
    {
        // 1. 현재까지 권한 있는지, 거절되었는지 확인
        ArrayList<String> list = new ArrayList<String>();

        for(int i=0; i<permission.length; i++)
        {
            String currentPermission = permission[i];

        }

        // 2. 위험 권한 부여 요청
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode == 101)
        {
            if(grantResults.length >0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                textView.setText("권한 승인하였습니다.");
            }else
            {
                textView.setText("권한 거절하였습니다.");
            }
        }
    }
}