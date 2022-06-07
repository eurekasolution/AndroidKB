package com.kbstar.b08dialog;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView textView;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                showMyDialog();
            }
        });
    }

    private void showMyDialog()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("다이얼로그 제목");
        builder.setMessage("광고를 수신하시겠습니까?");
        builder.setIcon(android.R.drawable.ic_dialog_info);

        builder.setPositiveButton("예(Y)", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String msg = "예 버튼을 클릭했습니다.";
                textView.setText(msg);
            }
        });

        builder.setNegativeButton("아니오(N)",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String msg = "No Button is Clicked";
                textView.setText(msg);
            }
        });

        builder.setNeutralButton("취소(C)",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String msg = "취소 !!!!";
                textView.setText(msg);
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();

    }
}