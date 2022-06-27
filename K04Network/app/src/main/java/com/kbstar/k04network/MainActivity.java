package com.kbstar.k04network;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.net.ServerSocket;
import java.net.Socket;

// Log.d("MYTEST:
public class MainActivity extends AppCompatActivity {
    public static final String TAG = "MYNETWORK";

    private Button btnSend, btnStart;
    private EditText clientInput;
    private TextView clientDisplay, serverDisplay;

    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSend = findViewById(R.id.btnSend);
        btnStart = findViewById(R.id.btnStart);
        clientInput = findViewById(R.id.clientInput);
        clientDisplay = findViewById(R.id.clientDisplay);
        serverDisplay = findViewById(R.id.serverDisplay);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        startServer();
                    }
                }).start();
            }
        });

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String sendMsg = clientInput.getText().toString();

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        sendData(sendMsg);
                    }
                }).start();
            }
        });
    }

    private void sendData(String data)
    {

    }

    private void startServer()
    {
        int serverPort = 10001;
        try {
            ServerSocket server = new ServerSocket(serverPort);
            printServer("Server Start : #"+ serverPort);

        }catch (Exception e) {
            Log.d(TAG, "startServer Exception");
        }
    }

    public void printClient(String log)
    {
        handler.post(new Runnable() {
            @Override
            public void run() {
                clientDisplay.append(log + "\n");
            }
        });
    }
    public void printServer(String log)
    {
        handler.post(new Runnable() {
            @Override
            public void run() {
                serverDisplay.append(log + "\n");
            }
        });
    }
}