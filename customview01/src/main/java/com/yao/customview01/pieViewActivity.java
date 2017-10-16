package com.yao.customview01;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class pieViewActivity extends AppCompatActivity {
    private pieView pieView;
    private List<PieData> mDatas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pie_view);
        pieView = (pieView) findViewById(R.id.pie_view);
        initDatas();
        pieView.setDatas(mDatas);
        pieView.setStartAngle(10);
        pieView.setView();
    }

    private void initDatas() {
        mDatas = new ArrayList<>();
        PieData pie1 = new PieData("美国",24);
        PieData pie2 = new PieData("中国",21);
        PieData pie3 = new PieData("日本",8);
        PieData pie4 = new PieData("俄罗斯",12);
        mDatas.add(pie1);
        mDatas.add(pie2);
        mDatas.add(pie3);
        mDatas.add(pie4);
    }
}
