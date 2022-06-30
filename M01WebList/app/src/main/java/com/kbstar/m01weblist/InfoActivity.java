package com.kbstar.m01weblist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class InfoActivity extends AppCompatActivity {
    private String TAG = "InfoActivity";
    private TextView tv_id, tv_name, tv_idx, tv_level;
    private Button btnClose;
    private TextView display;

    private String jsonString;
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
        protected String doInBackground(String... params) {

            Log.d(TAG, "Param 1 : " + params[1]);

            String serverURL = params[0];
            String postParam = "idx=" + params[1];
            try {
                URL url = new URL(serverURL);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                connection.setConnectTimeout(5000);
                connection.setRequestMethod("POST");

                connection.connect();

                OutputStream outputStream = connection.getOutputStream();
                outputStream.write(postParam.getBytes("UTF-8"));
                outputStream.flush();
                outputStream.close();

                int resCode = connection.getResponseCode();
                Log.d(TAG, "Responce Code = " + resCode);

                InputStream inputStream;

                if(resCode == HttpURLConnection.HTTP_OK)
                    inputStream = connection.getInputStream();
                else
                    inputStream = connection.getErrorStream();

                InputStreamReader isr = new InputStreamReader(inputStream, "UTF-8");
                BufferedReader br = new BufferedReader(isr);
                //BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));

                //StringBuilder sb = new StringBuilder();
                String rcvString = "";
                String line = "";


                while((line=br.readLine()) != null)
                {
                    rcvString = rcvString + line;
                    //sb.append(line);
                    Log.d(TAG, line);
                }

                br.close();
                return rcvString;

            }catch(Exception e)
            {
                Log.d(TAG, "Exception : " + e.getMessage());
                return null;
            }

        }


        @Override
        protected void onPostExecute(String response) {
            super.onPostExecute(response);

            jsonString = response;
            showData();
        }
    }

    public void showData()
    {
        // jsonString, kbusers
        String JSON_TAG = "kbusers";
        String JSON_TAG_IDX = "idx";
        String JSON_TAG_NAME = "name";
        String JSON_TAG_ID = "id";
        String JSON_TAG_LEVEL = "level";

        try {
            JSONObject jsonObject = new JSONObject(jsonString);

            tv_idx.setText( jsonObject.getString(JSON_TAG_IDX) );
            tv_id.setText( jsonObject.getString(JSON_TAG_ID) );
            tv_name.setText( jsonObject.getString(JSON_TAG_NAME) );
            tv_level.setText( jsonObject.getString(JSON_TAG_LEVEL) );

        }catch (Exception e)
        {
            Log.d(TAG, "Exception : " + e.getMessage());
        }
    }
}