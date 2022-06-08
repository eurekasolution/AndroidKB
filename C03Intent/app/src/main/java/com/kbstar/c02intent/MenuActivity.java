package com.kbstar.c02intent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MenuActivity extends AppCompatActivity {

    Button button;
    EditText nameText;
    EditText companyText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        button = findViewById(R.id.button2);

        nameText = findViewById(R.id.nameText);
        companyText = findViewById(R.id.companyText);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                //intent.putExtra("name", "홍길동");
                //intent.putExtra("company", "국민은행");

                intent.putExtra("name", nameText.getText().toString());
                intent.putExtra("company", companyText.getText().toString());
                setResult(1004, intent);

                finish();
            }
        });
    }
}