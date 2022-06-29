package com.kbstar.m01weblist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class InfoActivity extends AppCompatActivity {

    private TextView tv_id, tv_name, tv_idx, tv_level;
    private Button btnClose;
    private TextView display;

    private String extraIdx;
    private String SERVER_URL = "http://10.5.5.200:8080/android/info.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        tv_idx = findViewById(R.id.tv_idx);
        tv_id = findViewById(R.id.tv_id);
        tv_name = findViewById(R.id.tv_name);
        tv_level = findViewById(R.id.tv_level);
        btnClose = findViewById(R.id.btnClose);
        display = findViewById(R.id.display);

        Intent intent = getIntent();
        if(intent != null)
        {
            Bundle bundle = intent.getExtras();
            String idx = bundle.getString("idx");

            extraIdx = idx;

            Log.d("InfoActivity", "idx = " + idx);
            display.setText("key idx = " + idx + "\n");
        }

        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        InfoActivity.GetData task = new InfoActivity.GetData();
        task.execute(SERVER_URL, extraIdx);
    }

    private class GetData extends AsyncTask<String, Void, String>
    {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... strings) {
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
        }
    }
}