package com.yao.customview03.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2017/10/17 0017.
 */

public class DrawTextView extends View {
    private Paint mPaint;
    private String str = "ABC";
    public DrawTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(4f);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        mPaint.setTextSize(100);
        canvas.drawText(str,200,200,mPaint);
        canvas.drawPosText(str,new float[]{
                300,300,
                400,400,
                500,500
        },mPaint);
    }
}
