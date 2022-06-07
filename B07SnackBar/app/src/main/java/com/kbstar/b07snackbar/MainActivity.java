package com.kbstar.b07snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button2);

        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "스낵바 테스트", Snackbar.LENGTH_LONG).show();
            }
        });

    }
}