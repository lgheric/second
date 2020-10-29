package com.example.second;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.storage.StorageManager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class SdCardActivity extends BaseActivity {

        public static String TAG = "SdCardCheck";
        public TextView mTv;
        public Button mBtIn;
        public Button mBtout;
        public String result = "";
        public String inSdcardPath = "";
        public String outSdcaraPath = "";
        public static final int REQUEST_WRITE = 1;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_sdcard);

            mTv = findViewById(R.id.text);
            mBtIn = findViewById(R.id.button_in);
            mBtout = findViewById(R.id.button_out);

		    checkPerssiom();

            mBtIn.setOnClickListener(new OnClickListener(){
                @Override
                public void onClick(View v) {
                    // 获取SD卡的目录
                    saveDataToSdcard(inSdcardPath, "inSdcard_text.txt");
                }
            });
            mBtout.setOnClickListener(new OnClickListener(){
                @Override
                public void onClick(View v) {
                    saveDataToSdcard(outSdcaraPath, "outSdcard_text.txt");
                }
            });
            checkSd();
            getCommonPath();
            getStoragePath(this, true);
            getStoragePath(this, false);
            getExternalStoreAvailableSize();
            mTv.setText(result);


            //-------------------------------------------------------
            Button btCheckMountState = findViewById(R.id.bt_check_mount_state);
            Button btOutput = findViewById(R.id.bt_output_sdcard);
            Button btInput = findViewById(R.id.bt_input_sdcard);
            btCheckMountState.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                        alterToast("Sdcard可用");
                    } else {
                        alterToast("Sdcard不可用");
                    }
                }
            });
            btOutput.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    //判断是否6.0以上的手机   不是就不用
                    if (Build.VERSION.SDK_INT>= 23){
                        //判断是否有这个权限
                        if (ContextCompat.checkSelfPermission(SdCardActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
                            //2、申请权限: 参数二：权限的数组；参数三：请求码
                            ActivityCompat.requestPermissions(SdCardActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_WRITE);
                        } else {

                            File file = new File(Environment.getExternalStorageDirectory(), "MySdcard.txt");
                            if (!file.exists()) {
                                try {
                                    boolean  rs  = file.createNewFile();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                            try {
                                FileWriter fileWriter = new FileWriter(file);
                                fileWriter.write("饭撒发生的噶地方舒服舒服撒冯绍峰撒");
                                fileWriter.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }


                        }
                    }
                }
            });
            btInput.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    File file = new File(Environment.getExternalStorageDirectory(), "MySdcard.txt");
                    if (!file.exists()) {
                        alterToast("Sdcard的文件不存在");
                        return;
                    }
                    try {
                        BufferedReader br = new BufferedReader(new FileReader(file));
                        String result = br.readLine();
                        alterToast(result);
                        br.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            //-------------------------------------------------------

        }

        public void checkSd() {
            String state = Environment.getExternalStorageState();
            result += "Environment.MEDIA_MOUNTED is:" + state.equals(Environment.MEDIA_MOUNTED) + "\n";
            result += "Environment.MEDIA_MOUNTED_READ_ONLY is:" + state.equals(Environment.MEDIA_MOUNTED_READ_ONLY) + "\n";
            result += "Environment.MEDIA_SHARED is:" + state.equals(Environment.MEDIA_SHARED) + "\n";

            Log.d(TAG, "state.equals(Environment.MEDIA_MOUNTED is:" + state.equals(Environment.MEDIA_MOUNTED));
            Log.d(TAG, "state.equals(Environment.MEDIA_MOUNTED_READ_ONLY is:" + state.equals(Environment.MEDIA_MOUNTED_READ_ONLY));
            Log.d(TAG, "state.equals(Environment.MEDIA_SHARED is:" + state.equals(Environment.MEDIA_SHARED));
        }

        public String getStoragePath(Context mContext, boolean is_removale) {
            StorageManager mStorageManager = (StorageManager) mContext.getSystemService(Context.STORAGE_SERVICE);
            Class<?> storageVolumeClazz = null;
            try {
                storageVolumeClazz = Class.forName("android.os.storage.StorageVolume");
                Method getVolumeList = mStorageManager.getClass().getMethod("getVolumeList");
                Method getPath = storageVolumeClazz.getMethod("getPath");
                Method isRemovable = storageVolumeClazz.getMethod("isRemovable");
                Object resultObject = getVolumeList.invoke(mStorageManager);
                assert resultObject != null;
                final int length = Array.getLength(resultObject);
                for (int i = 0; i < length; i++) {
                    Object storageVolumeElement = Array.get(resultObject, i);
                    String path = (String) getPath.invoke(storageVolumeElement);
                    boolean removable = (Boolean) isRemovable.invoke(storageVolumeElement);
                    if (is_removale == removable) {
                        if (is_removale) {
                            result += "sdcard外部存储路经" + path + "\n";
                            outSdcaraPath = path;
                        } else {
                            result += "sdcard内部存储路经" + path + "\n";
                            inSdcardPath = path;
                        }
                        return path;
                    }
                }
            } catch (ClassNotFoundException | InvocationTargetException | NoSuchMethodException | IllegalAccessException e) {
                e.printStackTrace();
            }
            return null;
        }


        public void showSdCard() {
            //String sdcardPath = System.getenv("EXTERNAL_STORAGE");
            String sdcardPath = Environment.getExternalStorageDirectory().getAbsolutePath();
            result += "sdcard内部存储路经" + sdcardPath + "\n";
            String extSdcardPaht = System.getenv("SECONDARY_STORAGE");
            result += "sdcard外部存储路经" + extSdcardPaht + "\n";

        }
        /**
         * 得到外部存储可用的空间
         * @return 剩余空间的大小，单位是Byte
         */
        public long getExternalStoreAvailableSize(){
            String state = Environment.getExternalStorageState();
            if(state.equals(Environment.MEDIA_MOUNTED) || state.equals(Environment.MEDIA_SHARED)) {
                // 取得sdcard文件路径
                File pathFile = Environment.getExternalStorageDirectory();
                android.os.StatFs statfs = new android.os.StatFs(pathFile.getPath());
                // 获取SDCard上每个block的SIZE
                long nBlocSize = statfs.getBlockSize();
                // 获取可供程序使用的Block的数量
                long nAvailaBlock = statfs.getAvailableBlocks();
                // 计算 SDCard 剩余大小Byte
                long nSDFreeSize = nAvailaBlock * nBlocSize ;
                if (nSDFreeSize > 1024 * 1024 * 1024) {
                    result += "外部存储可用的空间:" + nSDFreeSize/(1024 * 1024 * 1024) + "G\n";
                } else {
                    result += "外部存储可用的空间:" + nSDFreeSize + "Byte\n";
                }
                return nSDFreeSize;
            } else {
                result += "size:" + "0" + "\n";
            }
            return 0;
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

        public void getCommonPath() {
            File file = Environment.getExternalStorageDirectory();
            String path = file.getAbsolutePath();
            result += "sdcard存储路经" + path + "\n";
            mTv.post(new Runnable(){
                @Override
                public void run() {
                    mTv.setText(result);
                }
            });
        }

        public void saveDataToSdcard(String path, String fileName) {
            if ("".equals(path) || path == null) {
                result += "path is null or ''" + "\n";
                mTv.post(new Runnable(){
                    @Override
                    public void run() {
                        mTv.setText(result);
                    }
                });
                return;
            }
            String str = "sangfor";
            File sdDire = Environment.getExternalStorageDirectory();
            FileOutputStream outFileStream;
            try {
                outFileStream = new FileOutputStream(path + File.separator + fileName);
                outFileStream.write(str.getBytes());
                outFileStream.close();
                Toast.makeText(SdCardActivity.this, fileName +"文件保存成功", Toast.LENGTH_LONG).show();
            } catch (Exception e) {
                e.printStackTrace();
                result += "save " + fileName +" fail" + "\n";
                mTv.post(new Runnable(){
                    @Override
                    public void run() {
                        mTv.setText(result);
                    }
                });
                Log.e(TAG, "save file fail");
            }
        }

    //动态获取内存存储权限
//    public static void verifyStoragePermissions(Activity activity) {
//        // Check if we have write permission
//        int permission = ActivityCompat.checkSelfPermission(activity,
//                Manifest.permission.WRITE_EXTERNAL_STORAGE);
//
//        if (permission != PackageManager.PERMISSION_GRANTED) {
//            // We don't have permission so prompt the user
//            ActivityCompat.requestPermissions(activity, PERMISSIONS_STORAGE, REQUEST_EXTERNAL_STORAGE);
//        }
//    }

        private void alterToast(String text) {
            Toast.makeText(SdCardActivity.this, text, Toast.LENGTH_LONG).show();
        }
}