package com.kbstar.d02fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class MainFragment extends Fragment {

    Button btnMenu;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_main, container, false);

        ViewGroup view = ( ViewGroup ) inflater.inflate(R.layout.fragment_main, container, false);

        // fragment_main에 있는 button 이벤트 처리
        btnMenu = view.findViewById(R.id.btnMenu);

        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity activity =  (MainActivity) getActivity();
                // MainActivity Fragment 변환하는 메소드 만들기기
                activity.changeFragment(1);
            }
        });

       return view;
    }
}