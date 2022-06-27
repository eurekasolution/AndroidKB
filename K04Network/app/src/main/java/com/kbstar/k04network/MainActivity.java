package com.kbstar.k04network;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

// Log.d("MYTEST:
public class MainActivity extends AppCompatActivity {
    public static final String TAG = "MYNETWORK";

    private Button btnSend, btnStart;
    private EditText clientInput;
    private TextView clientDisplay, serverDisplay;

    private int serverPort = 10001;

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
        // serverPort
        try {
            Socket serverSocket = new Socket("localhost", serverPort);

            printClient("서버에 접속했습니다.");
            

        }catch(Exception e) {
            Log.d(TAG, "Client Exception : " + e.getMessage());
        }
    }

    private void startServer()
    {

        try {
            ServerSocket server = new ServerSocket(serverPort);
            printServer("Server Start : #"+ serverPort);

            while(true)
            {
                // 1.2.3.4:12345

                printServer("Listen ...... #"+serverPort);
                Socket clientSocket = server.accept();

                InetAddress clientAddress = clientSocket.getLocalAddress();
                int clientPort = clientSocket.getPort();

                printServer("Accepted ... " + clientAddress + ":" + clientPort);

                ObjectInputStream inStream = new ObjectInputStream(
                                                    clientSocket.getInputStream()
                                                );
                Object obj = inStream.readObject();
                printServer("RCV : " + obj);

                ObjectOutputStream outStream = new ObjectOutputStream(
                                                    clientSocket.getOutputStream()
                                                );
                outStream.writeObject(obj + " from Server");
                outStream.flush();

                printServer("ECHO SENDED");
                clientSocket.close();
                printServer("socket closed..");
            }

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