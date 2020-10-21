package com.example.second;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import java.util.Objects;

public class RightFragment extends Fragment implements View.OnClickListener{
    private DrawerLayout drawer_layout;
    private FragmentManager fManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fg_right, container, false);//通过布局填充器拿到右侧滑菜单的布局view
        view.findViewById(R.id.btn_one).setOnClickListener(this);//给拿到的布局里的btn_one控件添加点击事件监听
        view.findViewById(R.id.btn_two).setOnClickListener(this);
        view.findViewById(R.id.btn_three).setOnClickListener(this);
        fManager = Objects.requireNonNull(getActivity()).getSupportFragmentManager();//获取碎片管理器对象
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_one:
                //实例化一个碎片对象
                ContentFragment cFragment1 = new ContentFragment("1.点击了右侧菜单项一",R.color.blue);
                //通过碎片管理器替换内容显示区的内容（这里只改了文字和背景颜色，内容显示区是在主布局中）
                //fManager.beginTransaction().replace(R.id.fly_content,cFragment1).commit();
                //关闭右侧滑菜单
                drawer_layout.closeDrawer(GravityCompat.END);
                break;
            case R.id.btn_two:
                ContentFragment cFragment2 = new ContentFragment("2.点击了右侧菜单项二",R.color.red);
                //fManager.beginTransaction().replace(R.id.fly_content,cFragment2).commit();
                drawer_layout.closeDrawer(GravityCompat.END);
                break;
            case R.id.btn_three:
                ContentFragment cFragment3 = new ContentFragment("3.点击了右侧菜单项三",R.color.yellow);
                //fManager.beginTransaction().replace(R.id.fly_content,cFragment3).commit();
                drawer_layout.closeDrawer(GravityCompat.END);
                break;
        }
    }

    //暴露给Activity，用于传入DrawerLayout，因为点击后想关掉DrawerLayout
    public void setDrawerLayout(DrawerLayout drawer_layout){
        this.drawer_layout = drawer_layout;
    }

}
