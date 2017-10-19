package com.yao.customview03.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.yao.customview03.R;

/**
 * Created by Administrator on 2017/10/17 0017.
 */

public class DemoView1 extends View {
    private Context mContext;
    private Paint mPaint;
    private Bitmap mBitmap;
    private Handler mHandler;
    private int mWidth,mHeight;
    private int sideLength;

    private int animCurrentPage = -1;
    private int animMaxPage = 13;
    private long animDuration = 1000;

    public DemoView1(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        init();
    }

    private void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.FILL);
        mBitmap = BitmapFactory.decodeResource(mContext.getResources(), R.mipmap.checkmark);
        mHandler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                if(animCurrentPage<animMaxPage && animCurrentPage>=0){
                    invalidate();
                    animCurrentPage++;
                    if(animCurrentPage==animMaxPage-1){
                        return;
                    }
                    sendEmptyMessageDelayed(0,animDuration/animMaxPage);
                }
            }
        };
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.d("DemoView1","onMeasure");
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        Log.d("DemoView1","onLayout");
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        Log.d("DemoView1","onSizeChanged");
        mWidth = w;
        mHeight = h;
        sideLength = mBitmap.getHeight();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        Log.d("DemoView1","onDraw");
        canvas.translate(mWidth/2,mHeight/2);
        canvas.drawCircle(0,0,240,mPaint);
        Rect src = new Rect(sideLength*animCurrentPage,0,sideLength*(animCurrentPage+1),sideLength);
        Rect dst = new Rect(-200,-200,200,200);
        canvas.drawBitmap(mBitmap,src,dst,null);
    }

    public void start(){
        animCurrentPage = 0;
        mHandler.sendEmptyMessageDelayed(0,animDuration/animMaxPage);
    }
}
