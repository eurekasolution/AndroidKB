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

import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    private String TAG = "Volley-RegisterActivity";
    private EditText inputId,inputName, inputPass;
    private Button btnLogin;
    Boolean isSuccess = false;
    private static String LOGIN_ID;
    private static String LOGIN_NAME;
    private static String LOGIN_LEVEL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        inputId = findViewById(R.id.et_id);
        inputPass = findViewById(R.id.et_pass);
        btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });
    }

    public void login()
    {
        String id = inputId.getText().toString();
        String pass = inputPass.getText().toString();

        Response.Listener<String> responseListener =
                new Response.Listener<String>(){
                    @Override
                    public void onResponse(String response) {
                        Log.d(TAG, "OK Response = " + response);

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String loginResult = jsonObject.getString("result");

                            String printSuccessFail = "";


                            if(loginResult.equals("1"))
                            {
                                printSuccessFail = "성공";
                                isSuccess = true;

                                LOGIN_ID = jsonObject.getString("id");
                                LOGIN_NAME = jsonObject.getString("name");
                                LOGIN_LEVEL = jsonObject.getString("level");

                            }else
                            {
                                printSuccessFail = "실패";
                                isSuccess = false;
                            }

                            new AlertDialog.Builder(LoginActivity.this)
                                    .setTitle("로그인 결과")
                                    .setMessage("로그인 " + printSuccessFail + "했습니다.")
                                    .setPositiveButton("확인1", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            if(isSuccess) {
                                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                                intent.putExtra("LOGIN_ID", LOGIN_ID );
                                                intent.putExtra("LOGIN_NAME", LOGIN_NAME );
                                                intent.putExtra("LOGIN_LEVEL", LOGIN_LEVEL );
                                                startActivity(intent);
                                                finish();
                                            }else
                                            {
                                                inputId.setText("");
                                                inputPass.setText("");
                                            }
                                        }
                                    })
                                    .setNegativeButton("취소", null)
                                    .show();

                        }catch(Exception e)
                        {

                        }





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
        LoginRequest loginRequest = new LoginRequest(
                id,
                pass,
                Request.Method.POST,
                responseListener,
                errorListener
        );

        loginRequest.setShouldCache(false);

        RequestQueue q = Volley.newRequestQueue(getApplicationContext());
        q.add(loginRequest);
    }
}