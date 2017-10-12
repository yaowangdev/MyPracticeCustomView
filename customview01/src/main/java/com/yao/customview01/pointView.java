package com.yao.customview01;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2017/10/12 0012.
 */

public class pointView extends View {
    private Paint mPaint;

    public pointView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    private void initView() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(10);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawPoint(300,100,mPaint);
        canvas.drawPoints(new float[]{100,100,200,200,300,300},mPaint);
        //跳过前offset的个数 算count个数数据
        canvas.drawPoints(new float[]{100,400,100,500,100,600},2,4,mPaint);
    }
}
