package com.example.wuht.win10loadingview;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by wuht on 2016/9/22.
 */
public class Win10LoadingView extends View {

    private Paint
            mPaint = new Paint();
    private int mWidth, mHeight;
    private Path mCirclePath;
    private PathMeasure mPathMeasure;
    private ValueAnimator mValueAnimator;
    private float alpha, lengthOfPath;

    public Win10LoadingView(Context context) {
        this(context, null);
        init();
    }

    public Win10LoadingView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
        init();
    }

    public Win10LoadingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        initPaint(mPaint, Color.WHITE, true, Paint.Style.STROKE);
        mPaint.setStrokeWidth(12);
        mPaint.setDither(false);
        mPaint.setStrokeCap(Paint.Cap.ROUND);

        mCirclePath = new Path();
        RectF rectF = new RectF(-100, -100, 100, 100);
        mCirclePath.addArc(rectF, -90, 359.99f);

        mPathMeasure = new PathMeasure();
        mPathMeasure.setPath(mCirclePath, false);

        lengthOfPath = mPathMeasure.getLength();

        mValueAnimator = ValueAnimator.ofFloat(0, 1f).setDuration(2000);
        mValueAnimator.setRepeatCount(-1);//无限循环
        mValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                alpha = (float) animation.getAnimatedValue();
                invalidate();
            }
        });
    }

    @Override
    protected void onDraw(Canvas canvas) {
        drawPath(canvas);
    }

    private void drawPath(Canvas canvas) {

        canvas.translate(mWidth / 2, mHeight / 2);

        Path path = new Path();
        int num = (int) (alpha / 0.1);
        switch (num) {
            default:
            case 4:
                mPathMeasure.getSegment(lengthOfPath * (-25f/9*alpha*alpha+50f/9*alpha-16f/9) , lengthOfPath * (-25f/9*alpha*alpha+50f/9*alpha-16f/9)  + 0.6f, path, true);
            case 3:
                mPathMeasure.getSegment(lengthOfPath * (-2.0408163f*alpha*alpha+4.0816326f*alpha-1.0408f) , lengthOfPath * (-2.0408163f*alpha*alpha+4.0816326f*alpha-1.0408f)  + 0.6f, path, true);
            case 2:
                mPathMeasure.getSegment(lengthOfPath * (-25f / 16 * alpha * alpha + 25f / 8 * alpha - 9f / 16), lengthOfPath * (-25f / 16 * alpha * alpha + 25f / 8 * alpha - 9f / 16) + 0.6f, path, true);
            case 1:
                mPathMeasure.getSegment(lengthOfPath * (-1.2345679f * alpha * alpha + 2.4691358f * alpha - 0.2345f), lengthOfPath * (-1.2345679f * alpha * alpha + 2.4691358f * alpha - 0.2345f) + 0.6f, path, true);
            case 0:
                mPathMeasure.getSegment(lengthOfPath * (-alpha * alpha + 2 * alpha), lengthOfPath * (-alpha * alpha + 2 * alpha) + 1, path, true);
                break;
        }
        canvas.drawPath(path, mPaint);
    }


    private void initPaint(Paint paint, int red, boolean isAntiAlias, Paint.Style style) {
        paint.setColor(red);
        paint.setAntiAlias(isAntiAlias);
        paint.setStyle(style);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
    }

    public void startLoading() {
        mValueAnimator.start();
    }
}
