package com.example.second;


import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.widget.ViewFlipper;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewFlipper vflp_help = findViewById(R.id.vflp_help);
        vflp_help.startFlipping();

    }



}