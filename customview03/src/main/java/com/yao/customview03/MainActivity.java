package com.yao.customview03;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.yao.customview03.activity.DemoActivity1;
import com.yao.customview03.activity.DemoActivity2;
import com.yao.customview03.activity.DrawPicActivity;
import com.yao.customview03.activity.DrawTextActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btn1,btn2,btn3,btn4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        btn3 = (Button) findViewById(R.id.btn3);
        btn4 = (Button) findViewById(R.id.btn4);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn1:
                startActivity(new Intent(this, DrawPicActivity.class));
                break;
            case R.id.btn2:
                startActivity(new Intent(this, DrawTextActivity.class));
                break;
            case R.id.btn3:
                startActivity(new Intent(this, DemoActivity1.class));
                break;
            case R.id.btn4:
                startActivity(new Intent(this, DemoActivity2.class));
                break;
        }
    }
}
