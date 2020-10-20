package rui.view;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Scroller;

import androidx.annotation.Nullable;

import rui.todd.BaseActivity;


public class TouchView extends LinearLayout {

    private Scroller scroller;
    private boolean isLeft;

    public TouchView(Context context) {
        this(context, null);
    }

    public TouchView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TouchView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        scroller = new Scroller(context);
    }

    int lastX, lastY;
    private static final String TAG = "TouchView";

    public void smoothScrollTo(int destX, int destY) {
        int scrollX = getScrollX();
        int scrollY = getScrollY();
        int deltaX = destX - scrollX;
        int deltaY = destY - scrollY;
        scroller.startScroll(scrollX, scrollY, deltaX, deltaY, 500);
        invalidate();
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = ((int) event.getX());
        int y = ((int) event.getY());
        int offsetY = 0, offsetX = 0;
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                lastX = x;
                lastY = y;
                break;
            case MotionEvent.ACTION_MOVE:
                offsetX = x - lastX;
                offsetY = y - lastY;
                offsetLeftAndRight(offsetX);
                offsetTopAndBottom(offsetY);
                break;
            case MotionEvent.ACTION_UP:
                int left = 0, right = 0;
                int fw = ((FrameLayout) getParent()).getWidth();
                int fh = ((FrameLayout) getParent()).getHeight();
                if ((getLeft() + getWidth() / 2) * 2 > fw) {
                    isLeft = false;
                    right = fw;
                    left = fw - getWidth();
                } else {
                    isLeft = true;
                    right = getWidth();
                    left = 0;
                }
                BaseActivity.marginTop = getTop() + offsetY;
                BaseActivity.marginLeft = left;
//                animateStart(left, right);
                break;
        }
        return true;
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        offsetLeftAndRight(BaseActivity.marginLeft);
        offsetTopAndBottom(BaseActivity.marginTop);
    }

    private void animateStart(int left, int right) {
        int curLeft = getLeft();
        int curRight = getRight();
        ObjectAnimator animator1 = ObjectAnimator.ofInt(this, "left", curLeft, left);
        ObjectAnimator animator2 = ObjectAnimator.ofInt(this, "right", curRight, right);
        AnimatorSet set = new AnimatorSet();
        set.playTogether(animator1, animator2);
        set.setDuration(300);
        set.start();
        set.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                setX(BaseActivity.marginLeft);
                setY(BaseActivity.marginTop);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }
}
