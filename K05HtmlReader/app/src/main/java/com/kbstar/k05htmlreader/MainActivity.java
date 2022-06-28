package com.kbstar.k05htmlreader;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.Buffer;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "HtmlReader";
    private EditText editText;
    private Button button;
    private TextView textView;

    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);
        button = findViewById(R.id.button);
        textView = findViewById(R.id.textView);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String inputString = editText.getText().toString();

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        requestData(inputString);
                    }
                }).start();

            }
        });
    }

    public void requestData(String urlData)
    {
        StringBuilder output = new StringBuilder();

        clearLog();

        try {

            URL url = new URL(urlData);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            if(conn != null)
            {
                conn.setConnectTimeout(10000);
                conn.setRequestMethod("GET");
                conn.setDoInput(true); // InputStream 으로 서버로부터 응답받음

                int responseCode = conn.getResponseCode();
                BufferedReader reader = new BufferedReader(
                                            new InputStreamReader(
                                                conn.getInputStream()
                                            )
                                        );
                String line = "";
                int counter = 0;
                while(true)
                {
                    line = reader.readLine();
                    if(line == null)
                        break;
                    output.append( ++counter + " : " + line + "\n");
                }

                reader.close();
                conn.disconnect();

            }

        }catch(Exception e)
        {
            printLog("Exception : " + e.getMessage());
        }

        printLog(output.toString());

    }

    private void printLog(String log)
    {
        handler.post(new Runnable() {
            @Override
            public void run() {
                textView.append(log + "\n");
            }
        });
    }

    private void clearLog()
    {
        handler.post(new Runnable() {
            @Override
            public void run() {
                textView.setText("");
            }
        });
    }
}