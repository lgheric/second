package com.example.second;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;

public class OneActivity extends BaseActivity {

    @SuppressLint("InflateParams")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one);

        ViewPager vpager_one = (ViewPager) findViewById(R.id.vpager_one);

        ArrayList<View> aList = new ArrayList<>();

        LayoutInflater li = getLayoutInflater();

        aList.add(li.inflate(R.layout.view_one,null,false));
        aList.add(li.inflate(R.layout.view_two,null,false));
        aList.add(li.inflate(R.layout.view_three,null,false));

        MyPagerAdapter mAdapter = new MyPagerAdapter(aList);

        vpager_one.setAdapter(mAdapter);
    }
}
