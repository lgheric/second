package com.example.second;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Objects;

public class MyActivity extends BaseActivity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        TextView txtshow = findViewById(R.id.txtshow);
        //获得Intent对象,并且用Bundle出去里面的数据
        Intent it = getIntent();
        Bundle bd = it.getExtras();

        //按键值的方式取出Bundle中的数据
        assert bd != null;
        String name = Objects.requireNonNull(bd.getCharSequence("user")).toString();
        String sex = Objects.requireNonNull(bd.getCharSequence("sex")).toString();
        txtshow.setText("尊敬的"+ name + " " + sex + "士"+"恭喜你,注册成功~");

    }

}
