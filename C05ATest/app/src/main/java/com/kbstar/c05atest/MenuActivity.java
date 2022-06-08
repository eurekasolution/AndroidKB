package com.kbstar.c05atest;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

public class MenuActivity extends AppCompatActivity {
    TextView textView;
    public static final String KEY_SIMPLE_DATA = "data";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        textView = findViewById(R.id.textView);
        Button button = findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra("name", "EurekaSolution");
                setResult(1004, intent);
                finish();
            }
        });

        Intent intent = getIntent();
        processIntent(intent);
    }

    private void processIntent(Intent intent) {
        if (intent != null) {
            Bundle bundle = intent.getExtras();
            SimpleData data = bundle.getParcelable(KEY_SIMPLE_DATA);
            System.out.println("data = " + data.toString());
            if (data != null) {
                System.out.println("\t\t\t[+] data.message = " + data.message);
                textView.setText("전달 받은 데이터\nNumber : " + data.number
                        + "\nMessage : " + data.message);
            }
        }
    }


}
