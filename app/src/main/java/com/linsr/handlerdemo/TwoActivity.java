package com.linsr.handlerdemo;

import android.app.Activity;
import android.nfc.Tag;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.lang.ref.WeakReference;

/**
 * 静态内部类使用handler，防止内存泄漏
 */
public class TwoActivity extends AppCompatActivity {

    private static final String TAG = "===TwoActivity";

    MyHandler mMyHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mMyHandler = new MyHandler(this);
        mMyHandler.sendEmptyMessage(0);
    }

    private static class MyHandler extends Handler {
        private WeakReference<Activity> mWeakReference;

        public MyHandler(Activity activity) {
            mWeakReference = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Activity activity = mWeakReference.get();
            switch (msg.what) {
                case 0:
                    Log.d(TAG,"receive msg ，thread:"+Thread.currentThread().getName());
                    break;
            }
        }
    }

}
