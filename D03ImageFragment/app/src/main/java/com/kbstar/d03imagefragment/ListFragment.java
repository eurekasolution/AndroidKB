package com.kbstar.d03imagefragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class ListFragment extends Fragment {

    private Button button1;
    private Button button2;
    private Button button3;

    private MainActivity mainActivity;

    // inner class 만들듯이, inner interface를 만든다.
    public static interface ImageSelectCallback {
        public void changeImage(int index);
    }

    public ImageSelectCallback callback;



    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if(context instanceof ImageSelectCallback)
        {
            callback = (ImageSelectCallback) context;
        }
    }




    /*
        callback 변수 자료형을 ImageSelectCallback으로 선언한 이유
            선택한것에 따라 다른 Fragment 이미지 변경하려고 한다.
            액티비티(프레그먼트1, 2 포함)한테 데이터를 전달해야 한다.
            만약 액티비티에 changeImage(2)메소드를 정의한 후,
            changeImage(2) 호출
     */


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_list, container, false);

        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.fragment_list, container, false);

        // Button 3개 생성
        button1 = view.findViewById(R.id.button1);
        button2 = view.findViewById(R.id.button2);
        button3 = view.findViewById(R.id.button3);

        mainActivity = (MainActivity) getActivity();

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(callback != null)
                {
                    callback.changeImage(0);
                }
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(callback != null)
                {
                    callback.changeImage(1);
                }

            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(callback != null)
                {
                    callback.changeImage(2);
                }
            }
        });


        return view;
    }
}