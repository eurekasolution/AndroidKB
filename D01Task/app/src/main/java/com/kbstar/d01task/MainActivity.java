package com.kbstar.d01task;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button button;
    EditText textName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        showToast("onCreate()");

        button = findViewById(R.id.button);
        textName = findViewById(R.id.textName);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext()
                        , MenuActivity.class);

                startActivity(intent);
            }
        });


    }


    @Override
    protected void onStart() {
        super.onStart();
        showToast("onStart()");

        saveState();
    }

    private void saveState()
    {
        SharedPreferences sp = getSharedPreferences("sharedKey", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("name", textName.getText().toString());
        editor.commit();
    }

    private void clearState()
    {
        SharedPreferences sp = getSharedPreferences("sharedKey", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.clear();
        editor.commit();
    }

    private void restoreState()
    {
        SharedPreferences sp = getSharedPreferences("sharedKey", Activity.MODE_PRIVATE);

        if(sp != null && sp.contains("name")) {
            // SharedPreferences.Editor editor = sp.edit();

            String name = sp.getString("name", "");
                            // getString("key", "defaultValue");
            textName.setText(name);
            showToast("restored Name = " + name);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        showToast("onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        showToast("onDestroy()");

        clearState();
    }

    @Override
    protected void onPause() {
        super.onPause();
        showToast("onPause()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        showToast("onResume()");

        restoreState();
    }

    public void showToast(String msg)
    {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }
}