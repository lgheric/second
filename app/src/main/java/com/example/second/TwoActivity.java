package com.example.second;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;

public class TwoActivity extends BaseActivity {

    @SuppressLint("InflateParams")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);

        ViewPager vpager_two = (ViewPager) findViewById(R.id.vpager_two);

        ArrayList<View> aList = new ArrayList<>();

        LayoutInflater li = getLayoutInflater();

        aList.add(li.inflate(R.layout.view_one,null,false));
        aList.add(li.inflate(R.layout.view_two,null,false));
        aList.add(li.inflate(R.layout.view_three, null, false));

        ArrayList<String> sList = new ArrayList<>();
        sList.add("橘黄");
        sList.add("淡黄");
        sList.add("浅棕");

        MyPagerAdapter2 mAdapter = new MyPagerAdapter2(aList, sList);

        vpager_two.setAdapter(mAdapter);
    }

}
