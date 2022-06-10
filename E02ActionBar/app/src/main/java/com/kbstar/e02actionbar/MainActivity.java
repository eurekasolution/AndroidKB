package com.kbstar.e02actionbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    Button button;
    ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        button = findViewById(R.id.button);
        actionBar = getSupportActionBar();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actionBar.setLogo(R.drawable.home);
                actionBar.setDisplayOptions(ActionBar.DISPLAY_HOME_AS_UP|ActionBar.DISPLAY_USE_LOGO);
                //actionBar.setDisplayOptions(ActionBar.DISPLAY_HOME_AS_UP|ActionBar.DISPLAY_SHOW_TITLE);
                //actionBar.setDisplayOptions(ActionBar.DISPLAY_HOME_AS_UP);

                /*
                DISPLAY_XXXX
                       DISPLAY_USE_LOGO : 아이콘 부분에 로고 아이콘
                               SHOW_HOME : 홈 아이콘 표시
                               HOME_AS_UP : <
                               SHOW_TITLE : 타이틀
                 */
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // return super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();

        switch (itemId)
        {
            case R.id.menu_refresh:
                doRefresh();
                textView.setText("새로고침");
                break;
            case R.id.menu_search:
                showToast("검색");
                textView.setText("검색하기");
                break;
            case R.id.menu_settings:
                showToast("설정");
                textView.setText("설정하기");
                break;
            default:
                showToast("Unknown Menu Selected");
                textView.setText("모름름");
                break;
        }

        /*
        if(item.getItemId() == R.id.menu_refresh)
        {
            showToast("새로고침");
        }
        */

        return super.onOptionsItemSelected(item);
    }

    public void doRefresh()
    {
        showToast("새로고침");
    }

    public void showToast(String msg)
    {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

}