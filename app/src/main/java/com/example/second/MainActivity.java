package com.example.second;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final int TYPE_BOOK = 0;
    private static final int TYPE_APP = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //数据准备：
        ArrayList<Object> mData = new ArrayList<>();
        for(int i = 0;i < 20;i++){
            int n = (int) (Math.random() * 2);
            switch (n){
                case TYPE_BOOK:
                    mData.add(new Book("《第一行代码》","郭霖");
                    break;
                case TYPE_APP:
                    mData.add(new App(R.mipmap.iv_icon_baidu,"百度"));
                    break;
            }
        }

        ListView list_content = findViewById(R.id.list_content);
        MutiLayoutAdapter myAdapter = new MutiLayoutAdapter(this, mData);
        list_content.setAdapter(myAdapter);
    }


}