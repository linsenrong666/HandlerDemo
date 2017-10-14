package com.linsr.handlerdemo;

import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class TwoActivity extends AppCompatActivity {

    private String TAG = "===TwoActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getMainLooper().getQueue();

    }

}
