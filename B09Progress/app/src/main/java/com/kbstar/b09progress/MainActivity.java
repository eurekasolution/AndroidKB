package com.kbstar.b09progress;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {

    ProgressBar progress;
    Button button;
    Button button2;
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progress = findViewById(R.id.progressBar); // 막대형
        progress.setIndeterminate(false);
        progress.setProgress(70);

        // STEP C-01

        button = findViewById(R.id.button);
        button2 = findViewById(R.id.button2);

        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                // 다이얼로그형 프로그레스 바

                dialog = new ProgressDialog(MainActivity.this);
                dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                dialog.setMessage("스피너 테스트 중입니다.");
                dialog.show();
            }
        });

    }

    public void kbClick(View view)
    {
        System.out.println("------------------------------ kbClick");
        if(dialog != null)
        {
            dialog.dismiss();
        }
    }
}