package com.example.second;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ContentUris;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Contacts;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private Intent intent_e;
    private static final int ACTIVITY_GET_CAMERA_IMAGE=1;
    public static String TAG = "SdCardCheck";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //0.sdcard_activity
        Button sdcard_activity = findViewById(R.id.sdcard_activity);
        sdcard_activity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,SdCardActivity.class);
                startActivity(intent);
            }
        });

        //0.1 Activity间的数据传递 一次传递多个
        Button reg_activity = findViewById(R.id.reg_activity);
        reg_activity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,NewsActivity.class);
                startActivity(intent);
            }
        });

        //0.2 多个Activity间的交互(后一个传回给前一个)
        Button multiple_activity = findViewById(R.id.multiple_activity);
        multiple_activity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,MultiActivity.class);
                startActivity(intent);
            }
        });

        //1.给移动客服10086拨打电话   成功
        Button btn = findViewById(R.id.tel_10086);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("tel:10086");
                Intent intent = new Intent(Intent.ACTION_DIAL, uri);
                startActivity(intent);
            }
        });

        //2.发送短信  成功
        Button sendsms = findViewById(R.id.sendsms);
        sendsms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("smsto:10086");
                Intent intent = new Intent(Intent.ACTION_SENDTO, uri);
                intent.putExtra("sms_body", "Hello");
                startActivity(intent);
            }
        });

        //3.发送彩信（相当于发送带附件的短信）    TODO:未再现  但成功国调起了系统发短信页面，并填写必要信息
        Button sendsms2 = findViewById(R.id.sendsms2);
        sendsms2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.putExtra("sms_body", "Hello");
                Uri uri = Uri.parse("content://media/external/images/media/23");
                intent.putExtra(Intent.EXTRA_STREAM, uri);
                intent.setType("image/png");
                startActivity(intent);
            }
        });

        //4.打开浏览器:  成功！
        // 打开Google主页
        Button action_view = findViewById(R.id.action_view);
        action_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("http://www.baidu.com");
                Intent intent  = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        //5.发送电子邮件:(阉割了Google服务的没戏!!!!)
        Button mailto = findViewById(R.id.mailto);
        mailto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 给someone@domain.com发邮件
                Uri uri = Uri.parse("mailto:someone@domain.com");
                Intent intent  = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);

            }
        });

        //5.1.给someone@domain.com发邮件发送内容为“Hello”的邮件  TODO:未成功
        Button mailto2 = findViewById(R.id.mailto2);
        mailto2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_EMAIL, "someone@domain.com");
                intent.putExtra(Intent.EXTRA_SUBJECT, "Subject");
                intent.putExtra(Intent.EXTRA_TEXT, "Hello");
                intent.setType("text/plain");
                startActivity(intent);
            }
        });

        //5.2.给多人发邮件 TODO:未成功
        Button mailto3 = findViewById(R.id.mailto3);
        mailto3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Intent.ACTION_SEND);
                String[] tos = {"1@abc.com", "2@abc.com"}; // 收件人
                String[] ccs = {"3@abc.com", "4@abc.com"}; // 抄送
                String[] bccs = {"5@abc.com", "6@abc.com"}; // 密送
                intent.putExtra(Intent.EXTRA_EMAIL, tos);
                intent.putExtra(Intent.EXTRA_CC, ccs);
                intent.putExtra(Intent.EXTRA_BCC, bccs);
                intent.putExtra(Intent.EXTRA_SUBJECT, "Subject");
                intent.putExtra(Intent.EXTRA_TEXT, "Hello");
                intent.setType("message/rfc822");
                startActivity(intent);
            }
        });

        //6.显示地图: TODO:未成功  只是调起了第三方导航app
        // 打开Google地图中国北京位置（北纬39.9，东经116.3）
        Button gotomap = findViewById(R.id.gotomap);
        gotomap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("geo:39.9,116.3");//北京西三环某地
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        //7.路径规划 TODO:未成功
        // 路径规划：从北京某地（北纬39.9，东经116.3）到上海某地（北纬31.2，东经121.4）
        Button path_planning = findViewById(R.id.path_planning);
        path_planning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("http://maps.google.com/maps?f=d&saddr=39.9 116.3&daddr=31.2 121.4");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        //8.多媒体播放:TODO:未成功 app崩了
        Button playmedia = findViewById(R.id.playmedia);
        playmedia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                Uri uri = Uri.parse("file:///mnt/sdcard/foo.mp3");
                intent.setDataAndType(uri, "audio/mp3");
                startActivity(intent);
            }
        });

        //8.1获取SD卡下所有音频文件,然后播放第一首=-=   TODO:未成功 app崩了
        Button playmedia2 = findViewById(R.id.playmedia2);
        playmedia2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.withAppendedPath(MediaStore.Audio.Media.INTERNAL_CONTENT_URI, "1");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        //9.打开拍照程序  成功
        Button capture = findViewById(R.id.capture);
        capture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent_e = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent_e, 0);
            }
        });

        //9.1.取出照片数据    TODO:未成功 app崩了
        Button capture2 = findViewById(R.id.capture2);
        capture2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle extras = intent_e.getExtras();
                assert extras != null;
                Bitmap bitmap = (Bitmap) extras.get("data");

            }
        });

        //9.2.调用系统相机应用程序，并存储拍下来的照片  TODO:未成功 app崩了
        Button capture3 = findViewById(R.id.capture3);
        capture3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                long time = Calendar.getInstance().getTimeInMillis();
                intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/tucue", time + ".jpg")));
                startActivityForResult(intent, ACTIVITY_GET_CAMERA_IMAGE);

            }
        });

        //10.获取并剪切图片        TODO:未成功，没有崩，但没有结果
        Button chopimg = findViewById(R.id.chopimg);
        chopimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkPerssiom();
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                intent.putExtra("crop", "true"); // 开启剪切
                intent.putExtra("aspectX", 1); // 剪切的宽高比为1：2
                intent.putExtra("aspectY", 2);
                intent.putExtra("outputX", 20); // 保存图片的宽和高
                intent.putExtra("outputY", 40);
                intent.putExtra("output", Uri.fromFile(new File("/mnt/sdcard/temp"))); // 保存路径
                intent.putExtra("outputFormat", "JPEG");// 返回格式
                startActivityForResult(intent, 0);

            }
        });

        //10.1 剪切特定图片       TODO:未成功，打开了指定目录，选择图片后就没有下文了，后来还崩溃了
        Button chopimg2 = findViewById(R.id.chopimg2);
        chopimg2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent("com.android.camera.action.CROP");
                intent.setClassName("com.android.camera", "com.android.camera.CropImage");
                intent.setData(Uri.fromFile(new File("/mnt/sdcard/temp")));
                intent.putExtra("outputX", 1); // 剪切的宽高比为1：2
                intent.putExtra("outputY", 2);
                intent.putExtra("aspectX", 20); // 保存图片的宽和高
                intent.putExtra("aspectY", 40);
                intent.putExtra("scale", true);
                intent.putExtra("noFaceDetection", true);
                intent.putExtra("output", Uri.parse("file:///mnt/sdcard/temp"));
                startActivityForResult(intent, 0);
            }
        });

        //11.打开Google Market    已成功启动Google Play，但未能定位到指定页面，因为com.demo.app不存在于市场中
        Button google_market = findViewById(R.id.google_market);
        google_market.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("market://details?id=" + "com.demo.app");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        //12.进入手机设置界面:          成功进入指定手机设置页面
        Button go_setting = findViewById(R.id.go_setting);
        go_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 进入无线网络设置界面（其它可以举一反三）
                Intent intent = new Intent(android.provider.Settings.ACTION_WIRELESS_SETTINGS);
                startActivityForResult(intent, 0);
            }
        });

        //13.安装apk:             没有反应，没报错，没启动安装，不知道ssp是什么参数
        Button setup_apk = findViewById(R.id.setup_apk);
        setup_apk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri installUri = Uri.fromParts("package", "xxx", null);
                Intent returnIt = new Intent(Intent.ACTION_PACKAGE_ADDED, installUri);
            }
        });

       //14.卸载apk:             没有反应，没报错，没启动卸载程序
        Button uninstall_apk = findViewById(R.id.uninstall_apk);
        uninstall_apk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri installUri = Uri.fromParts("package", "com.example.second", null);
                Intent returnIt = new Intent(Intent.ACTION_DELETE, installUri);
            }
        });

       //15.发送附件:           TODO:成功启动了邮件客户端选择程序，但未走完测试流程
        Button send_attach = findViewById(R.id.send_attach);
        send_attach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(Intent.ACTION_SEND);
                it.putExtra(Intent.EXTRA_SUBJECT, "The email subject text");
                it.putExtra(Intent.EXTRA_STREAM, "file:///mnt/sdcard/foo.mp3");
                it.setType("audio/mp3");
                startActivity(Intent.createChooser(it, "Choose Email Client"));
            }
        });

       //16.进入联系人页面:    成功打开联系人列表
        Button go_contact = findViewById(R.id.go_contact);
        go_contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.setData(Contacts.People.CONTENT_URI);
                startActivity(intent);
            }
        });

       //17.查看指定联系人: TODO:成功打开指定联系人详情页，但联系人ID 不知怎么查看联系人ID
        Button view_somebody = findViewById(R.id.view_somebody);
        view_somebody.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri personUri = ContentUris.withAppendedId(Contacts.People.CONTENT_URI, 5);//info.id联系人ID
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.setData(personUri);
                startActivity(intent);
            }
        });


    }
    public void checkPerssiom() {
        if (Build.VERSION.SDK_INT >= 23) {
            int REQUEST_CODE_CONTACT = 101;
            String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
            //验证是否许可权限
            for (String str : permissions) {
                if (this.checkSelfPermission(str) != PackageManager.PERMISSION_GRANTED) {
                    //申请权限
                    this.requestPermissions(permissions, REQUEST_CODE_CONTACT);
                    return;
                } else {
                    Log.d(TAG, "this.checkSelfPermission(str) == PackageManager.PERMISSION_GRANTED");
                }
            }
        }
    }

}
