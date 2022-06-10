package com.kbstar.e01optionmenu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
                break;
            case R.id.menu_search:
                showToast("검색");
                break;
            case R.id.menu_settings:
                showToast("설정");
                break;
            default:
                showToast("Unknown Menu Selected");
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