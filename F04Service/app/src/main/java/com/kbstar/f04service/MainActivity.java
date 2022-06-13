package com.kbstar.f04service;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private Button button;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button);
        editText = findViewById(R.id.editText);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String msg = editText.getText().toString();

                Intent intent = new Intent(
                                        getApplicationContext(),
                                        MyService.class);
                intent.putExtra("msg", msg);
                intent.putExtra("name", "김국민");
                // 필요한 부가정보를 계속 추가
                startService(intent);
            }
        });

        // 하고싶은 일이 있으면..추가
    }
}