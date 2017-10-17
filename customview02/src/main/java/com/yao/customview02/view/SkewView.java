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

public class SkewView extends View {
    private Paint mPaint;
    private int mWidth,mHeight;
    public SkewView(Context context, @Nullable AttributeSet attrs) {
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
        mPaint.setStrokeWidth(4f);
        mPaint.setStyle(Paint.Style.STROKE);
        RectF rectF = new RectF(-200,-200,200,200);
        canvas.skew(1,-1);
        canvas.drawRect(rectF,mPaint);
    }
}
