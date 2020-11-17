package com.example.second;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class ContentFragment extends Fragment {

    private String strContent;
    private int bgColor;

    public ContentFragment(String strContent,int bgColor) {
        this.strContent = strContent;
        this.bgColor = bgColor;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //拿到布局
        View view = inflater.inflate(R.layout.fg_content, container, false);
        /*开始改变布局*/
        //给布局设背景
        view.setBackgroundColor(getResources().getColor(bgColor));
        //拿到文本控件
        TextView tv_content = view.findViewById(R.id.txt_content);
        //给布局中的文本控件设置内容
        tv_content.setText(strContent);
        return view;
    }
}
