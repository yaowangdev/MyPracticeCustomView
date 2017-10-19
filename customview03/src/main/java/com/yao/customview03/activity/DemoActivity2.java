package com.yao.customview03.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;

import com.yao.customview03.R;
import com.yao.customview03.view.DemoView2;

public class DemoActivity2 extends AppCompatActivity {
    private DemoView2 demoView2;
    private int count;
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            demoView2.setProgress(++count);
            if(count==100){
                return;
            }
            mHandler.sendEmptyMessageDelayed(0,400);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo2);
        demoView2 = (DemoView2) findViewById(R.id.leaf_loading);
        mHandler.sendEmptyMessageDelayed(0,3000);
    }
}
