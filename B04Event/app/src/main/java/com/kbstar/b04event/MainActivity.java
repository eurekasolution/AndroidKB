package com.kbstar.b04event;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private TextView textView;
    private View view;
    private View view2;

    GestureDetector detector;

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

                return true; // <--- true로 바꾸셔야 정상 동작합니다.
            }
        });

        detector = new GestureDetector(this, new GestureDetector.OnGestureListener() {
            @Override
            public boolean onDown(MotionEvent motionEvent) {
                println("onDown() is called");
                System.out.println("------------------------------- XXXX");
                return true;
            }

            @Override
            public void onShowPress(MotionEvent motionEvent) {
                println("onShowPress() is called");
            }

            @Override
            public boolean onSingleTapUp(MotionEvent motionEvent) {
                println("onSingleTapUp() is called");
                return true;
            }

            @Override
            public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
                println("onScroll() is called");
                return true;
            }

            @Override
            public void onLongPress(MotionEvent motionEvent) {
                println("onLongPress() is called");
            }

            @Override
            public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
                println("onFling() is called");
                return true;
            }
        });

        view2.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                detector.onTouchEvent(motionEvent);
                return true;
            }
        });

    }

    public void println(String msg)
    {
        textView.append(msg + "\n");
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK)
        {
            Toast
                    .makeText(this, "BACK BUTTON is Pressed", Toast.LENGTH_LONG)
                    .show();
            return true;
        } else if(keyCode == KeyEvent.KEYCODE_VOLUME_UP)
        {
            Toast
                    .makeText(this, "Volume + ", Toast.LENGTH_LONG)
                    .show();
            return true;
        } else if(keyCode == KeyEvent.KEYCODE_VOLUME_DOWN)
        {
            Toast
                    .makeText(this, "Volume - ", Toast.LENGTH_LONG)
                    .show();
            return true;
        }


        return false;
    }
}

/*
KEYCODE_A ~ KEYCODE_Z
KEYCODE_0 ~ KEYCODE_9
KEYCODE_VOLUME_UP / _DOWN
KEYCODE_BACK
KEYCODE_HOME
 */