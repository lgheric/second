package com.example.second;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;

public class MainActivity extends AppCompatActivity{

    private DrawerLayout drawer_layout;
    private RightFragment fg_right_menu;
    private LeftFragment fg_left_menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //获取碎片管理器对象
        FragmentManager fManager = getSupportFragmentManager();

        fg_right_menu = (RightFragment) fManager.findFragmentById(R.id.fg_right_menu);//通过碎片管理器获得左侧滑的碎片对象
        fg_left_menu = (LeftFragment) fManager.findFragmentById(R.id.fg_left_menu);//通过碎片管理器获得右侧滑的碎片对象

        initViews();

    }

    @SuppressLint("WrongConstant")
    private void initViews() {
        //---获得主侧滑布局
        drawer_layout = (DrawerLayout) findViewById(R.id.drawer_layout);
        //FrameLayout fly_content = (FrameLayout) findViewById(R.id.fly_content);

        //----获得主布局中include的布局topbar
        View topbar = findViewById(R.id.topbar);
        //获得topbar布局中的btn_right控件,并给其添加点击事件监听
        Button btn_right = (Button) topbar.findViewById(R.id.btn_right);
        btn_right.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                drawer_layout.openDrawer(GravityCompat.END);//打开右侧滑菜单
                drawer_layout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED, GravityCompat.END);    //解除锁定
            }

        });

        //---设置右面的侧滑菜单只能通过编程来打开
        drawer_layout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED, GravityCompat.END);
        //给主侧滑布局添加事件监听
        drawer_layout.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(@NonNull View view, float v) {

            }

            @Override
            public void onDrawerOpened(@NonNull View view) {

            }

            @Override
            public void onDrawerClosed(@NonNull View view) {
                drawer_layout.setDrawerLockMode(
                        DrawerLayout.LOCK_MODE_LOCKED_CLOSED, GravityCompat.END);
            }

            @Override
            public void onDrawerStateChanged(int i) {

            }
        });

        //---业务需要的自定义函数，用来把drawer_layout传给碎片对象
        fg_right_menu.setDrawerLayout(drawer_layout);
        fg_left_menu.setDrawerLayout(drawer_layout);
    }
}