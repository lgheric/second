package com.example.second;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private GestureDetector mDetector;
    private final static String TAG = "MyGesture";
    private final static int MIN_MOVE = 200;   //最小距离

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //实例化GestureListener与GestureDetector对象
        MyGestureListener mgListener = new MyGestureListener();
        mDetector = new GestureDetector(this, mgListener);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return mDetector.onTouchEvent(event);
    }

    //自定义一个GestureListener,这个是View类下的，别写错哦！！！
    private class MyGestureListener extends GestureDetector.SimpleOnGestureListener {

        @Override
        public boolean onDown(MotionEvent motionEvent) {
            Log.d(TAG, "onDown:按下");
            Toast.makeText(MainActivity.this, "onDown:按下", Toast.LENGTH_SHORT).show();
            return false;
        }

//        @Override
//        public void onShowPress(MotionEvent motionEvent) {
//            Log.d(TAG, "onShowPress:手指按下一段时间,不过还没到长按");
//            Toast.makeText(MainActivity.this, "onShowPress:手指按下一段时间,不过还没到长按", Toast.LENGTH_SHORT).show();
//        }
//
//        @Override
//        public boolean onSingleTapUp(MotionEvent motionEvent) {
//            Log.d(TAG, "onSingleTapUp:手指离开屏幕的一瞬间");
//            Toast.makeText(MainActivity.this, "onSingleTapUp:手指离开屏幕的一瞬间", Toast.LENGTH_SHORT).show();
//            return false;
//        }
//
//        @Override
//        public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
//            Log.d(TAG, "onScroll:在触摸屏上滑动");
//            Toast.makeText(MainActivity.this, "onScroll:在触摸屏上滑动", Toast.LENGTH_SHORT).show();
//            return false;
//        }
//
//        @Override
//        public void onLongPress(MotionEvent motionEvent) {
//            Toast.makeText(MainActivity.this, "onScroll:在触摸屏上滑动", Toast.LENGTH_SHORT).show();
//            Log.d(TAG, "onScroll:在触摸屏上滑动");
//        }
//
//        @Override
//        public boolean onFling(MotionEvent e1, MotionEvent e2, float v, float v1) {
//            Log.d(TAG, "onFling:迅速滑动，并松开");
//            if(e1.getY() - e2.getY() > MIN_MOVE){
//                startActivity(new Intent(MainActivity.this, NewsActivity.class));
//                Toast.makeText(MainActivity.this, "通过手势启动Activity", Toast.LENGTH_SHORT).show();
//            }else if(e1.getY() - e2.getY()  < MIN_MOVE){
//                finish();
//                Toast.makeText(MainActivity.this,"通过手势关闭Activity",Toast.LENGTH_SHORT).show();
//            }
//            return true;
//        }

    }
}
