package com.kbstar.d03imagefragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity
        implements ListFragment.ImageSelectCallback {

    ImageFragment imageFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager manager = getSupportFragmentManager();

        imageFragment = (ImageFragment) manager.findFragmentById(R.id.imageFragment);
    }

    @Override
    public void changeImage(int index) {
        if(index == 2)
        {
            //
        }
    }
}