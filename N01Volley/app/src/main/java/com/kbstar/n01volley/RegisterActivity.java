package com.kbstar.n01volley;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class RegsiterActivity extends AppCompatActivity {

    private EditText inputId,inputName, inputPass;
    private Button btnInsert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regsiter);

        inputId = findViewById(R.id.inputId);
        inputName = findViewById(R.id.inputName);
        inputPass = findViewById(R.id.inputPass);

        btnInsert = findViewById(R.id.btnInsert);

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                regist();
            }
        });
    }

    public void regist()
    {

    }
}