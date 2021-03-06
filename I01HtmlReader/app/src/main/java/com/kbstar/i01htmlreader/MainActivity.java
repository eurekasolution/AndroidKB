package com.kbstar.i01htmlreader;

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

public class MainActivity extends AppCompatActivity {

    private EditText input;
    private Button button;
    private TextView textView;

    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        input = findViewById(R.id.input);
        button = findViewById(R.id.button);
        textView = findViewById(R.id.textView);

        /*
        웹 서버에 데이터 요청/응답 : HttpURLConnection
        요청, 응답에 코드 양이 많아지는 문제 발생
        비정상 종료가 가능
            해결하자 : 라이브러리
            Volley 라이브러리
         */

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = input.getText().toString();

                textView.setText("");

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        requestToServer(url);
                    }
                }).start();
            }
        });
    }

    public void requestToServer(String urlParam)
    {
        StringBuilder printString = new StringBuilder();

        try {
            URL url = new URL(urlParam);


            HttpURLConnection conn = ( HttpURLConnection ) url.openConnection();
            if(conn != null)
            {
                conn.setConnectTimeout(10000);
                // Inpustream으로 서버로 부터 응답을 받겠다.
                conn.setDoInput(true);
                conn.setRequestMethod("GET");
                // GET : HTML Header , 255, 보안에  취약

                int requestCode = conn.getResponseCode();
                BufferedReader reader = new BufferedReader(
                                            new InputStreamReader(conn.getInputStream())
                                        );
                String line = null;

                while(true)
                {
                    line = reader.readLine();
                    if(line == null)
                        break;
                    printString.append(line + "\n");

                }

                reader.close();
                conn.disconnect();
            }

        }catch (Exception e)
        {
            printLog("예외발생 : " + e.getMessage());
        }

        printLog(printString.toString());

    }

    public void printLog(String msg)
    {
        handler.post(new Runnable() {
            @Override
            public void run() {
                textView.append(msg + "\n");
                //for(int i=1; i<=100000; i++)
                //    textView.append("todo : " + i);
            }
        });
    }


}