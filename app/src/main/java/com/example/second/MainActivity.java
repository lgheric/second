package com.example.second;


import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity{


    private AlertDialog alert = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Context mContext = MainActivity.this;
        Button btn_show = findViewById(R.id.btn_show);

        //初始化Builder
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);

        //加载自定义的那个View,同时设置下
        final LayoutInflater inflater = MainActivity.this.getLayoutInflater();
        View view_custom = inflater.inflate(R.layout.view_dialog_custom, null, false);
        builder.setView(view_custom);
        builder.setCancelable(false);
        alert = builder.create();

        view_custom.findViewById(R.id.btn_cancle).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alert.dismiss();
            }
        });

        view_custom.findViewById(R.id.btn_blog).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "访问博客", Toast.LENGTH_SHORT).show();
                Uri uri = Uri.parse("http://blog.csdn.net/coder_pig");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
                alert.dismiss();
            }
        });

        view_custom.findViewById(R.id.btn_close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "对话框已关闭~", Toast.LENGTH_SHORT).show();
                alert.dismiss();
            }
        });

        btn_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alert.show();
            }
        });
    }

}