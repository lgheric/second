package com.example.second;

import android.content.res.Configuration;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView txtResult = (TextView) findViewById(R.id.txtResult);
        StringBuilder status = new StringBuilder();
        //①获取系统的Configuration对象
        Configuration cfg = getResources().getConfiguration();
        //②想查什么查什么
        status.append("densityDpi:").append(cfg.densityDpi).append("\n");
        status.append("fontScale:").append(cfg.fontScale).append("\n");
        status.append("hardKeyboardHidden:").append(cfg.hardKeyboardHidden).append("\n");
        status.append("keyboard:").append(cfg.keyboard).append("\n");
        status.append("keyboardHidden:").append(cfg.keyboardHidden).append("\n");
        status.append("locale:").append(cfg.locale).append("\n");
        status.append("mcc:").append(cfg.mcc).append("\n");
        status.append("mnc:").append(cfg.mnc).append("\n");
        status.append("navigation:").append(cfg.navigation).append("\n");
        status.append("navigationHidden:").append(cfg.navigationHidden).append("\n");
        status.append("orientation:").append(cfg.orientation).append("\n");
        status.append("screenHeightDp:").append(cfg.screenHeightDp).append("\n");
        status.append("screenWidthDp:").append(cfg.screenWidthDp).append("\n");
        status.append("screenLayout:").append(cfg.screenLayout).append("\n");
        status.append("smallestScreenWidthDp:").append(cfg.densityDpi).append("\n");
        status.append("touchscreen:").append(cfg.densityDpi).append("\n");
        status.append("uiMode:").append(cfg.densityDpi).append("\n");
        txtResult.setTextSize(24.0f);
        txtResult.setText(status.toString());
    }

}
