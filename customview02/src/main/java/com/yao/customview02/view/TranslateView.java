package com.yao.customview02.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2017/10/16 0016.
 */

public class TranslateView extends View {
    private Paint mPaint;
    public TranslateView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.RED);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.translate(200,200);
        canvas.drawCircle(0,0,100,mPaint);
        mPaint.setColor(Color.BLUE);
        canvas.translate(0,200);
        canvas.drawCircle(0,0,100,mPaint);
    }
}
