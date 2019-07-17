package rui.view;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class ScrollColorView extends View {
    private SlideListener listener;
    private float height1 = 0;
    private float startY;
    private boolean isSlide = false;
    int height = 0;
    private static final String TAG = "ScrollColorView";

    public interface SlideListener {
        void slide(int length);
    }

    public void addSlideListener(SlideListener listener) {
        this.listener = listener;
    }

    public ScrollColorView(Context context) {
        super(context);
    }

    public ScrollColorView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ScrollColorView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                startY = event.getY();
                break;

            case MotionEvent.ACTION_MOVE:
                float y = event.getY();
                height1 += y - startY;
                // TODO: 2019/4/15 高度 + 移动的距离
                LinearLayout.LayoutParams params=new LinearLayout.LayoutParams (ViewGroup.LayoutParams.MATCH_PARENT, (int) (height+height1));
                setLayoutParams(params);
//                requestLayout();
                startY = y;
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        return true;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(getDefaultSize(getSuggestedMinimumWidth(), widthMeasureSpec),
                getDefaultSize(getSuggestedMinimumHeight(), (int) (heightMeasureSpec+height1)));
        height = heightMeasureSpec;
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
}
