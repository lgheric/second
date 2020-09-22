package com.example.second;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class ContentFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fg_content, container, false);
        TextView tv_content = (TextView) view.findViewById(R.id.tv_content);
        assert getArguments() != null;
        String text = getArguments().getString("text");
        tv_content.setText(text);
        return view;
    }
}
