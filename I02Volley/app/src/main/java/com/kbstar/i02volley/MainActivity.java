package com.kbstar.i02volley;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class MainActivity extends AppCompatActivity {
    private EditText input;
    private Button button;
    private TextView textView;

    private RequestQueue requestQueue; // q

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        input = findViewById(R.id.input);
        button = findViewById(R.id.button);
        textView = findViewById(R.id.textView);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                process();
            }
        });

        if(requestQueue == null)
        {
            requestQueue = Volley.newRequestQueue(getApplicationContext());
        }

    }

    public void printLog(String msg)
    {
        textView.append(msg + "\n");
    }

    public void process()
    {
        String url = input.getText().toString();

        textView.setText("");

        StringRequest request = new StringRequest(
                Request.Method.GET,
                url,
                new Response.Listener<String>(){
                    @Override
                    public void onResponse(String response) {
                        //textView.setText(response);
                        printLog(response);
                    }
                },
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        printLog("에러발생 : " + error.getMessage());
                    }
                }
        );

        // RequestQueue애 넣어주기만 하면 끝.
        // 받은 응답이 있어어, 무시하고 새로운 요청하고, 결과 표시할 것인가?
        request.setShouldCache(false);
        requestQueue.add(request);
    }
}

/*
    CSV, TSV
    Commas Seperate Value
    JSON : JavaScript Ojbect Notation

    item:111

    {
        "name":"홍길동",
        "age" : 11,
        "company" : {
            "name" : "국민은행",
            "http" : "kbstar.com"
            "other":
            {

            }
        }
    }


{"boxOfficeResult":
    {
        "boxofficeType":"일별 박스오피스",
        "showRange":"20220601~20220601",
        "dailyBoxOfficeList":[{
            "rnum":"1",
            "rank":"1",
            "rankInten":"8",
            "rankOldAndNew":"OLD",
            "movieCd":"20206061",
            "movieNm":"쥬라기 월드: 도미니언",
            "openDt":"2022-06-01","salesAmt":"7773253550","salesShare":"52.9","salesInten":"7762645550","salesChange":"73177.3","salesAcc":"7783861550","audiCnt":"763647","audiInten":"763023","audiChange":"122279.3","audiAcc":"764271","scrnCnt":"2218","showCnt":"9438"},{"rnum":"2","rank":"2","rankInten":"-1","rankOldAndNew":"OLD","movieCd":"20204548","movieNm":"범죄도시 2","openDt":"2022-05-18","salesAmt":"4774774660","salesShare":"32.5","salesInten":"2132072040","salesChange":"80.7","salesAcc":"77269574040","audiCnt":"463148","audiInten":"197803","audiChange":"74.5","audiAcc":"7476639","scrnCnt":"1305","showCnt":"5317"},{"rnum":"3","rank":"3","rankInten":"0","rankOldAndNew":"NEW","movieCd":"20135304","movieNm":"극장판 포켓몬스터DP: 기라티나와 하늘의 꽃다발 쉐이미","openDt":"2022-06-01","salesAmt":"1547458980","salesShare":"10.5","salesInten":"1547458980","salesChange":"100","salesAcc":"1547458980","audiCnt":"163336","audiInten":"163336","audiChange":"100","audiAcc":"163336","scrnCnt":"809","showCnt":"1741"},{"rnum":"4","rank":"4","rankInten":"-1","rankOldAndNew":"OLD","movieCd":"20224634","movieNm":"그대가 조국","openDt":"2022-05-25","salesAmt":"327029180","salesShare":"2.2","salesInten":"157792200","salesChange":"93.2","salesAcc":"2128718690","audiCnt":"40328","audiInten":"23018","audiChange":"133","audiAcc":"230065","scrnCnt":"356","showCnt":"402"},{"rnum":"5","rank":"5","rankInten":"-3","rankOldAndNew":"OLD","movieCd":"20212855","movieNm":"닥터 스트레인지: 대혼돈의 멀티버스","openDt":"2022-05-04","salesAmt":"109081860","salesShare":"0.7","salesInten":"-209597850","salesChange":"-65.8","salesAcc":"61907353560","audiCnt":"10412","audiInten":"-20010","audiChange":"-65.8","audiAcc":"5814188","scrnCnt":"158","showCnt":"229"},{"rnum":"6","rank":"6","rankInten":"0","rankOldAndNew":"NEW","movieCd":"20210590","movieNm":"카시오페아","openDt":"2022-06-01","salesAmt":"32230600","salesShare":"0.2","salesInten":"32230600","salesChange":"100","salesAcc":"48341700","audiCnt":"3606","audiInten":"3606","audiChange":"100","audiAcc":"5342","scrnCnt":"175","showCnt":"243"},{"rnum":"7","rank":"7","rankInten":"0","rankOldAndNew":"NEW","movieCd":"20224468","movieNm":"애프터 양","openDt":"2022-06-01","salesAmt":"37465500","salesShare":"0.3","salesInten":"37465500","salesChange":"100","salesAcc":"49879500","audiCnt":"3579","audiInten":"3579","audiChange":"100","audiAcc":"4754","scrnCnt":"161","showCnt":"250"},{"rnum":"8","rank":"8","rankInten":"0","rankOldAndNew":"NEW","movieCd":"20224891","movieNm":"특수요원 빼꼼","openDt":"2022-06-01","salesAmt":"25806500","salesShare":"0.2","salesInten":"25806500","salesChange":"100","salesAcc":"29307500","audiCnt":"2822","audiInten":"2822","audiChange":"100","audiAcc":"3211","scrnCnt":"115","showCnt":"132"},{"rnum":"9","rank":"9","rankInten":"2","rankOldAndNew":"OLD","movieCd":"20143441","movieNm":"몬스터 싱어: 매직 인 파리","openDt":"2022-05-26","salesAmt":"6280500","salesShare":"0.0","salesInten":"1759300","salesChange":"38.9","salesAcc":"121488120","audiCnt":"856","audiInten":"302","audiChange":"54.5","audiAcc":"14000","scrnCnt":"49","showCnt":"49"},{"rnum":"10","rank":"10","rankInten":"-3","rankOldAndNew":"OLD","movieCd":"20211568","movieNm":"아치의 노래, 정태춘","openDt":"2022-05-18","salesAmt":"6031900","salesShare":"0.0","salesInten":"-2183000","salesChange":"-26.6","salesAcc":"235779620","audiCnt":"647","audiInten":"-224","audiChange":"-25.7","audiAcc":"24508","scrnCnt":"25","showCnt":"33"}]}}
 */