package com.kbstar.c05atest;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public static final int REQUEST_CODE_MENU = 1001;
    public static final String KEY_SIMPLE_DATA = "data";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
                SimpleData data = new SimpleData(333, "국민은행");
                intent.putExtra(KEY_SIMPLE_DATA, data);
                //intent.putExtra("REQUEST_CODE", REQUEST_CODE_MENU);
                //startActivityResult.launch(intent);
                startActivity(intent);
                //startActivityForResult(intent, REQUEST_CODE_MENU);
            }
        });
    }

    // 콜백 함수를 만들어서 처리하는 것으로 바뀌었다.이전과 같다.
    ActivityResultLauncher<Intent> startActivityResult = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == 1004) {
                        String name = result.getData().getStringExtra("name");
                        Toast.makeText(getApplicationContext(), "응답으로 전달된 name : " + name,
                                Toast.LENGTH_LONG).show();
                    }
                }
            });

}
