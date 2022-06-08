package com.kbstar.c04callintent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity {

    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        button = findViewById(R.id.button3);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                //intent.putExtra("name", "홍길동");
                //intent.putExtra("company", "국민은행");

                //intent.putExtra("name", nameText.getText().toString());
                //intent.putExtra("company", companyText.getText().toString());
                setResult(RESULT_OK, intent);

                finish();
            }
        });
    }
}