package com.kbstar.n01volley;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

public class RegisterActivity extends AppCompatActivity {
    private String TAG = "Volley-RegisterActivity";
    private EditText inputId,inputName, inputPass;
    private Button btnInsert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regsiter);

        inputId = findViewById(R.id.inputId);
        inputName = findViewById(R.id.inputName);
        inputPass = findViewById(R.id.inputPass);

        btnInsert = findViewById(R.id.btnInsert);

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                regist();
            }
        });
    }

    public void regist()
    {
        String id = inputId.getText().toString();
        String name = inputName.getText().toString();
        String pass = inputPass.getText().toString();

        Response.Listener<String> responseListener =
                new Response.Listener<String>(){
                    @Override
                    public void onResponse(String response) {
                        Log.d(TAG, "OK Response = " + response);

                        new AlertDialog.Builder(RegisterActivity.this)
                                .setTitle("회원가입 결과")
                                .setMessage("회원가입이 완료되었습니다.")
                                .setPositiveButton("확인1", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                        startActivity(intent);
                                        finish();
                                    }
                                })
                                .setNegativeButton("취소", null)
                                .show();

                    }
                };
        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "onErrorResponse() error : " + error.getMessage());
            }
        };
        // RegistRequest(id, name, pass, "POST", "htt...", lis, err)

        // Volley로 회원 입력값을 전송
        RegistRequest registRequest = new RegistRequest(
               id,
               name,
               pass,
               Request.Method.POST,
               responseListener,
               errorListener
        );

        registRequest.setShouldCache(false);

        RequestQueue q = Volley.newRequestQueue(getApplicationContext());
        q.add(registRequest);
    }
}