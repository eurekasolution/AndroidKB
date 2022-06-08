package com.kbstar.c04callintent;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    Button button;
    Button button2;
    Button button4;
    Button btnJustOpen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);
        button = findViewById(R.id.button);

        button2 = findViewById(R.id.button2);
        button4 = findViewById(R.id.button4);
        btnJustOpen = findViewById(R.id.btnJustOpen);

        btnJustOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
                startActivity(intent);
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String telNum = editText.getText().toString();
                // tel:010-1234-5678
                telNum = "tel:" + telNum;

                //Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse(telNum));
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("content://contacts/people"));
                startActivity(intent);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
                // 이 방법은 Deprecate 되었다.
                //startActivityForResult(intent, REQ_CODE_MENU);

                startActivityResult.launch(intent);
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                ComponentName component =
                        new ComponentName("com.kbstar.c04callintent",
                            "com.kbstar.c04callintent.MenuActivity");

                intent.setComponent(component);
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

    /*
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

     */
}