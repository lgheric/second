package com.example.second;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.LinkedList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ListView listview;
    private MyAdapter mAdapter;
    int flag=2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bindViews();

        LinkedList<Data> mData = new LinkedList<>();
        mData.add(new Data(R.mipmap.ic_launcher_round,"给猪哥跪了~~~ x 1"));

        mAdapter = new MyAdapter(this, mData);
        listview.setAdapter(mAdapter);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_add:
                mAdapter.add(new Data(R.mipmap.ic_launcher_round,"给猪哥跪了~~~ x " + flag));
                flag++;
                break;
            case R.id.btn_add_position:
                int n =  mAdapter.getCount();
                mAdapter.add(n,new Data(R.mipmap.ic_launcher,"给猪哥跪了~~~(added)"));
                Toast.makeText(this,"n:"+n,Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_update:
                mAdapter.update(5,new Data(R.mipmap.ic_launcher,"给猪哥跪了~~~(edited)"),listview);

                //mData.set(15, "更新的item");
                //方式一：
//                adapter = new MyAdapter(Main8Activity.this, list);
//                listview.setAdapter(adapter);
                //方式二：
//                adapter.notifyDataSetChanged();
                //方式三：
                //adapter.update(15,new Data(R.id.img_icon));
                break;
            case R.id.btn_del:
                    int m =  mAdapter.getCount()-1;
                    if(m >=0){
                        mAdapter.remove(m);
                    }else{
                        Toast.makeText(this,"没有了"+m,Toast.LENGTH_SHORT).show();
                    }

                break;
            case R.id.btn_del_all:
                    mAdapter.removeAll();
                break;
        }
    }


    private void bindViews(){
        Button btn_add = findViewById(R.id.btn_add);
        Button btn_add_position = findViewById(R.id.btn_add_position);
        Button btn_update = findViewById(R.id.btn_update);
        Button btn_del = findViewById(R.id.btn_del);
        Button btn_del_all = findViewById(R.id.btn_del_all);
        listview = findViewById(R.id.listview);

        btn_add.setOnClickListener(this);
        btn_add_position.setOnClickListener(this);
        btn_update.setOnClickListener(this);
        btn_del.setOnClickListener(this);
        btn_del_all.setOnClickListener(this);
    }





}