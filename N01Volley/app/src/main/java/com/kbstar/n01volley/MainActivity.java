package com.kbstar.n01volley;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private LinearLayout layout_logout, layout_login;
    private Button btnGoLogin, btnGoRegist;
    private Button btnLogout;
    private TextView tv_login;

    private static String LOGIN_ID;
    private static String LOGIN_NAME;
    private static String LOGIN_LEVEL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnGoRegist = findViewById(R.id.btnGoRegist);
        btnGoRegist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoRegist();
            }
        });

        btnGoLogin = findViewById(R.id.btnGoLogin);
        btnGoLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoLogin();
            }
        });

        layout_login = findViewById(R.id.layout_login);
        layout_logout = findViewById(R.id.layout_logout);

        Intent intent = getIntent();
        LOGIN_ID = intent.getStringExtra("LOGIN_ID");

        tv_login = findViewById(R.id.tv_login);

        if(LOGIN_ID == null)
        {
            layout_login.setVisibility(View.INVISIBLE);
            layout_logout.setVisibility(View.VISIBLE);
        }else
        {


            LOGIN_NAME = intent.getStringExtra("LOGIN_NAME");
            LOGIN_LEVEL = intent.getStringExtra("LOGIN_LEVEL");

            layout_login.setVisibility(View.VISIBLE);
            layout_logout.setVisibility(View.INVISIBLE);

            tv_login.setText(LOGIN_NAME + "님");
        }

        btnLogout = findViewById(R.id.btnLogout);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logout();
            }
        });

    }

    public void logout()
    {
        new AlertDialog.Builder(this)
                .setTitle("로그아웃 확인")
                .setMessage("로그아웃 하시겠습니까?")
                .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                       tv_login.setText("");

                       layout_login.setVisibility(View.INVISIBLE);
                       layout_logout.setVisibility(View.VISIBLE);

                       Intent intent = new Intent(MainActivity.this,
                                                            LoginActivity.class);
                       intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                       startActivity(intent);
                    }
                })
                .setNegativeButton("취소", null)
                .show();
    }

    public void gotoRegist()
    {
        Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
        startActivity(intent);
    }

    public void gotoLogin()
    {
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(intent);
    }
}