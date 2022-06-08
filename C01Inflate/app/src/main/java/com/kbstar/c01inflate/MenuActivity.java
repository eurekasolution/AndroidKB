package com.kbstar.c01inflate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MenuActivity extends AppCompatActivity {

    private LinearLayout container;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        // 버튼, 컨테이너 객체 찾아오기
        button = findViewById(R.id.button);
        System.out.println("++++++++++++++++++++++++++++++ button : " + button.getText().toString());

        container = findViewById(R.id.container);

        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                LayoutInflater inflater = ( LayoutInflater )getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                inflater.inflate(R.layout.sub1, container, true);
                CheckBox checkBox = container.findViewById(R.id.checkBox);
                checkBox.setTag("Sub1 로딩 완료");

                button = container.findViewById(R.id.button2);
                System.out.println("======================= button2 : " + button.getText().toString());
            }
        });
    }
}