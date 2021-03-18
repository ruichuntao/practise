package rui.view;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.RectF;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class SearchView extends View {

    Paint mPaint;
    int mViewWidth, mViewHeight;


    public SearchView(Context context) {
        this(context, null);
    }

    public SearchView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SearchView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAll();
    }

    void initAll() {
        initPaint();
        initPath();
        initListener();
        initHandler();
        initAnimator();
        mCurrentState = State.STARTING;
        mStartingAnimator.start();
    }

    enum State {
        NONE, STARTING, SEARCHING, ENDING
    }

    State mCurrentState = State.NONE;

    Path path_search, path_circle;

    PathMeasure mMeasure;

    int defaultDuration = 2000;

    ValueAnimator mStartingAnimator, mSearchAnimator, mEndingAnimator;

    float mAnimatorValue = 0;

    ValueAnimator.AnimatorUpdateListener mUpdateListener;

    Animator.AnimatorListener mAnimatorListener;

    Handler mAnimatorHandler;

    boolean isOver = false;

    int count = 0;

    void initPaint() {
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.WHITE);
        mPaint.setStrokeWidth(15);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setAntiAlias(true);
    }

    void initPath() {
        path_search = new Path();
        path_circle = new Path();
        mMeasure = new PathMeasure();
        RectF oval1 = new RectF(-50, -50, 50, 50);
        path_search.addArc(oval1, 45, 359.9f);
        RectF oval2 = new RectF(-100, -100, 100, 100);
        path_circle.addArc(oval2, 45, -359.9f);
        float[] pos = new float[2];
        mMeasure.setPath(path_circle, false);
        mMeasure.getPosTan(0, pos, null);
        path_search.lineTo(pos[0], pos[1]);

    }

    void initListener() {
        mUpdateListener = animation -> {
            mAnimatorValue = ((float) animation.getAnimatedValue());
            invalidate();
        };
        mAnimatorListener = new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                mAnimatorHandler.sendEmptyMessage(0);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        };
    }

    void initHandler() {
        mAnimatorHandler = new Handler(msg -> {
            switch (mCurrentState) {
                case STARTING:
                    isOver = false;
                    mCurrentState = State.SEARCHING;
                    mStartingAnimator.removeAllListeners();
                    mStartingAnimator.start();
                    break;
                case SEARCHING:
                    if (!isOver) {
                        mSearchAnimator.start();
                        count++;
                        if (count > 2) isOver = true;
                    } else {
                        mCurrentState = State.ENDING;
                        mEndingAnimator.start();
                    }
                    break;
                case ENDING:
                    mCurrentState = State.NONE;
                    break;
            }
            return false;
        });
    }

    void initAnimator() {
        mStartingAnimator = ValueAnimator.ofFloat(0, 1).setDuration(defaultDuration);
        mSearchAnimator = ValueAnimator.ofFloat(0, 1).setDuration(defaultDuration);
        mEndingAnimator = ValueAnimator.ofFloat(0, 1).setDuration(defaultDuration);

        mSearchAnimator.addUpdateListener(mUpdateListener);
        mStartingAnimator.addUpdateListener(mUpdateListener);
        mEndingAnimator.addUpdateListener(mUpdateListener);

        mStartingAnimator.addListener(mAnimatorListener);
        mSearchAnimator.addListener(mAnimatorListener);
        mEndingAnimator.addListener(mAnimatorListener);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mViewWidth = w;
        mViewHeight = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawSearch(canvas);
    }

    void drawSearch(Canvas canvas) {
        mPaint.setColor(Color.WHITE);
        canvas.translate(mViewWidth >> 1, mViewHeight >> 1);
        canvas.drawColor(Color.parseColor("#0082D7"));
        switch (mCurrentState) {
            case NONE:
                canvas.drawPath(path_search, mPaint);
                break;
            case STARTING:
                mMeasure.setPath(path_search, false);
                Path dst = new Path();
                mMeasure.getSegment(mMeasure.getLength() * mAnimatorValue, mMeasure.getLength(), dst, true);
                canvas.drawPath(dst, mPaint);
                break;
            case SEARCHING:
                mMeasure.setPath(path_circle, false);
                Path dst2 = new Path();
                float stop = mMeasure.getLength() * mAnimatorValue;
                float start = (float) (stop - ((0.5 - Math.abs(mAnimatorValue - 0.5)) * 200f));
                mMeasure.getSegment(start, stop, dst2, true);
                canvas.drawPath(dst2, mPaint);
                break;
            case ENDING:
                mMeasure.setPath(path_search, false);
                Path dst3 = new Path();
                mMeasure.getSegment(mMeasure.getLength() * mAnimatorValue, mMeasure.getLength(), dst3, true);
                canvas.drawPath(dst3, mPaint);
                break;
        }
    }
}
