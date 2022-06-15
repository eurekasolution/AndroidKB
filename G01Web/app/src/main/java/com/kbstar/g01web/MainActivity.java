package com.kbstar.g01web;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText editText;
    private Button button;
    private WebView webView;
    private WebView popView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);
        button = findViewById(R.id.button);
        webView = findViewById(R.id.webView);
        popView = findViewById(R.id.webView);

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        /*
        webView.setWebChromeClient(new WebChromeClient(){
            @Override
            public boolean onCreateWindow(WebView view, boolean isDialog, boolean isUserGesture, Message resultMsg) {
                //return super.onCreateWindow(view, isDialog, isUserGesture, resultMsg);
                // 팝업용 웹뷰 속성 설정

                popView = new WebView(view.getContext());

                popView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
                popView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
                popView.getSettings().setSupportMultipleWindows(true);
                popView.getSettings().setJavaScriptEnabled(true);


                return true;
            }
        });

         */

        webView.setWebViewClient(new ViewClient());

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                webView.loadUrl(editText.getText().toString());
            }
        });

        webView.loadUrl("https://www.kbstar.com");
        // 팝업창처럼 새로운 창을 띄우는 경우는 구글검색..
    }

    private class ViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view,String url) {
            view.loadUrl(url);
            return true;
        }
    }
}