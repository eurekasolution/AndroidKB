package com.kbstar.c05parcellable;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public static final int REQ_CODE = 1000;
    Button btnOpen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnOpen = findViewById(R.id.btnOpen);
        btnOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
                MyData data = new MyData("MyKBSTAR",77);
                intent.putExtra("dataKey", data);

                MyData data2 = new MyData("국민은행",33);
                intent.putExtra("dataKey2", data2);

                Bundle bundle = intent.getExtras();
                MyData md = bundle.getParcelable("dataKey");

                startActivity(intent);
            }
        });
    }



}