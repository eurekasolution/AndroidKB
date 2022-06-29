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

import org.json.JSONArray;
import org.json.JSONObject;

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
        recyclerView.setAdapter(adapter);

        btnList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                display.setText("Start Get List ...\n");
                arrayList.clear();
                adapter.notifyDataSetChanged();

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

        @Override
        protected void onPostExecute(String response) {
            super.onPostExecute(response);

            if(response != null)
            {
                display.setText(response);
                jsonString = response;
                showList();
            }

        }
    }

    public void showList()
    {
        // jsonString, kbusers
        String JSON_TAG = "kbusers";
        String JSON_TAG_IDX = "idx";
        String JSON_TAG_NAME = "name";
        String JSON_TAG_ID = "id";
        String JSON_TAG_LEVEL = "level";

        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            JSONArray jsonArray = jsonObject.getJSONArray(JSON_TAG);

            Log.d(TAG, "json list length = " + jsonArray.length());

            for(int i=0; i<jsonArray.length(); i++)
            {
                JSONObject item = jsonArray.getJSONObject(i);


                UserData userData = new UserData();

                userData.setUserIdx( item.getString(JSON_TAG_IDX) );
                userData.setUserName( item.getString(JSON_TAG_NAME) );
                userData.setUserId( item.getString(JSON_TAG_ID) );
                userData.setUserLevel( item.getString( JSON_TAG_LEVEL) );

                Log.d(TAG, " i = " + i + ", name =" + item.getString(JSON_TAG_NAME));

                arrayList.add(userData);
                adapter.notifyDataSetChanged(); // 새로고침
            }

            Log.d(TAG, "array List size = " + arrayList.size());
        }catch (Exception e)
        {
            Log.d(TAG, "Exception : " + e.getMessage());
        }
    }

}