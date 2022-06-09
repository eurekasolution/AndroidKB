package com.kbstar.d02fragment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    MainFragment mainFragment;
    MenuFragment menuFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainFragment = new MainFragment();
        menuFragment = new MenuFragment();

    }


    public void changeFragment(int idx)
    {
        if(idx == 1)
        {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container, menuFragment)
                    .commit();
        }
        if(idx == 0)
        {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container, mainFragment)
                    .commit();
        }
    }
}