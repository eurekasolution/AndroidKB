package com.kbstar.c04callintent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);
        button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String telNum = editText.getText().toString();
                // tel:010-1234-5678
                telNum = "tel:" + telNum;

                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(telNum));
                startActivity(intent);
            }
        });

    }
}