package com.example.second;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn = findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //1,最常见的启动Activity方式
                //startActivity(new Intent(MainActivity.this,NewsActivity.class));
                //通过Intent的ComponentName
                ComponentName cn = new ComponentName("com.example.second","com.example.second.NewsActivity") ;
                Intent intent = new Intent() ;
                intent.setComponent(cn) ;
                startActivity(intent) ;
            }
        });

    }

}
