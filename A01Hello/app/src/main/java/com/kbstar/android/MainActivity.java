package com.kbstar.android;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);

        /*
        JS
        var btn = document.getElementById('btn');
        let btn = document.querySelector('#btn');
        let btn2 = $("#btn")
         */

        textView = findViewById(R.id.textView);
        System.out.println("-------------------- " + textView.getText().toString() );


    }
}