package com.kbstar.b05orientation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private String name;
    private EditText editText;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        printToast("onCreate()");
        editText = findViewById(R.id.editText);
        button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                name = editText.getText().toString();

               //String context = getApplicationContext().toString();

                printToast("버튼이 클릭되었습니다. 이름 저장 : " + name );
            }
        });

        if(savedInstanceState != null)
        {
            name = savedInstanceState.getString("name");
            printToast(getApplicationContext() + "값을 복구했습니다.");
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("name", name);
    }

    @Override
    protected void onStart() {
        super.onStart();
        printToast("onStart()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        printToast("onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        printToast("onDestroy()");
    }

    public void printToast(String msg)
    {
        Toast.makeText(this,msg,  Toast.LENGTH_LONG).show();
    }
}