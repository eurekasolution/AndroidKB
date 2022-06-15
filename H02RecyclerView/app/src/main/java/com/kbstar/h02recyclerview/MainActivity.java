package com.kbstar.h02recyclerview;
// H02RecyclerView
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);

        LinearLayoutManager layoutManager
                = new LinearLayoutManager(
                        this,
                        LinearLayoutManager.VERTICAL,
                        false
                    );

        recyclerView.setLayoutManager(layoutManager);

        PersonAdapter adapter = new PersonAdapter();

        adapter.addItem(new Person("홍길동", "010-1111-1111"));
        adapter.addItem(new Person("이순신", "010-2222-2222"));
        adapter.addItem(new Person("이순신1", "010-2222-2222"));

        adapter.addItem(new Person("홍길동", "010-1111-1111"));
        adapter.addItem(new Person("이순신", "010-2222-2222"));
        adapter.addItem(new Person("이순신1", "010-2222-2222"));

        adapter.addItem(new Person("홍길동", "010-1111-1111"));
        adapter.addItem(new Person("이순신", "010-2222-2222"));
        adapter.addItem(new Person("이순신1", "010-2222-2222"));

        adapter.addItem(new Person("홍길동", "010-1111-1111"));
        adapter.addItem(new Person("이순신", "010-2222-2222"));
        adapter.addItem(new Person("이순신1", "010-2222-2222"));

        adapter.addItem(new Person("홍길동", "010-1111-1111"));
        adapter.addItem(new Person("이순신", "010-2222-2222"));
        adapter.addItem(new Person("이순신1", "010-2222-2222"));


        recyclerView.setAdapter(adapter);

    }
}