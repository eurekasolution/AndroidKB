package com.kbstar.m01weblist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private String SERVER_URL = "http://10.5.5.200:8080/android/list.php";

    private RecyclerView recyclerView;
    private Button btnList;
    private TextView display;

    private String TAG = "WEB-LIST";

    private ArrayList<UserData> arrayList;
    private UserAdapter adapter;
    private String jsonString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        btnList = findViewById(R.id.btnList);
        display = findViewById(R.id.display);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        arrayList = new ArrayList<>();
        adapter = new UserAdapter(this, arrayList);

        btnList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}