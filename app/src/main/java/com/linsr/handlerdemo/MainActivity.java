package com.linsr.handlerdemo;

import android.app.Dialog;
import android.nfc.NdefRecord;
import android.nfc.Tag;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private String TAG = "===MainActivity";
    Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            Log.d(TAG,"handleMessage,current thread:"+Thread.currentThread().getName());
            return false;
        }
    }) {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    Log.d(TAG,"receive msg,current thread:"+Thread.currentThread().getName());
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.textaaa).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG,"====");
                //dialog 使用application的context 会报错
                Dialog dialog = new Dialog(getApplicationContext());
                dialog.show();
            }
        });

//        sendMsgFromChildThread();

//        useHandlerOnChildThread();
    }

    //从子线程往主线程发送消息
    private void sendMsgFromChildThread() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.d(TAG, "send msg :" + Thread.currentThread().getName());
                mHandler.sendEmptyMessage(1);
            }
        }).start();
    }

    //在子线程中使用Handler
    private void useHandlerOnChildThread() {

        HandlerThread thread = new HandlerThread("HandlerThread");
        thread.start();
        Handler handler = new Handler(thread.getLooper()) {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                Log.d(TAG, "handler :" + Thread.currentThread().getName());//子线程
            }
        };
        handler.post(new Runnable() {
            @Override
            public void run() {
                Log.d(TAG, "post:" + Thread.currentThread().getName());//子线程
            }
        });
        handler.sendEmptyMessage(0);
    }

}
