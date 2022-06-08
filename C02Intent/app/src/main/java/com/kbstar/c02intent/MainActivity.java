package com.kbstar.c02intent;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public static final int REQ_CODE_MENU = 1001;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
                // org
                //startActivityForResult(intent, REQ_CODE_MENU);
                startActivityResult.launch(intent);
            }
        });
    }

    // 콜백 함수(자동으로 호출되는 함수)를 만들어서 처리하는 것으로 최근 바뀜
    ActivityResultLauncher<Intent> startActivityResult = registerForActivityResult(
        new ActivityResultContracts.StartActivityForResult(),
        new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
               String name = result.getData().getStringExtra("name");
               String company = result.getData().getStringExtra("company");
               int resultCode = result.getResultCode();

               if(resultCode == 1004)
               {
                   Toast.makeText(getApplicationContext(),
                           "응답 이름 : "+ name + ", " + company ,
                           Toast.LENGTH_LONG).show();
               }
            }
        }
    );

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}