package com.yao.customview01;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/10/16 0016.
 */

public class pieView extends View{
    private Paint mPaint;

    private int mWidth;
    private int mHeight;

    private int[] mColors = {0xFFCCFF00, 0xFF6495ED, 0xFFE32636, 0xFF800000, 0xFF808000, 0xFFFF8C69, 0xFF808080,
            0xFFE6B800, 0xFF7CFC00};

    private List<PieData> mDatas;

    private float startAngle = 0;

    public pieView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mDatas = new ArrayList<>();
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        mWidth = w;
        mHeight = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if(null == mDatas){
            return;
        }
        float mCurrentStartAngle = startAngle;
        canvas.translate(mWidth/2,mHeight/2);
        float r = (float) (Math.min(mWidth,mHeight)/2*0.8);
        RectF rect = new RectF(-r,-r,r,r);
        for (int i = 0; i <mDatas.size() ; i++) {
            PieData pieData = mDatas.get(i);
            mPaint.setColor(pieData.getColor());
            canvas.drawArc(rect,mCurrentStartAngle,pieData.getAngle(),true,mPaint);
            mCurrentStartAngle+=pieData.getAngle();
        }
    }


    public void setStartAngle(float startAngle){
        this.startAngle = startAngle;
    }

    public void setDatas(List<PieData> mDatas){
        this.mDatas = mDatas;
        initDatas(mDatas);
    }
    public void setView(){
        invalidate();
    }

    private void initDatas(List<PieData> mDatas) {
        if(null == mDatas || mDatas.size()==0){
            return;
        }
        float sumValue = 0;
        for (int i = 0; i <mDatas.size(); i++) {
            PieData pie = mDatas.get(i);
            sumValue += pie.getValue();
            int j = i%mColors.length;
            pie.setColor(mColors[j]);
        }
        float sumAngle = 0;
        for (int i = 0; i <mDatas.size() ; i++) {
            PieData pie = mDatas.get(i);
            float percent = pie.getValue()/sumValue;
            float angle = percent*360;
            pie.setPercentage(percent);
            pie.setAngle(angle);
            sumAngle += angle;
        }
    }
}
