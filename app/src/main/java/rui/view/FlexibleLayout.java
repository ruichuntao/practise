package rui.view;

import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

public class FlexibleLayout extends LinearLayout {

    private boolean mIsDragged;
    private float mInitialY, mInitialX;
    private int mHeaderHeight;
    private int mHeaderWidth;
    private View mHeaderView;
    private final int animationTime = 300;

    public FlexibleLayout(Context context) {
        this(context, null);
    }

    public FlexibleLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FlexibleLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public FlexibleLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mHeaderView = ((ViewGroup) getChildAt(0)).getChildAt(0);
                mHeaderHeight = mHeaderView.getHeight();
                mHeaderWidth = mHeaderView.getWidth();
            }
        }, 100);

    }

    private PullListener listener;

    public interface PullListener {
        void pulling();
    }

    public void setListener(PullListener listener) {
        this.listener = listener;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mInitialX = ev.getX();
                mInitialY = ev.getY();
                mIsDragged = false;
                break;
            case MotionEvent.ACTION_MOVE:
                float diffY = ev.getY() - mInitialY;
                float diffX = ev.getX() - mInitialX;
                if (diffY > 0 && diffY / Math.abs(diffX) > 2 && mHeaderView.isShown()) {
                    mIsDragged = true;
                    return true;
                }
                break;
        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_MOVE:
                if (mIsDragged && mHeaderView.isShown()) {
                    float diffY = ev.getY() - mInitialY;
                    changeHeader(diffY);
                    return true;
                }
                break;
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                if (mIsDragged && mHeaderView.isShown()) {
                    resetHeader();
                    return true;
                }
                break;
        }
        return super.onTouchEvent(ev);
    }

    private void resetHeader() {
        int height = mHeaderView.getLayoutParams().height;
        ValueAnimator animator = ValueAnimator.ofInt(height, mHeaderHeight);
        animator.addUpdateListener(animation -> {
            mHeaderView.getLayoutParams().height = (int) animation.getAnimatedValue();
            mHeaderView.requestLayout();
        });
        int width = mHeaderView.getLayoutParams().width;
        ValueAnimator animator1 = ValueAnimator.ofInt(width, mHeaderWidth);
        animator1.addUpdateListener(animation -> {
            mHeaderView.getLayoutParams().width = (int) animation.getAnimatedValue();
            mHeaderView.requestLayout();
        });
        float x = mHeaderView.getTranslationX();
        ValueAnimator animator2 = ValueAnimator.ofFloat(x, 0);
        animator2.addUpdateListener(animation -> {
            mHeaderView.setTranslationX((Float) animation.getAnimatedValue());
        });
        AnimatorSet set = new AnimatorSet();
        set.playTogether(animator, animator1, animator2);
        set.setDuration(animationTime);
        set.start();
    }

    private static final String TAG = "FlexibleLayout";

    private void changeHeader(float offsetY) {
        int pullOffset = (int) Math.pow(offsetY, 0.8);
        int newHeight = pullOffset + mHeaderHeight;
        int newWidth = (int) ((((float) newHeight / mHeaderHeight)) * mHeaderWidth);
        RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) mHeaderView.getLayoutParams();
        params.height = newHeight;
        params.width = newWidth;
        int margin = (newWidth - mHeaderWidth) / 2;
        mHeaderView.setTranslationX(-margin);
        mHeaderView.setLayoutParams(params);
    }
}
