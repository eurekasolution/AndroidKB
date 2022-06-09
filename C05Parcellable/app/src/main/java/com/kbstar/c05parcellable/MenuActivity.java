package com.kbstar.c05parcellable;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MenuActivity extends AppCompatActivity {
    private TextView display;
    private Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        display = findViewById(R.id.display);

        //버튼 클릭하면 ..이벤트 처리..
        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();
            }
        });

        System.out.println("================================== 1");

        Intent intent = getIntent();

        printIntentInfo(intent);
    }

    private void printIntentInfo(Intent intent)
    {
        if(intent!= null)
        {
            Bundle bundle = intent.getExtras();
            MyData data = bundle.getParcelable("dataKey");
            MyData data2 = bundle.getParcelable("dataKey2");
            System.out.println("RCV+++++++++++++++++++++++++++++++++++++++++++");
            System.out.println("name = " + data.getName());
            System.out.println("age = " + data.getAge());
            if(data != null)
            {
                //System.out.println("--------------------------------");
                //System.out.println("name = " + data.getName());
                //System.out.println("age = " + data.getAge());
                display.setText("이름 : " + data2.getName() + "\n나이 : " + data2.getAge());
            }
            if(data2 != null)
            {
                //System.out.println("--------------------------------");
                //System.out.println("name = " + data.getName());
                //System.out.println("age = " + data.getAge());
                display.append("\n이름 : " + data.getName() + "\n나이 : " + data.getAge());
            }

        }
    }
}