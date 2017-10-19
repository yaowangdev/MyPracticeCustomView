package com.yao.customview03.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.yao.customview03.R;
import com.yao.library.util.DisplayUtils;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * Created by Administrator on 2017/10/17 0017.
 */

public class DemoView2 extends View {
    private static final int LEFT_MARGIN = 9;// 用于控制绘制的进度条距离左／上／下的距离
    private static final int RIGHT_MARGIN = 25;// 用于控制绘制的进度条距离右的距离
    private static final int WHITE_COLOR = 0xfffde399;//淡白色
    private static final int ORANGE_COLOR = 0xffffa800;//橙黄色
    private int mLeftMargin;
    private int mRightMargin;
    private Bitmap mOuterBitmap;
    private Paint mBitmapPaint;
    private Paint mWhitePaint;
    private Paint mOrangePaint;
    private int mOuterBitmapWd;//框框宽
    private int mOuterBitmapHt;//框框高
    private int mViewWidth;//View宽
    private int mViewHeight;//View高
    private Rect mOuterSrcRect;//用于显示的内容
    private Rect mOuterDestRect;//用于显示的区域
    private RectF mWhiteRectF;//
    private RectF mArcRectF;//
    private RectF mOrangeRectF;
    private int mTotalProgress = 100;//总的Progress;
    private int mProgress = 0;//当前Progress;
    private int mProgressWidth;//进度条宽度;
    private int mCurrentProgressPosition; //进度位置
    private int mArcRadius;//圆弧半径
    private int mArcRightLocation;//

    // 叶子飘动一个周期所花的时间
    private static final long LEAF_FLOAT_TIME = 3000;
    // 叶子旋转一周需要的时间
    private static final long LEAF_ROTATE_TIME = 2000;

    // 中等振幅大小
    private int mMiddleAmplitude = MIDDLE_AMPLITUDE;
    // 振幅差
    private int mAmplitudeDisparity = AMPLITUDE_DISPARITY;

    // 叶子飘动一个周期所花的时间
    private long mLeafFloatTime = LEAF_FLOAT_TIME;
    // 叶子旋转一周需要的时间
    private long mLeafRotateTime = LEAF_ROTATE_TIME;

    // 中等振幅大小
    private static final int MIDDLE_AMPLITUDE = 13;
    // 不同类型之间的振幅差距
    private static final int AMPLITUDE_DISPARITY = 5;
    // 用于产生叶子信息
    private LeafFactory mLeafFactory;
    // 产生出的叶子信息
    private List<Leaf> mLeafInfos;
    // 用于控制随机增加的时间不抱团
    private int mAddTime;

    private Bitmap mLeafBitmap;
    private int mLeafWidth, mLeafHeight;


    public DemoView2(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mLeftMargin = DisplayUtils.dip2px(context, LEFT_MARGIN);
        mRightMargin = DisplayUtils.dip2px(context, RIGHT_MARGIN);

        initPaint();
        initBitmap();

        mLeafFactory = new LeafFactory();
        mLeafInfos = mLeafFactory.generateLeafs();
    }

    private void initPaint() {
        mBitmapPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mWhitePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mWhitePaint.setColor(WHITE_COLOR);
        mOrangePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mOrangePaint.setColor(ORANGE_COLOR);
    }

    private void initBitmap() {
        mOuterBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.leaf_kuang);
        mOuterBitmapWd=mOuterBitmap.getWidth();
        mOuterBitmapHt=mOuterBitmap.getHeight();

        mLeafBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.leaf);
        mLeafWidth = mLeafBitmap.getWidth();
        mLeafHeight = mLeafBitmap.getHeight();
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        mViewWidth = w;
        mViewHeight = h;
        mProgressWidth = mViewWidth - mLeftMargin - mRightMargin;
        mArcRadius = (mViewHeight - 2 * mLeftMargin) / 2;
        mArcRightLocation = mLeftMargin + mArcRadius;

        mOuterSrcRect = new Rect(0, 0, mOuterBitmapWd, mOuterBitmapHt);
        mOuterDestRect = new Rect(0, 0, mViewWidth, mViewHeight);

        mWhiteRectF = new RectF(mLeftMargin, mLeftMargin, mViewWidth-mRightMargin, mViewHeight-mLeftMargin);
        mArcRectF = new RectF(mLeftMargin, mLeftMargin, mLeftMargin + 2 * mArcRadius,mViewHeight - mLeftMargin);

        mOrangeRectF = new RectF(mLeftMargin + mArcRadius, mLeftMargin,mCurrentProgressPosition+mLeftMargin, mViewHeight - mLeftMargin);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        Log.d("DemoView2","onDraw");
        drawProgressAndLeafs(canvas);
        canvas.drawBitmap(mOuterBitmap,mOuterSrcRect,mOuterDestRect,mBitmapPaint);
        invalidate();
    }

    private void drawProgressAndLeafs(Canvas canvas) {
        Log.d("DemoView2","mProgress="+mProgress);
        if(mProgress > mTotalProgress){
            mProgress=0;
        }
        mCurrentProgressPosition = mProgressWidth * mProgress / mTotalProgress;
        if(mCurrentProgressPosition<mArcRadius){
            // 绘制叶子
            drawLeafs(canvas);

            int angle = (int) Math.toDegrees(Math.acos((mArcRadius - mCurrentProgressPosition)
                    / (float) mArcRadius));
            // 起始的位置
            int startAngle = 180 - angle;
            // 扫过的角度
            int sweepAngle = 2 * angle;
            canvas.drawArc(mArcRectF, startAngle, sweepAngle, false, mOrangePaint);
        }else {
            drawLeafs(canvas);
            canvas.drawArc(mArcRectF, 90, 180, false, mOrangePaint);
            // 3.绘制orange RECT
            mOrangeRectF.left = mArcRightLocation;
            mOrangeRectF.right = mCurrentProgressPosition+mLeftMargin;
            canvas.drawRect(mOrangeRectF, mOrangePaint);
        }
    }

    private void drawLeafs(Canvas canvas) {
        mLeafRotateTime = mLeafRotateTime <= 0 ? LEAF_ROTATE_TIME : mLeafRotateTime;
        long currentTime = System.currentTimeMillis();
        for (int i = 0; i < mLeafInfos.size(); i++) {
            Leaf leaf = mLeafInfos.get(i);
            if (currentTime > leaf.startTime && leaf.startTime != 0) {
                // 绘制叶子－－根据叶子的类型和当前时间得出叶子的（x，y）
                getLeafLocation(leaf, currentTime);
                // 根据时间计算旋转角度
                canvas.save();
                // 通过Matrix控制叶子旋转
                Matrix matrix = new Matrix();
                float transX = mLeftMargin + leaf.x;
                float transY = mLeftMargin + leaf.y;
//                Log.i(TAG, "left.x = " + leaf.x + "--leaf.y=" + leaf.y);
                matrix.postTranslate(transX, transY);
                // 通过时间关联旋转角度，则可以直接通过修改LEAF_ROTATE_TIME调节叶子旋转快慢
                float rotateFraction = ((currentTime - leaf.startTime) % mLeafRotateTime)
                        / (float) mLeafRotateTime;
                int angle = (int) (rotateFraction * 360);
                // 根据叶子旋转方向确定叶子旋转角度
                int rotate = leaf.rotateDirection == 0 ? angle + leaf.rotateAngle : -angle
                        + leaf.rotateAngle;
                matrix.postRotate(rotate, transX
                        + mLeafWidth / 2, transY + mLeafHeight / 2);
                canvas.drawBitmap(mLeafBitmap, matrix, mBitmapPaint);
                canvas.restore();
            } else {
                continue;
            }
        }
    }

    private void getLeafLocation(Leaf leaf, long currentTime) {
        long intervalTime = currentTime - leaf.startTime;
        mLeafFloatTime = mLeafFloatTime <= 0 ? LEAF_FLOAT_TIME : mLeafFloatTime;
        if (intervalTime < 0) {
            return;
        } else if (intervalTime > mLeafFloatTime) {
            leaf.startTime = System.currentTimeMillis()
                    + new Random().nextInt((int) mLeafFloatTime);
        }

        float fraction = (float) intervalTime / mLeafFloatTime;
        leaf.x = (int) (mProgressWidth - mProgressWidth * fraction);
        leaf.y = getLocationY(leaf);
    }

    // 通过叶子信息获取当前叶子的Y值
    private int getLocationY(Leaf leaf) {
        // y = A(wx+Q)+h
        float w = (float) ((float) 2 * Math.PI / mProgressWidth);
        float a = mMiddleAmplitude;
        switch (leaf.type) {
            case LITTLE:
                // 小振幅 ＝ 中等振幅 － 振幅差
                a = mMiddleAmplitude - mAmplitudeDisparity;
                break;
            case MIDDLE:
                a = mMiddleAmplitude;
                break;
            case BIG:
                // 小振幅 ＝ 中等振幅 + 振幅差
                a = mMiddleAmplitude + mAmplitudeDisparity;
                break;
            default:
                break;
        }
//        Log.i(TAG, "---a = " + a + "---w = " + w + "--leaf.x = " + leaf.x);
        return (int) (a * Math.sin(w * leaf.x)) + mArcRadius * 2 / 3;
    }

    private enum StartType {
        LITTLE, MIDDLE, BIG
    }

    /**
     * 叶子对象，用来记录叶子主要数据
     *
     * @author Ajian_Studio
     */
    private class Leaf {

        // 在绘制部分的位置
        float x, y;
        // 控制叶子飘动的幅度
        StartType type;
        // 旋转角度
        int rotateAngle;
        // 旋转方向--0代表顺时针，1代表逆时针
        int rotateDirection;
        // 起始时间(ms)
        long startTime;
    }

    private class LeafFactory {
        private static final int MAX_LEAFS = 8;
        Random random = new Random();

        // 生成一个叶子信息
        public Leaf generateLeaf() {
            Leaf leaf = new Leaf();
            int randomType = random.nextInt(3);
            // 随时类型－ 随机振幅
            StartType type = StartType.MIDDLE;
            switch (randomType) {
                case 0:
                    break;
                case 1:
                    type = StartType.LITTLE;
                    break;
                case 2:
                    type = StartType.BIG;
                    break;
                default:
                    break;
            }
            leaf.type = type;
            // 随机起始的旋转角度
            leaf.rotateAngle = random.nextInt(360);
            // 随机旋转方向（顺时针或逆时针）
            leaf.rotateDirection = random.nextInt(2);
            // 为了产生交错的感觉，让开始的时间有一定的随机性
            mLeafFloatTime = mLeafFloatTime <= 0 ? LEAF_FLOAT_TIME : mLeafFloatTime;
            mAddTime += random.nextInt((int) (mLeafFloatTime * 2));
            Log.d("mAddTime","mAddTime="+mAddTime);
            leaf.startTime = System.currentTimeMillis() + mAddTime;
            return leaf;
        }

        // 根据最大叶子数产生叶子信息
        public List<Leaf> generateLeafs() {
            return generateLeafs(MAX_LEAFS);
        }

        // 根据传入的叶子数量产生叶子信息
        public List<Leaf> generateLeafs(int leafSize) {
            List<Leaf> leafs = new LinkedList<>();
            for (int i = 0; i < leafSize; i++) {
                leafs.add(generateLeaf());
            }
            return leafs;
        }
    }


    public void setProgress(int progress) {
        Log.d("DemoView2","progress="+progress);
        this.mProgress = progress;
        invalidate();
    }
}
