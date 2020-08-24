package rui.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;
import android.widget.Scroller;

import androidx.annotation.Nullable;


public class TouchView extends LinearLayout {

    private Scroller scroller;

    public TouchView(Context context) {
        this(context, null);
    }

    public TouchView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TouchView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        scroller = new Scroller(context);
//        smoothScrollTo(-400, -400);
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
//                Log.e(TAG, "onTouchEvent: left" + getLeft() + offsetX);
//                Log.e(TAG, "onTouchEvent: right" + getRight() + offsetX);
//                Log.e(TAG, "onTouchEvent: top" + getTop() + offsetY);
//                Log.e(TAG, "onTouchEvent: bottom" + getBottom() + offsetY);
//                layout(getLeft() + offsetX, getTop() + offsetY,
//                        getRight() + offsetX, getBottom() + offsetY);
                offsetLeftAndRight(offsetX);
                offsetTopAndBottom(offsetY);
//                LinearLayout.LayoutParams params = (LayoutParams) getLayoutParams();
//                params.leftMargin = getLeft() + offsetX;
//                params.topMargin = getTop() + offsetY;
//                Log.e(TAG, "onTouchEvent:  params.topMargin = " + params.topMargin);
//                setLayoutParams(params);
//                ((LinearLayout) getParent()).scrollBy(-offsetX, -offsetY);

                break;
            case MotionEvent.ACTION_UP:
//                int left = 0, right = 0;
//                int fw = ((LinearLayout) getParent()).getWidth();
//                int fh = ((LinearLayout) getParent()).getHeight();
//                if ((getLeft() + getWidth() / 2) * 2 > fw) {
//                    right = fw;
//                    left = fw - getWidth();
//                } else {
//                    right = getWidth();
//                    left = 0;
//                }
//                layout(left, getTop() + offsetY,
//                        right, getBottom() + offsetY);
//                Log.e(TAG, "onTouchEvent: getLeft" + getLeft());
//                smoothScrollTo(getLeft(), 0);
                break;
        }
        return true;
    }
}
