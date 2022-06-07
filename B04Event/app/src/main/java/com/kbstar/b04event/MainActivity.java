package com.kbstar.b04event;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView textView;
    private View view;
    private View view2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        view = findViewById(R.id.view);
        view2 = findViewById(R.id.view2);

        view.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int action = motionEvent.getAction();
                float posX = motionEvent.getX();
                float posY = motionEvent.getY();

                if(action == MotionEvent.ACTION_DOWN)
                {
                    println("Down : " + posX + ", " +posY);
                }else if(action == MotionEvent.ACTION_UP)
                {
                    println("Up : " + posX + ", " +posY);
                } else if(action == MotionEvent.ACTION_MOVE)
                {
                    println("Move : " + posX + ", " +posY);
                }

                return false;
            }
        });

    }

    public void println(String msg)
    {
        textView.append(msg + "\n");
    }
}