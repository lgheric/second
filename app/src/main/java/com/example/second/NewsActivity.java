package com.example.second;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;


public class NewsActivity extends BaseActivity {

    private EditText editname;
    private RadioGroup rad;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivity(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_news);

        //设置文字:
        setTitle(R.string.icon_list);  //XML代码中设置:android:label="@string/activity_dialog"

        Log.d("BaseActivity",getClass().getSimpleName());

        ActivityCollector.addActivity(this);//把当前activity添加到集合中统一管理

        //------------
        Button btnregister = findViewById(R.id.btnregister);
        editname = findViewById(R.id.editname);
        rad = findViewById(R.id.radioGroup);
        btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name,sex = "";
                Intent it = new Intent(NewsActivity.this,MyActivity.class);

                name = editname.getText().toString();

                //遍历RadioGroup找出被选中的单选按钮
                for(int i = 0;i < rad.getChildCount();i++)
                {
                    RadioButton rd = (RadioButton)rad.getChildAt(i);
                    if(rd.isChecked())
                    {
                        sex = rd.getText().toString();
                        break;
                    }
                }

                //新建Bundle对象,并把数据写入
                Bundle bd = new Bundle();
                bd.putCharSequence("user",name);
                bd.putCharSequence("sex",sex);

                //将数据包Bundle绑定到Intent上
                it.putExtras(bd);
                startActivity(it);
                //关闭第一个Activity
                finish();

            }
        });

        Button btnexit = findViewById(R.id.btnexit);
        btnexit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppExit(NewsActivity.this);
            }
        });
    }

    //保存点击的时间
    private long exitTime = 0;
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                Toast.makeText(getApplicationContext(), "再按一次退出程序",
                        Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                ActivityCollector.finishAll();
            }
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

//    // 定义一个变量，来标识是否退出
//    private static boolean isExit = false;
//    Handler mHandler = new Handler() {
//        @Override
//        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
//            isExit = false;
//        }
//    };
//
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if (keyCode == KeyEvent.KEYCODE_BACK) {
//            if (!isExit) {
//                isExit = true;
//                Toast.makeText(getApplicationContext(), "再按一次退出程序",
//                        Toast.LENGTH_SHORT).show();
//                // 利用handler延迟发送更改状态信息
//                mHandler.sendEmptyMessageDelayed(0, 2000);
//            } else {
//                finish();
//            }
//            return false;
//        }
//        return super.onKeyDown(keyCode, event);
//    }

    /**
     * 退出应用程序
     */
    public void AppExit(Context context) {
        try {
            ActivityCollector.finishAll();
            ActivityManager activityMgr = (ActivityManager) context
                    .getSystemService(Context.ACTIVITY_SERVICE);
            activityMgr.killBackgroundProcesses(context.getPackageName());
            System.exit(0);
        } catch (Exception ignored) {}
    }
}
