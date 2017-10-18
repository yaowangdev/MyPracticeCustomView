package com.yao.customview03.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Picture;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2017/10/17 0017.
 */

public class DrawPicView extends View {
    private Paint mPaint;
    private Picture mPicture = new Picture();

    public DrawPicView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(3f);
        recording();
    }

    private void recording() {
        Canvas canvas = mPicture.beginRecording(500,500);
        canvas.translate(250,250);
        canvas.drawCircle(0,0,100,mPaint);
        canvas.drawRect(-240,-240,240,240,mPaint);
        mPicture.endRecording();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawPicture(mPicture,new RectF(0,0,mPicture.getWidth(),250));
    }
}
