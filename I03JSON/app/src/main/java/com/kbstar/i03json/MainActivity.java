package com.kbstar.i03json;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity {
    private EditText input;
    private Button button;
    private TextView textView;

    private RequestQueue requestQueue; // q

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        input = findViewById(R.id.input);
        button = findViewById(R.id.button);
        textView = findViewById(R.id.textView);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                process();
            }
        });

        if(requestQueue == null)
        {
            requestQueue = Volley.newRequestQueue(getApplicationContext());
        }

    }

    public void printLog(String msg)
    {
        textView.append(msg + "\n");
    }

    public void process() {
        String url = input.getText().toString();

        textView.setText("");

        StringRequest request = new StringRequest(
                Request.Method.GET,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //textView.setText(response);
                        //printLog(response);
                        processJson(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        printLog("에러발생 : " + error.getMessage());
                    }
                }
        );

        request.setShouldCache(false);
        requestQueue.add(request);

    }

    public void processJson(String jsonData)
    {
        Gson gson = new Gson();
        MovieList movieList = gson.fromJson(jsonData, MovieList.class);

        printLog("movie size = " + movieList.boxOfficeResult.dailyBoxOfficeList.size());

        printLog("원본데이터\n\n" + jsonData);
    }
}