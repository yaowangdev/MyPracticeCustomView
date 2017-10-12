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

public class lineView extends View {
    private Paint mPaint;

    public lineView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    private void initView() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(5f);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawLine(100,100,300,300,mPaint);
        canvas.drawLines(new float[]{100,400,200,400,100,500,200,500},mPaint);
        canvas.drawLines(new float[]{
                400,400,400,500,
                400,500,500,500
        },4,4,mPaint);
    }
}
