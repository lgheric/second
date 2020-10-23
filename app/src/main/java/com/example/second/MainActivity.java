package com.example.second;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView txttitle;
    private ProgressBar pgbar;
    private MyAsyncTask myTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txttitle = findViewById(R.id.txttitle);
        pgbar = findViewById(R.id.pgbar);
        Button btnupdate = findViewById(R.id.btnupdate);
        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myTask = new MyAsyncTask(txttitle,pgbar);
                myTask.execute(1000);
            }
        });

        //取消更新
        Button btncancel = findViewById(R.id.btncancel);
        btncancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean flag = myTask.cancel(true);
                String str;
                if (flag) {
                    str = "取消成功";
                }
                else {
                    str = "取消失败";
                }
                Toast.makeText(MainActivity.this, str , Toast.LENGTH_LONG).show();
            }
        });


    }


}
