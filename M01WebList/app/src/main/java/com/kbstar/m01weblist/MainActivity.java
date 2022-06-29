package com.kbstar.m01weblist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private String SERVER_URL = "http://10.5.5.200:8080/android/list.php";

    private RecyclerView recyclerView;
    private Button btnList;
    private TextView display;

    private String TAG = "WEB-LIST";

    private ArrayList<UserData> arrayList;
    private UserAdapter adapter;
    private String jsonString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        btnList = findViewById(R.id.btnList);
        display = findViewById(R.id.display);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        arrayList = new ArrayList<>();
        adapter = new UserAdapter(this, arrayList);

        btnList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                display.setText("Start Get List ...\n");
                arrayList.clear();

                GetData task = new GetData();
                task.execute(SERVER_URL, "NOPARAM");
            }
        });
    }

    private class GetData extends AsyncTask<String, Void, String>
    {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
        }

        @Override
        protected String doInBackground(String... params) {

            Log.d(TAG, "Param 1 : " + params[1]);

            String serverURL = params[0];
            String postParam = "dummy=" + params[1];
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
    }

}