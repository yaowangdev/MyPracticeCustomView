package com.yao.customview03.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.yao.customview03.R;
import com.yao.customview03.view.DemoView1;

public class DemoActivity1 extends AppCompatActivity {
    private Button btn;
    private DemoView1 demoView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo1);
        btn = (Button) findViewById(R.id.btn);
        demoView1 = (DemoView1) findViewById(R.id.demoview);
        demoView1.start();
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                demoView1.start();
//            }
//        });
    }
}
