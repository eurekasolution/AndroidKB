package com.kbstar.b06toast;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    private EditText editText;
    private EditText editText2;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);
        editText2 = findViewById(R.id.editText2);
        button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener(){
            @Override

            public void onClick(View view) {
                //printToast("Clicked");
                //Toast.makeText(MainActivity.this, "clicked ",  Toast.LENGTH_LONG).show();
                //System.out.println("------------------------------------------Btn clicked");

                try{
                    int x = Integer.parseInt( editText.getText().toString() );
                    int y = Integer.parseInt( editText2.getText().toString() );

                    Toast toastView = Toast.makeText(getApplicationContext(), "x = " + x + ", y = " + y, Toast.LENGTH_LONG);

                    toastView.setGravity(Gravity.TOP|Gravity.LEFT, x, y);
                    toastView.show();

                }catch (NumberFormatException e)
                {

                }

            }
        });
    }

    public void printToast(String msg)
    {
        Toast.makeText(this,msg,  Toast.LENGTH_LONG).show();
    }

}