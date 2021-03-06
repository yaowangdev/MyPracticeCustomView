package com.yao.customview01;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2017/10/12 0012.
 */

public class ovalView extends View {
    private Paint mPaint;

    public ovalView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }
    private void initView() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(10);
        mPaint.setStyle(Paint.Style.STROKE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        RectF rectF = new RectF(100,100,600,300);
        canvas.drawOval(rectF,mPaint);
    }
}
