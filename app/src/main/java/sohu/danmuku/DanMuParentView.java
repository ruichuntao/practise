package sohu.danmuku;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

import sohu.danmuku.view.IDanMuParent;

public class DanMuParentView extends RelativeLayout {

    public DanMuParentView(Context context) {
        super(context);
    }

    public DanMuParentView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        for (int i = 0; i < getChildCount(); i++) {
            View view = getChildAt(i);
            if (view instanceof IDanMuParent) {
                if (((IDanMuParent) view).hasCanTouchDanMus()) {
                    view.bringToFront();
                    view.getParent().requestDisallowInterceptTouchEvent(true);
                } else {
                    moveChildToBack(view);
                }
            }
        }
        return super.onInterceptTouchEvent(ev);
    }

    public void moveChildToBack(View child) {
        int index = indexOfChild(child);
        if (index > 0) {
            detachViewFromParent(index);
            attachViewToParent(child, 0, child.getLayoutParams());
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }

}
