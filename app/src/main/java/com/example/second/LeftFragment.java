package com.example.second;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import java.util.Objects;

public class LeftFragment extends Fragment {

    private DrawerLayout drawer_layout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fg_left, container, false);//通过布局填充器拿到左侧滑菜单的布局view
        ImageView img_bg = (ImageView) view.findViewById(R.id.img_bg);//给拿到的布局里的img_bg控件添加点击事件监听
        img_bg.setOnClickListener(new View.OnClickListener() {//
            @Override
            public void onClick(View v) {
                //启动一个新的Activity
                Objects.requireNonNull(getActivity()).startActivity(new Intent(getActivity(),NewsActivity.class));
                //关闭左侧滑菜单
                drawer_layout.closeDrawer(GravityCompat.START);
            }
        });
        return view;
    }

    //暴露给Activity，用于传入DrawerLayout，因为点击后想关掉DrawerLayout
    public void setDrawerLayout(DrawerLayout drawer_layout){
        this.drawer_layout = drawer_layout;
    }

}
