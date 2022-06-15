package com.kbstar.h05pagesliding;

// H05PageSliding

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    boolean isOpen = false;
    LinearLayout slidingMenu;
    Button button;
    Animation leftAni;
    Animation rightAni;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        slidingMenu = findViewById(R.id.slidingMenu);
        button = findViewById(R.id.button);

        leftAni = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.translate_left);
        rightAni = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.translate_right);

        SlidingPageAnimationListener listener = new SlidingPageAnimationListener();
        leftAni.setAnimationListener(listener);
        rightAni.setAnimationListener(listener);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(isOpen ==true) {
                    slidingMenu.startAnimation(rightAni);
                }else {
                    slidingMenu.setVisibility(View.VISIBLE);
                    slidingMenu.startAnimation(leftAni);
                }
            }
        });
    }

    private class SlidingPageAnimationListener
                implements Animation.AnimationListener
    {
        @Override
        public void onAnimationStart(Animation animation) {

        }

        @Override
        public void onAnimationEnd(Animation animation) {
            if(isOpen)
            {
                slidingMenu.setVisibility(View.INVISIBLE);
                button.setText("Open");
                isOpen = false;
            }else
            {
                //slidingMenu.setVisibility(View.VISIBLE);
                button.setText("Close");
                isOpen = true;
            }
        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    }
}