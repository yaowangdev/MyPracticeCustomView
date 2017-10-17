package com.yao.customview02.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2017/10/16 0016.
 */

public class ScaleView extends View {
    private Paint mPaint;
    private int mWidth,mHeight;
    public ScaleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.RED);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        mWidth = w;
        mHeight = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.translate(mWidth/2,mHeight/2);
        RectF rectF = new RectF(-300,-300,300,300);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(6f);
        for (int i = 0; i < 40; i++) {
            canvas.scale(0.9f,0.9f,100,100);
            canvas.drawRect(rectF,mPaint);
        }
    }
}
