package com.yao.customview01;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2017/10/12 0012.
 */

public class rectView extends View {
    private Paint mPaint;
    public rectView(Context context, @Nullable AttributeSet attrs) {
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
        canvas.drawRect(100,100,500,200,mPaint);

        Rect rect = new Rect(100,600,500,700);
        canvas.drawRect(rect,mPaint);

        RectF rectF = new RectF(100,300,500,400);//rectF精度为float,rect精度为int
        canvas.drawRect(rectF,mPaint);

        canvas.drawRoundRect(rectF,60,60,mPaint);

    }
}
