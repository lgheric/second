package com.example.second;


import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private Context global_context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        global_context = MainActivity.this;
        midToast("ha ha ha",Toast.LENGTH_LONG);
    }

    @SuppressWarnings("SameParameterValue")
    void midToast(String str, int showTime)
    {
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.view_toast_custom, (ViewGroup) findViewById(R.id.lly_toast));
        ImageView img_logo = view.findViewById(R.id.img_logo);
        TextView tv_msg = view.findViewById(R.id.tv_msg);
        tv_msg.setText(str);
        
        Toast toast = new Toast(global_context);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(view);
        toast.show();
    }
}