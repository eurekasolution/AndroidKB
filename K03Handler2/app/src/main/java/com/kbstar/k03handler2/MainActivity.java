package com.kbstar.k03handler2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

// MessageQuer를 이용하는 방법
// 쓰레드에서 핸들러에게 전달

public class MainActivity extends AppCompatActivity {

    private Button btnStart;
    private TextView textView;
    private int counter = 0;

    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnStart = findViewById(R.id.btnStart);
        textView = findViewById(R.id.textView);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyThread thread = new MyThread();
                thread.start();
            }
        });

        handler = new MainHandler();
    }

    class MyThread extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < 100; i++)
            {
                try {
                    Thread.sleep(1000);
                }catch(Exception e)
                {

                }

                counter ++;
                //textView.setText("" + counter);

                Message msg = handler.obtainMessage();
                Bundle bundle = new Bundle();
                bundle.putInt("key", counter);
                bundle.putInt("doubleKey", counter * 2);

                msg.setData(bundle);


                handler.sendMessage(msg);

                /*
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        textView.setText("" + counter);
                    }
                });

                 */
            }
        }
    }

    class MainHandler extends Handler {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);

            Bundle bundle = msg.getData();
            int value = bundle.getInt("key");
            textView.setText("key : " +  value);
            textView.append(", double : " + bundle.getInt("doubleKey"));
        }
    }
}