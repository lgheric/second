package com.example.second;

import android.app.AlertDialog;
import android.content.Context;
import android.gesture.Gesture;
import android.gesture.GestureLibraries;
import android.gesture.GestureLibrary;
import android.gesture.GestureOverlayView;
import android.gesture.Prediction;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private GestureLibrary gestureLibrary;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = MainActivity.this;
        gestureLibrary = GestureLibraries.fromFile("/mygestures");
        if (gestureLibrary.load()) {
            Toast.makeText(mContext, "手势库加载成功", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(mContext, "手势库加载失败", Toast.LENGTH_SHORT).show();
        }

        //获取手势编辑组件后，设置相关参数
        GestureOverlayView gesture = findViewById(R.id.gesture);
        gesture.setGestureColor(Color.GREEN);
        gesture.setGestureStrokeWidth(5);
        gesture.addOnGesturePerformedListener(new GestureOverlayView.OnGesturePerformedListener() {
            @Override
            public void onGesturePerformed(GestureOverlayView gestureOverlayView, final Gesture gesture) {
                //识别用户刚绘制的手势
                ArrayList<Prediction> predictions = gestureLibrary.recognize(gesture);
                ArrayList<String> result = new ArrayList<>();
                //遍历所有找到的Prediction对象
                for (Prediction pred : predictions) {
                    if (pred.score > 2.0) {
                        result.add("与手势【" + pred.name + "】相似度为" + pred.score);
                    }
                }
                if (result.size() > 0) {
                    ArrayAdapter<Object> adapter = new ArrayAdapter<>(mContext,
                            android.R.layout.simple_dropdown_item_1line, result.toArray());
                    new AlertDialog.Builder(mContext).setAdapter(adapter,null).setPositiveButton("确定",null).show();
                }else{
                    Toast.makeText(mContext,"无法找到匹配的手势！",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
