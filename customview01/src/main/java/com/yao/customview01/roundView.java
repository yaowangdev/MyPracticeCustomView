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

public class roundView extends View {
    private Paint mPaint;
    public roundView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        mPaint.setStyle(Paint.Style.STROKE);
        canvas.drawCircle(200,200,100,mPaint);
        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(200,500,100,mPaint);
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        canvas.drawCircle(200,800,100,mPaint);

    }

    private void initView() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(50);
    }
}
